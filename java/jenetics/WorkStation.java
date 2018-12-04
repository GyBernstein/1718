package com.gaoyuan.jenetics.sample;

import com.gaoyuan.jenetics.vo.ManageJobPlan;
import io.jenetics.BitGene;
import io.jenetics.Optimize;
import io.jenetics.Phenotype;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.util.ISeq;

import java.util.*;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;
import static io.jenetics.engine.Limits.bySteadyFitness;

/**
 * @Author 高源
 * @Date 2018/11/27 16:00
 */
public class WorkStation {

    private static List<ManageJobPlan> _jobs = new ArrayList<>();
    private static List<Integer> _chrome = new ArrayList<>();

    private final static String[] machines = new String[] {"MA1", "MA2", "MA3", "MA4", "MA5"};
    private static Random random = new Random();
    private static Map<String, Integer> ma2int = new HashMap<>();
    private static double best = Double.MAX_VALUE;

    private static void init() {
        for (int i = 0; i < machines.length; i++) {
            ma2int.put(machines[i], i);
        }

        for (int i = 0; i < 20; i++) {
            int m = random.nextInt(5);
//            (int)Math.log(Double.valueOf(i+1)*100)
            ManageJobPlan mj = new ManageJobPlan((i+1)*2 + (int)Math.log(Double.valueOf(i+1)*100), m,
                    "Workshop"+i, machines[m], "");
            _chrome.add(m);
            _jobs.add(mj);
        }
        System.out.println(_chrome);
        CODEC = Codecs.ofSubSet(ISeq.of(_chrome));
    }

    public WorkStation(List<ManageJobPlan> jobPlans, List<Integer> chrome) {
        this._jobs = jobPlans;
        this._chrome = chrome;
    }

    public static double fitness(ISeq<Integer> jms) {
        Random random = new Random();
        for (int i = 0; i < jms.size(); i++) {
            ManageJobPlan jobPlan = _jobs.get(random.nextInt(_jobs.size()));
            int j = jms.get(i);
            if (j < 5) {
                jobPlan.setMachineId(j);
            }
        }
        Map<Integer, Double> map = _jobs.stream().collect(ManageJobPlan.toMap());
        double max = 0.0;
        for (Double v : map.values()) {
            max = max > v ? max : v;
        }
        if (max < best) best = max;
        return best;
    }
    //_chrome.stream().collect(ISeq.toISeq())
    public static Codec<ISeq<Integer>, BitGene> CODEC = null;

    public static void main(String[] args) {
        init();
        final Engine<BitGene, Double> engine = Engine.builder(WorkStation::fitness, CODEC)
                .populationSize(500)
                .optimize(Optimize.MINIMUM)
                .build();
        // Create evolution statistics consumer.
        final EvolutionStatistics<Double, ?>
                statistics = EvolutionStatistics.ofNumber();
        final Phenotype<BitGene, Double> bestResult = engine.stream()
                // Truncate the evolution stream after 7 "steady"
                // generations.
                .limit(bySteadyFitness(130))
                // The evolution will stop after maximal 100
                // generations.
                .limit(500)
                // Update the evaluation statistics after
                // each generation
                .peek(statistics)
                // Collect (reduce) the evolution stream to
                // its best phenotype.
                .collect(toBestPhenotype());

        System.out.println(statistics);
        System.out.println(bestResult);
        // 时间相加除以5得1090，即掰碎工单平均分 每台机器都是1090.
        System.out.println(_jobs);
    }
}

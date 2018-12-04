package com.gaoyuan.jenetics.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

import static java.lang.String.format;


public class ManageJobPlan implements Serializable {
    private String jobId;
    private Double operateTime;
    private Integer planNum;
    private String moldCode;
    /** 机器编号**/
    private String machineCode;
    private Integer machineId;

    public ManageJobPlan(Integer planNum, Integer machineId, String jobId, String machineCode, String moldCode) {
    	this.machineId = machineId;
		this.planNum = planNum;
		this.jobId = jobId;
		this.machineCode = machineCode;
		this.moldCode = moldCode;
		this.operateTime = planNum * 10.0;
	}

	public ManageJobPlan(double operateTime) {
    	this.operateTime = operateTime;
	}

	public static Collector<ManageJobPlan, ?, Map<Integer, Double>> toMap() {
		return Collector.of(
				() -> new HashMap<Integer, Double>(),
				(a, b) -> {
					Integer key = b.getMachineId();
					if (a.get(key) == null) {
						a.put(key, b.getOperateTime());
					} else {
						a.put(key, a.get(key)+b.getOperateTime());
					} },
				(a, b) -> {
					for (Map.Entry<Integer, Double> entry: a.entrySet()) {
						entry.setValue(entry.getValue()+b.getOrDefault(entry.getKey(), 0.0));
					}
					return a;
				},
				r -> r
		);
	}



	@Override
	public String toString() {
    	String s = format("Job[jobId=%s, machineId=%d, time=%f]", jobId, machineId, operateTime);
//				this.jobId + ", " + this.machineId + ", " + this.operateTime;
    	return s;
	}

    public Integer getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Integer planNum) {
		this.planNum = planNum;
	}

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

	public String getMoldCode() {
		return moldCode;
	}

	public void setMoldCode(String moldCode) {
		this.moldCode = moldCode;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public Double getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Double operateTime) {
		this.operateTime = operateTime;
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
}

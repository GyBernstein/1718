package com.gaoyuan.hot.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;import org.apache.ibatis.parsing.GenericTokenParser;

import com.alibaba.druid.support.logging.Log;

public class QunsQuestion {

	public static void main(String[] args) {
		// 初始状态
		Map<Integer, Boolean>  bulbMap = new HashMap<>();
		for(int i = 1; i<=200000; i++) {
			bulbMap.put(i, true);
		}
		
		long start = System.currentTimeMillis();
		for(int j = 3; j<=200000; j++) {
			for(Entry<Integer, Boolean> bulb: bulbMap.entrySet()) {
				if (bulb.getKey()%j == 0) {
					bulbMap.put(bulb.getKey(), !bulb.getValue());
				}
			}
//			StringBuffer sb = new StringBuffer();
//			for(Entry<Integer, Boolean> bulb: bulbMap.entrySet()) {
//				if (bulb.getValue()) {
//					sb.append("*");
//				} else {
//					sb.append("o");
//				}17 63 2674 328222
//			}
		}
		
		List<Integer> resultList = new ArrayList<>();
		int totalCount = 0;
		for(Entry<Integer, Boolean> bulb: bulbMap.entrySet()) {
			if (bulb.getValue()) {
				resultList.add(bulb.getKey());
				totalCount+=1;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("亮着的灯泡编号："+resultList.toString());
		System.out.println("亮着的灯泡总数： "+totalCount);
		System.out.println("总用时："+ (end-start));
//		System.out.println("200以内次数和："+getPrs(200));
	}

	private static int getPrs(int num) {
		int result = 0;
		Map<Integer, Boolean> anotherMap = new HashMap<>();
		for(int j = 2; j <= num; j++) {
			int times = 0;
			int k = j;
			for(int i = 2; i <= j; i++) {
				
				while (k % i == 0) {
					k /= i;
					result += 1;
					times+=1;
				}
				if (k == 1) {
					break;
				}
			}
			if (j % 2 == 0) {
				if (times % 2 == 0) {
					anotherMap.put(j, false);
				} else {
					anotherMap.put(j, true);
				}
			} 
			
		}
		List<Integer> resultList = new ArrayList<>();
		int totalCount = 0;
		for(Entry<Integer, Boolean> entry: anotherMap.entrySet()) {
			if (entry.getValue()) {
				resultList.add(entry.getKey());
				totalCount+=1;
			}
		}
		System.out.println("亮着的灯泡编号2："+resultList.toString());
		System.out.println("亮着的灯泡总数2： "+totalCount);
		return result;
	}
}

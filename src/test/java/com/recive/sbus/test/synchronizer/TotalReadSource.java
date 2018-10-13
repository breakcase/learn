package com.recive.sbus.test.synchronizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TotalReadSource extends Thread {
	ConcurrentHashMap<Integer, Integer> map = ReadSource1.map;

	private int total = 0;

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			// dosomething
			// 遍历map计算出结果并打印
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				setTotal(getTotal() + entry.getValue());
			}
			System.out.println("总和为" + getTotal());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}

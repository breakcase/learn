package com.recive.sbus.test.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class ReadSource1 extends Thread {
	int count;
	CyclicBarrier cyclicBarrier;
	public static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();

	public ReadSource1(CyclicBarrier cyclicBarrier, int count) {
		this.cyclicBarrier = cyclicBarrier;
		this.count = count;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			// dosomething
			map.put(count, count);
			System.out.println("线程" + Thread.currentThread().getName() + "值为：" + count);
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}
}

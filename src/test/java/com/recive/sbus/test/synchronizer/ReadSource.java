package com.recive.sbus.test.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ReadSource extends Thread {
	CyclicBarrier cyclicBarrier;

	public ReadSource(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("线程" + Thread.currentThread().getName() + "读取资源,等待其他线程读取完毕！");
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}
}

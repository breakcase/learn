package com.recive.sbus.test.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		// 1.定义一个关卡 一般构造器
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
		for (int i = 0; i < 5; i++) {
			ReadSource readSource = new ReadSource(cyclicBarrier);
			readSource.start();
		}
		cyclicBarrier.await();
		System.out.println("ReadSource线程全部执行完！");

		// 2.定义一个关卡 复杂构造器
		CyclicBarrier cyclicBarrier1 = new CyclicBarrier(5, new TotalReadSource());
		for (int i = 0; i < 5; i++) {
			ReadSource1 readSource = new ReadSource1(cyclicBarrier1, i);
			readSource.start();
		}

	}

}

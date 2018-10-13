package com.recive.sbus.test.threadpool;

import java.util.concurrent.TimeUnit;

public class DoSomething implements Runnable {
	private int sleepTime;

	public DoSomething(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running.");
		try {
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

package com.recive.sbus.test.pc;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<PCData> queue;
	private static final int SLEEPTIME = 1000;

	public Consumer(BlockingQueue<PCData> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("开始消费PCData...");
		Random r = new Random();
		try {
			while (true) {
				PCData pcData = queue.take();
				if (null != pcData) {
					int re = pcData.getData() * pcData.getData();
					System.out.println(MessageFormat.format("{0}*{1}={2}", pcData.getData(), pcData.getData(), re));
				}
				Thread.sleep(r.nextInt(SLEEPTIME));
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}

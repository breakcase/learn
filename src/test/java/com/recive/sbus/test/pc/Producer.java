package com.recive.sbus.test.pc;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author recivejt
 *
 */
public class Producer implements Runnable {

	private volatile boolean isRunning = true;

	private BlockingQueue<PCData> queue;// 内存缓冲区

	private static AtomicInteger count = new AtomicInteger();// 总数 原子操作

	private static final int SLEEPTIME = 1000;

	public Producer(BlockingQueue<PCData> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("开始生产PCData...");
		PCData pcData = null;
		Random r = new Random();
		try {
			while (isRunning) {

				Thread.sleep(r.nextInt(SLEEPTIME));

				pcData = new PCData(count.incrementAndGet());// 原子方式+1

				if (!queue.offer(pcData, 2, TimeUnit.SECONDS)) {
					System.err.println(" 加入队列失败");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public void stop() {
		isRunning = false;
	}

}

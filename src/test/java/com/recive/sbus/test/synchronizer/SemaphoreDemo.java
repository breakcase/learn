package com.recive.sbus.test.synchronizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public static void main(String[] args) {
		// 只能允许5个客户端线程访问服务器资源
		final Semaphore semaphore = new Semaphore(5);
		// 新建30个线程，模拟客户端同时有30个请求线程
		ExecutorService service = Executors.newFixedThreadPool(30);
		// 模拟提交30个请求给服务器
		for (int i = 0; i < 30; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					try {
						// 获取许可
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + "线程持有信号量");
						// 模拟请求服务器资源
						Thread.sleep(2000);
						System.out.println("当前可用的信号量为:" + semaphore.availablePermits());
						// 访问完后，释放资源
						System.out.println(Thread.currentThread().getName() + "线程释放信号量");
						semaphore.release();
						System.out.println("当前可用的信号量为:" + semaphore.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		// 释放线程池资源
		service.shutdown();
	}
}

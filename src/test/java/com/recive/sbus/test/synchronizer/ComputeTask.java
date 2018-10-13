package com.recive.sbus.test.synchronizer;

import java.util.concurrent.Callable;

public class ComputeTask implements Callable<Integer> {

	private Integer result = 0;
	private String name;

	public ComputeTask(Integer result, String name) {
		this.result = result;
		this.name = name;
		System.out.println("生产子线程计算任务：" + name);
	}

	public String getTaskName() {
		return this.name;
	}

	@Override
	public Integer call() throws Exception {
		for (int i = 0; i < 10; i++) {
			result += 1;
		}
		// 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成。
		Thread.sleep(5000);
		System.out.println("子线程计算任务: " + name + " 执行完成!result:" + result);
		return result;
	}

}

package com.recive.sbus.test.synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
	public static void main(String[] args) {

		// 场景1. FutureTask执行多任务计算的使用场景
		// 创建任务集合
		List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
		// 创建线程池
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			// 传入Callable对象创建FutureTask对象
			FutureTask<Integer> ft = new FutureTask<Integer>(new ComputeTask(i, "多任务计算" + i));
			taskList.add(ft);
			// 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
			exec.submit(ft);
		}

		System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

		// 开始统计各计算线程计算结果
		Integer totalResult = 0;
		for (FutureTask<Integer> ft : taskList) {
			try {
				// FutureTask的get方法会自动阻塞,直到获取计算结果为止
				totalResult = totalResult + ft.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		// 关闭线程池
		exec.shutdown();
		System.out.println("多任务计算后的总结果是:" + totalResult);

		// 场景2.FutureTask在高并发环境下确保任务只执行一次
		
	}
}

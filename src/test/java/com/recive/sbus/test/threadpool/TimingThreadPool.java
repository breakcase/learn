package com.recive.sbus.test.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	private final Logger log = Logger.getLogger("TimingThreadPool");
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		System.out.println(String.format("Thread %s:start %s", t, r));
		startTime.set(System.nanoTime());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			System.out.println(String.format("Thread %s:end%s,time=%dns", t, r, taskTime));
		} finally {
			super.afterExecute(r, t);
		}
	}

	@Override
	protected void terminated() {
		try {
			log.info(String.format("Terminated:avg time=%dns", totalTime.get() / numTasks.get()));
		} finally {
			super.terminated();
		}
	}

	public static void main(String[] args) {
		TimingThreadPool pool = new TimingThreadPool(3, 5, 1000, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		pool.execute(new DoSomething(5));
		pool.execute(new DoSomething(4));
		pool.execute(new DoSomething(3));
		pool.execute(new DoSomething(2));
		pool.execute(new DoSomething(1));
		pool.shutdown();
	}

}

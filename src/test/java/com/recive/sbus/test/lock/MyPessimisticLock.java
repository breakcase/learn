package com.recive.sbus.test.lock;

public class MyPessimisticLock {
	private static int value = 0;

	public static void pessimisticLock(String tName) throws InterruptedException {
		Thread.sleep(1000L);
		for (int i = 0; i < 10; i++) {
			value++;
			System.out.println(tName + ":" + value);
		}
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					MyPessimisticLock.pessimisticLock("线程1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					MyPessimisticLock.pessimisticLock("线程2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}

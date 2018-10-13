package com.recive.sbus.test.lock;

import java.util.ArrayList;

public class TryLock2 {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	public static void main(String[] args) {
		final TryLock2 test = new TryLock2();

		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					test.insert(Thread.currentThread());
				};
			}.start();
		}

	}

	public void insert(Thread thread) {
		synchronized (this) {
			System.out.println(thread.getName() + "得到了锁");
			try {
				for (int i = 0; i < 5; i++) {
					arrayList.add(i);
				}
				System.out.println(thread.getName() + "sleep 10s");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
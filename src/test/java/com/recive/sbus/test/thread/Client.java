package com.recive.sbus.test.thread;

public class Client extends Thread {
	private SequeceNumber number;

	public Client(SequeceNumber number) {
		this.number = number;
	}

	@Override
	public void run() {

		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + ":[" + number.getNextNumber() + "]");
			number.putLocalMap(i + "", i + "");
			System.out.println(Thread.currentThread().getName() + "的Map的size:" + number.getLocalMap().size());
		}

	}
}

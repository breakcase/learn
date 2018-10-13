package com.recive.sbus.test.thread;

import java.util.HashMap;
import java.util.Map;

public class SequeceNumber {

	private static ThreadLocal<String> localName = new ThreadLocal<String>();

	private static ThreadLocal<Map<String, String>> localMap = new ThreadLocal<Map<String, String>>() {
		@Override
		public Map<String, String> initialValue() {
			return new HashMap<String, String>();
		}
	};

	private static ThreadLocal<Integer> number = new ThreadLocal<Integer>() {
		@Override
		public Integer initialValue() {
			return 0;
		}
	};

	public int getNextNumber() {
		number.set(number.get() + 1);
		return number.get();
	}

	public void setName(String name) {
		localName.set(name);
	}

	public String getName() {
		return localName.get();
	}

	public void putLocalMap(String key, String value) {
		Map<String, String> map = localMap.get();
		map.put(key, value);
		localMap.set(map);
	}

	public Map<String, String> getLocalMap() {
		return localMap.get();
	}

	public static void main(String[] args) {
		SequeceNumber sequeceNumber = new SequeceNumber();
		Client client1 = new Client(sequeceNumber);
		Client client2 = new Client(sequeceNumber);
		Client client3 = new Client(sequeceNumber);

		client1.start();
		client2.start();
		client3.start();
	}

}

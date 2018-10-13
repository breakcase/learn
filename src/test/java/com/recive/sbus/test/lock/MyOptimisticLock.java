package com.recive.sbus.test.lock;

import java.util.HashMap;
import java.util.Map;

public class MyOptimisticLock {

	private static Map<String, Object> map = new HashMap<String, Object>();// 数据存储

	public final static String VER = "version";
	public final static String CON = "content";

	static {
		map.put(MyOptimisticLock.VER, 0);
		map.put(CON, "");
	}

	public static int getversion() {
		return (int) map.get(VER);
	}

	public static String getContent() {
		return (String) map.get(CON);
	}

	public static void updateContent(String content) {
		map.put(CON, content);
	}

	public static void increaseVerison() {
		map.put(VER, (int) map.get(VER) + 1);
	}

	public static void reduceVerison() {
		map.put(VER, (int) map.get(VER) - 1);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new OpThread1(getversion()).start();
		}
	}

}

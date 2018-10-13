package com.recive.sbus.test.lock;

public class OpThread1 extends Thread {

	private int version;

	public OpThread1(int version) {
		this.version = version;
	}

	@Override
	public void run() {

		synchronized (OpThread1.class) {
			if (this.version + 1 > MyOptimisticLock.getversion()) {// 当前线程的版本大于数据存储的版本，证明是最新的数据
				System.out.println("线程[" + OpThread1.currentThread().getName() + "]为最新版本:[" + this.version
						+ "],进行数据更新！[" + MyOptimisticLock.getversion() + "]" + "内容:" + MyOptimisticLock.getContent());
				MyOptimisticLock.updateContent(OpThread1.currentThread().getName());// 更改数据
				MyOptimisticLock.increaseVerison();// 更改版本号
			} else {
				System.out.println("线程[" + OpThread1.currentThread().getName() + "]不能更新，线程版本号为" + this.version
						+ "存储版本号为" + MyOptimisticLock.getversion() + "内容:" + MyOptimisticLock.getContent());
			}
		}

		// System.out.println("存储的版本：" + MyOptimisticLock.getversion());
	}
}

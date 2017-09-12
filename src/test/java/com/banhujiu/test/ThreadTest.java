package com.banhujiu.test;

import run.LiftOff;

import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/12 0012 23:18
 */
public class ThreadTest {
	@Test
	public void test_runnable_run(){
		LiftOff launch = new LiftOff();
		launch.run();
	}

	@Test
	public void test_threads_start() throws InterruptedException {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("Waiting for LiftOff");

		Thread.sleep(1000);
	}
}

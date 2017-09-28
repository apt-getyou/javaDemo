package com.banhujiu.test.concurrent.thread;

import concurrent.thread.LiftOff;
import concurrent.thread.SleepingTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/9/12 0012 23:18
 */
public class ThreadTest {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void test_runnable_run() {
		LiftOff launch = new LiftOff();
		launch.run();
	}

	@Test
	public void test_threads_start() throws InterruptedException {
		Thread t = new Thread(new LiftOff());
		t.start();
		logger.info("Waiting for LiftOff");
	}

	@Test
	public void test_melita_threads_run() {
		for (int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
		}
		logger.info("Waiting for LiftOff");
	}

	@Test
	public void test_sleep_task() {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}
}

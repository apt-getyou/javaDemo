package com.banhujiu.test.concurrent.thread;

import concurrent.lock.wait.Car;
import concurrent.lock.wait.WaxOff;
import concurrent.lock.wait.WaxOn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/10/15 0015 20:16
 */
public class WaxOMaticTest {
	@Test
	public void test() throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

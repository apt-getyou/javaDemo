package com.banhujiu.test.concurrent.lock;

import concurrent.lock.toast.Butterer;
import concurrent.lock.toast.Eater;
import concurrent.lock.toast.Jammer;
import concurrent.lock.toast.ToastQueue;
import concurrent.lock.toast.Toaster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 18:19
 */
public class ToastOMaticTest {
	@Test
	public void test() throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue();
		ToastQueue butteredQueue = new ToastQueue();
		ToastQueue finishedQueue = new ToastQueue();

		ExecutorService exec = Executors.newCachedThreadPool();

		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();

	}
}

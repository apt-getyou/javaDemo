package com.banhujiu.test.concurrent.lock;

import concurrent.lock.piped.Receiver;
import concurrent.lock.piped.Sender;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 19:46
 */
public class PipedIOTest {
	@Test
	public void test() throws IOException, InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

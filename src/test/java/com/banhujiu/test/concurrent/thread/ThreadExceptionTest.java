package com.banhujiu.test.concurrent.thread;

import concurrent.thread.ExceptionThread;
import concurrent.exception.HandlerThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 20:49
 */
public class ThreadExceptionTest {
	private final Logger logger = LogManager.getLogger(this.getClass());
	@Test
	public void test_catch_exception() {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
		exec.shutdown();
	}

	@Test
	public void test_catch_uncaught_exception() {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread());
		logger.info("wait");
	}
}

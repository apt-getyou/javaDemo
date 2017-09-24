package com.banhujiu.test.concurrent.thread;

import concurrent.thread.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 16:31
 */
public class ExecutorTest {
	/**
	 * 使用Executor管理Thread对象，简化并发编程
	 */
	@Test
	public void test_cache_thread_pool() {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

	@Test
	public void test_fixed_thread_pool() {
		/*
		FixedThreadPool可以一次性预先执行代价高昂的线程分配，也可用此来限制线程数量
		但在一般使用中，还有优先使用CachedThreadPool,
		只有在使用CachedThreadPool会引发问题时才需要使用FixedThreadPool。
		 */
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

	@Test
	public void test_single_thread_executor(){
		/*
		SingleThreadExecutor就像是线程为1的FixedThreadPool。
		提供了一种重要的并发保障
		 */
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}

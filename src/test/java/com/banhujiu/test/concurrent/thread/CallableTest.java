package com.banhujiu.test.concurrent.thread;

import concurrent.thread.TaskWithResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/9/24 0024 16:47
 */
public class CallableTest {
	@Test
	public void test_callable() {
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> result = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			result.add(exec.submit(new TaskWithResult(i)));
		}
		try {
			for (Future<String> fs : result) {
				System.out.println(fs.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			exec.shutdown();
		}
	}
}

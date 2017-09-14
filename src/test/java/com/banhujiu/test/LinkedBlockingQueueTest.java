package com.banhujiu.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/8 0008 15:09
 */
public class LinkedBlockingQueueTest {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void test_Put_And_Add_When_Queue_Size_Larger_Then_Capacity() throws InterruptedException {
		LinkedBlockingQueue<Long> queue = new LinkedBlockingQueue<>(3);
		long value = 1;

		// add方法在队列已满情况下，直接抛出异常，提示队列已满
		for (int i = 0; i < 5; i++) {
			try {
				queue.add(value++);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				logger.info("队列已满，抛出异常");
				break;
			}
			logger.info("队列长度" + queue.size());
		}

		Thread.sleep(100);

		queue.clear();
		System.out.printf("清空队列\n");

		// put 当队列填满,会将当前线程挂起，等待队列内存在可用空间后继续存入
		for (int i = 0; i < 5; i++) {
			queue.put(value++);
			logger.info("队列长度" + queue.size());
		}

	}

	@Test
	public void Test_Offer() throws InterruptedException {
		LinkedBlockingQueue<Long> queue = new LinkedBlockingQueue<>(3);
		long value = 1;

		for (int i = 0; i < 5; i++) {
			queue.offer(value++);
			logger.info("队列长度" + queue.size());
		}
		logger.info(queue.toString());


		queue.clear();
		logger.info("清空队列\n");

		for (int i = 0; i < 5; i++) {
			queue.offer(value++, 2L, TimeUnit.SECONDS);
			logger.info("队列长度" + queue.size());
		}
		logger.info(queue.toString());

	}

}

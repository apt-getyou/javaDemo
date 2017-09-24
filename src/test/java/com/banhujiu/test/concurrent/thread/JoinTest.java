package com.banhujiu.test.concurrent.thread;

import concurrent.thread.Joiner;
import concurrent.thread.Sleeper;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 17:26
 */
public class JoinTest {
	@Test
	public void test_join() throws InterruptedException {
		Sleeper sleepy = new Sleeper("Sleepy", 1500);
		Sleeper grumpy = new Sleeper("Grumpy", 1500);

		Joiner dopey = new Joiner("Dopey", sleepy);
		Joiner doc = new Joiner("Doc", grumpy);

		/*
		中断grumpy，让doc运行
		而Sleepy由于没有中断，所以要等到Sleepy执行完后，dopey才会执行
		 */
		grumpy.interrupt();
		TimeUnit.SECONDS.sleep(4);
	}
}

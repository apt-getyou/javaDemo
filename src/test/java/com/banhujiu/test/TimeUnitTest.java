package com.banhujiu.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/8 0008 15:01
 */
public class TimeUnitTest {
	@Test
	public void testToNanos(){
		long time = TimeUnit.MICROSECONDS.toNanos(2);
		System.out.println(time);
	}
}

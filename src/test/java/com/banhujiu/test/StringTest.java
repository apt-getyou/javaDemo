package com.banhujiu.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/15 0015 13:54
 */
public class StringTest {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void test_String_create_object_efficiency() {
		int begin = 0;
		int size = 100000;

		{
			long stringBeginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				String s = String.valueOf(i);
			}
			logger.info("String对象创建结束，创建{}个对象，用时{}", size - begin, System.currentTimeMillis() - stringBeginTime);
		}

		{
			long stringBufferBeginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				StringBuffer sb = new StringBuffer(i);
			}
			logger.info("StringBuffer对象创建结束，创建{}个对象，用时{}", size - begin, System.currentTimeMillis() - stringBufferBeginTime);
		}

		{
			long stringBuilderBeginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				StringBuilder sb = new StringBuilder(i);
			}
			logger.info("StringBuilder对象创建结束，创建{}个对象，用时{}", size - begin, System.currentTimeMillis() - stringBuilderBeginTime);
		}
		/*
		 * 运行结果
		 * String对象创建结束，创建100000个对象，用时22
		 * StringBuffer对象创建结束，创建100000个对象，用时2687
		 * StringBuilder对象创建结束，创建100000个对象，用时1899
		 * 结论，单从创建对象来说，效率如下 String >> StringBuilder > StringBuffer
		 */
	}

	@Test
	public void test_operate_string_efficiency() {
		int begin = 0;
		int size = 50000;
		// 定义String变量原因。详见test_String_init
		String str = "str";
		{//String
			String s = "";
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				s += str;
			}
			logger.info("String操作字符对象,用时{}", System.currentTimeMillis() - beginTime);
		}
		{//StringBuilder
			StringBuilder s = new StringBuilder();
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				s.append(str);
			}
			logger.info("StringBuilder操作字符对象,用时{}", System.currentTimeMillis() - beginTime);
		}
		{//StringBuffer
			StringBuffer s = new StringBuffer();
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				s.append(str);
			}
			logger.info("StringBuffer操作字符对象,用时{}", System.currentTimeMillis() - beginTime);
		}
		/*
		 * 结果
		 * 16:06:20.812 [main] INFO  com.banhujiu.test.StringTest - String操作字符对象,用时4438
		 * 16:06:20.815 [main] INFO  com.banhujiu.test.StringTest - StringBuilder操作字符对象,用时1
		 * 16:06:20.817 [main] INFO  com.banhujiu.test.StringTest - StringBuffer操作字符对象,用时2
		 *
		 */
	}


	@Test
	public void test_String_init() {
		int begin = 0;
		int size = 50000;
		{//String
			String s = "";
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				s += String.valueOf(i);
			}
			logger.info("第一次String操作字符对象,用时{}", System.currentTimeMillis() - beginTime);
		}

		{//String
			String s = "";
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				s += String.valueOf(i);
			}
			logger.info("第二次String操作字符对象,用时{}", System.currentTimeMillis() - beginTime);
		}
		/*
		16:12:56.028 [main] INFO  com.banhujiu.test.StringTest - 第一次String操作字符对象,用时6881
		16:13:02.164 [main] INFO  com.banhujiu.test.StringTest - 第二次String操作字符对象,用时6133

		相同的代码，第二次的运行速度明显快了不少
		//TODO 解释原因
		 */
	}
}

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


}

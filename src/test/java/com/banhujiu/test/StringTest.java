package com.banhujiu.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/9/15 0015 13:54
 */
@SuppressWarnings("ALL")
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
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				String s = String.valueOf(i);
			}
			logger.info("第一次String生成对象,用时{}", System.currentTimeMillis() - beginTime);
		}

		{//String
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				String s = String.valueOf(i);
			}
			logger.info("第二次String生成对象,用时{}", System.currentTimeMillis() - beginTime);
		}

		{//String
			long beginTime = System.currentTimeMillis();
			for (int i = begin; i < size; i++) {
				String s = String.valueOf(i);
			}
			logger.info("第三次String生成对象,用时{}", System.currentTimeMillis() - beginTime);
		}
		/*
		10:39:24.833 [main] INFO  com.banhujiu.test.StringTest - 第一次String操作字符对象,用时7
		10:39:24.841 [main] INFO  com.banhujiu.test.StringTest - 第二次String操作字符对象,用时3
		10:39:24.844 [main] INFO  com.banhujiu.test.StringTest - 第二次String操作字符对象,用时3
		相同的代码，第二次的运行速度明显快了不少，而后运行速度相近了（不一定相同）
		原因：由于String对象的不可变性，java在新建String对象时，会先在jvm内存内寻找是否存在相同的值，
		如果存在，则直接引用，而不去分配新的内存并生成新的对象，所以第二次和第三次的速度才会相近且比第一次快
		 */
	}


	@Test
	public void test_string_create() {
		String a = new StringBuilder("中国").append("很大").toString();
		System.out.println(a.intern() == a);//true
		String b = new StringBuilder("ja").append("va").toString();
		System.out.println(b.intern() == b);//false


		String c = "中国" + "很大";
		System.out.println(c.intern() == c);//true
		String d = "ja" + "va";
		System.out.println(d.intern() == d);//true


		String e = "中国";
		String f = "中国";
		System.out.println(e == f);//true

		String i = new String("中国");
		String j = new String("中国");
		System.out.println(i == j);//false
		/*
		使用 new String() 方法会生成新的String对象，即使内存内已经有了相同的String对象，
		所以导致 i!=j,由于String对象的不可变性，在平时编码时，不推荐使用new String方式产生String对象，
		直接使用 String i = "" 方式，可以节省内存空间，提高运行效率，但也正因为String的不可变性，
		一旦一个String对象在内存中创建，它将是不可改变的，所有的String类中方法并不是改变String对象自己，
		而是重新创建一个新的String对象，所以在频繁操作String对象时，推荐使用
		StringBuild，如果存在线程问题，则使用StringBuffer。
		 */
	}


}

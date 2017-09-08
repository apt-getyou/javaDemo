package com.banhujiu.test;

import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/8 0008 15:33
 */
public class OverrideTest {


	private interface TestList<E> {
		void add(E e);

		void remove(E e);
	}

	interface TestQueue<E> {
		void offer();
	}

	/*
	 抽象类可不用重写接口的所有方法
	  */
	private abstract class TestAbstractList<E> implements TestList<E>, TestQueue<E> {
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			offer();
		}

		@Override
		public void offer() {
			System.out.println("TestAbstractList offer");
		}
	}

	private class TestClassList<E> extends TestAbstractList<E> {
		@Override
		public void offer() {
			System.out.println("TestClassList offer");
		}

		@Override
		public void remove(E e) {
			System.out.println("TestClassList remove");
		}
	}


	@Test
	public void test() {
		TestList<Long> testClassList = new TestClassList<>();
		testClassList.add(5L);
		//testClassList.offer(); //无法使用

		TestClassList<String> stringTestClassList = new TestClassList<>();
		stringTestClassList.offer();

		// 由于新建类时 使用的是 TestList接口定义对象，此时 testClassList 只能使用TestList定义的接口方法
	}
}

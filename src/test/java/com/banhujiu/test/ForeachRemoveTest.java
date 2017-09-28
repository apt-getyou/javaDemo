package com.banhujiu.test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/9/14 0014 11:27
 */
public class ForeachRemoveTest {
	@Test
	public void test_foreach_remove() {
		List<Long> list = buildList(10);
		try {
			for (Long aLong : list) {
				if (aLong == 6) {
					list.remove(aLong);
				}
			}
		} catch (ConcurrentModificationException e) {
			/*
			使用foreach删除集合内的参数时，由于fail-fast机制，会直接报出 java.util.ConcurrentModificationException 异常
			防止该合集在多线程操作下出现问题
			 */
			e.printStackTrace();
		}
	}

	@Test
	public void test_iterator_remove() {
		List<Long> list = buildList(10);
		Iterator<Long> it = list.iterator();
		try {
			it.remove();
		} catch (IllegalStateException e) {
			/*
			 * 迭代器的remove操作删除的是最近一次由next操作获取的元素，而不是当前游标所指向的元素。
			 * 所以初始化迭代器后，未使用next方法，remove不知道应该需要删除的元素，
			 * 会直接报 java.lang.IllegalStateException 异常
			 */
			e.printStackTrace();
		}
		while (it.hasNext()) {
			//首先需要使用next获取集合内元素
			it.next();

			it.remove();
			System.out.println(list);
		}
	}


	@Test
	public void test_foreach_remove_other_list() {
		List<Long> foreachList = buildList(10);
		List<Long> removeList = buildList(20);
		/*
		在循环内执行其他集合的remove方法，不会触发fail-fast机制
		 */
		for (Long aLong : foreachList) {
			//noinspection UseBulkOperation
			removeList.remove(aLong);
		}
		System.out.println(removeList);
	}

	private List<Long> buildList(Integer size) {
		size = size == null ? 10 : size;
		List<Long> list = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			list.add((long) i);
		}
		return list;
	}
}

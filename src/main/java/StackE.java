import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author 刘博文
 * @date 2017/6/4 0004 16:03
 */
public class StackE<E> {
	private E[] elements;

	private int size;

	private static final int DEFAULT_INITAL_CAPACITY = 16;

	public StackE() {
//		elements = new E[DEFAULT_INITAL_CAPACITY];
		elements = (E[]) new Object[DEFAULT_INITAL_CAPACITY];
	}


	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;

	}

	public E pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		E result = elements[--size];
		elements[size] = null;
		return result;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}

	public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
		Set<E> result = new HashSet<>();
		result.addAll(s1);
		result.addAll(s2);
		return result;
	}

	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<>();
	}

	public void pushAll(Iterable<? extends E> src) {
		for (E e : src) {
			push(e);
		}
	}

	public void popAll(Collection<? super E> dst) {
		while (!isEmpty()) {
			dst.add(pop());
		}
	}

	public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
		Iterator<? extends T> i = list.iterator();
		if(list.isEmpty()){
			throw new EmptyStackException();
		}
		T result = i.next();
		while (i.hasNext()) {
			T t = i.next();
			if (t.compareTo(result) > 0) {
				result = t;
			}
		}
		return result;

	}


	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		System.out.println(StackE.max(list));
	}
}

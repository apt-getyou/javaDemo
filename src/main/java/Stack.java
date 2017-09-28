import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 清空过期的对象引用
 *
 * @author banhujiu
 * @date 2017/2/28 0028 22:05
 */
public class Stack {
	private Object[] elements;

	private int size = 0;

	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	/**
	 * 此push方法在使用上不会显现出任何问题，但存在对对象的过期引用问题，导致内存泄露，且这种内存泄露十分的隐蔽
	 */
	public Object pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[--size];//此处存在内存泄露，栈收缩时没有清空被弹出的对象，导致对象的过期引用
	}

	/**
	 * 不存在内存泄露的方法
	 */
	public Object popTrue() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		Object result = elements[--size];
		elements[size] = null; //清空过期引用
		return result;
	}

	//在栈不够时扩大栈的容量
	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}

	@Override
	public String toString() {
		return "Stack{" +
				"elements=" + Arrays.toString(elements) +
				", size=" + size +
				'}';
	}

	@Override
	protected Stack clone() throws CloneNotSupportedException {
		Stack result = (Stack) super.clone();
		result.elements = elements.clone();
		return result;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Stack stack1 = new Stack();
		// CloneNotSupportedException
		System.out.print(stack1.clone() != stack1);
	}
}

package jvm;

/**
 * @author 刘博文
 * @date 2017/8/31 0031 23:04
 * jvm args ： -Xss128k (设置本地方法栈大小)
 */
public class JavaVMStackSOF {
	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		/*
		此处使用递归的写法，但程序无法控制递归的次数时（递归次数由入参控制。。）
		程序不断地向虚拟机栈（HotSpot不区分虚拟机栈和本地方法栈）压入对象，最终将栈堆满，抛出
		StackOverflowError或OutOfMemoryError异常，并导致java程序挂掉
		所以在实际编程中，应避免使用递归方法，可选择使用for循环+if判断替代
		 */
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println(oom.stackLength);
			System.err.println(e);
			throw e;
			/*
			输出：
			991
		java.lang.StackOverflowError
		Exception in thread "main" java.lang.StackOverflowError
			at jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:11)
			at jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
			at jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
			.......
			at jvm.JavaVMStackSOF.main(JavaVMStackSOF.java:18)

			单线程下无论是栈帧太大还是虚拟机栈容量太小，当内存无法分配的时候，虚拟机抛出的都是StackOverflowError异常
			 */
		}
	}


}

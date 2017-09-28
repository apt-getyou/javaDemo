package jvm;

/**
 * @author banhujiu
 * @date 2017/8/31 0031 23:20
 * JVM Args: -Xss64K
 * !!!!!不要再windows系统上执行以下代码
 * 由于在windows平台上。java的线程是映射到操作系统的内核线程上的
 * 所以以下代码很大几率（个人感觉100%）会导致系统假死
 */
public class JavaVMStackOOM {
	private void dontStop(){
		while (true){

		}
	}

	public void stackLeakByThread(){
		while (true){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}

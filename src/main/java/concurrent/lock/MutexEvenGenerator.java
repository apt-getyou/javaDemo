package concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author banhujiu
 * @date 2017/9/24 0024 22:12
 */
public class MutexEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	private Lock lock = new ReentrantLock();//显式锁

	@Override
	public int next() {
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield(); //Cause failure faster. 实际上毫无用处
			++currentEvenValue;
			return currentEvenValue; // return 在 unlock 之前
		} finally {
			/*
			 * unlock 必须要写在 finally 里面，以确保unlock不会过早发生，从而导致互斥锁失败
			 * synchronized
			 */
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}
}

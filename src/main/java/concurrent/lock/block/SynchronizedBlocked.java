package concurrent.lock.block;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/15 0015 17:26
 */
public class SynchronizedBlocked implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	public synchronized void f() {
		while (true) {
			Thread.yield();
		}
	}

	public SynchronizedBlocked() {
		new Thread(this::f);
	}

	@Override
	public void run() {
		logger.info("Trying to call f()");
		f();
		logger.info("Exiting SynchronizedBlocked.run()");
	}
}

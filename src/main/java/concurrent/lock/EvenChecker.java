package concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/9/24 0024 21:21
 */
public class EvenChecker implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	public IntGenerator generator;

	private final int id;

	public EvenChecker(IntGenerator generator, int id) {
		this.generator = generator;
		this.id = id;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				logger.info(val + " not even");
				generator.cancel();// Cancels All EvenChecks
			}
		}
	}

	public static void test(IntGenerator gp, int count) {
		final Logger logger = LogManager.getLogger();
		logger.info("press control-c to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			logger.info(i);
			exec.execute(new EvenChecker(gp, i));
		}
		exec.shutdown();
	}

	public static void test(IntGenerator gp) {
		test(gp, 10);
	}
}

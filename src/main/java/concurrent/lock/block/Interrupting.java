package concurrent.lock.block;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/15 0015 17:29
 */
public class Interrupting {

	private static ExecutorService exec = Executors.newCachedThreadPool();

	private static void test(Runnable r) throws InterruptedException {
		final Logger logger = LogManager.getLogger();
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		String name = r.getClass().getName();
		logger.info("Interrupting " + name);
		f.cancel(true);
		logger.info("Interrupting send to " + name);
	}

	public static void main(String[] args) throws Exception {
		final Logger logger = LogManager.getLogger();
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		logger.info("Aborting with System.exit(0);");
		System.exit(0);
	}
}

package concurrent.lock.block;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/15 0015 17:21
 */
public class SleepBlocked implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			logger.error("InterruptedException");
		}
		logger.debug("Exiting SleepBlocked.run()");
	}
}

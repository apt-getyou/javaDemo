package concurrent.thread;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 16:54
 */
public class SleepingTask extends LiftOff {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void run() {
		logger.info("SleepingTask was run");
		while (countDown-- > 0) {
			logger.info(status());
			try {
				TimeUnit.MILLISECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("SleepingTask was end");
	}
}

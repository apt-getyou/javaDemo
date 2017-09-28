package concurrent.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/9/24 0024 20:55
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.info("caught " + e);
		logger.info("message : " + e.getMessage());
	}
}

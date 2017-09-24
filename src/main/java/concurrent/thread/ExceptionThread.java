package concurrent.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 20:48
 */
public class ExceptionThread implements Runnable {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		logger.info("run by " + t);
		logger.info("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException("ExceptionThread Runtime");
	}
}

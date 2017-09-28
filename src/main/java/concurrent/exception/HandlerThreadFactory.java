package concurrent.exception;

import java.util.concurrent.ThreadFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/9/24 0024 20:58
 */
public class HandlerThreadFactory implements ThreadFactory {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public Thread newThread(Runnable r) {
		logger.info(this + " creating new Thread");
		Thread t = new Thread(r);
		logger.info("created " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		logger.info("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}
}

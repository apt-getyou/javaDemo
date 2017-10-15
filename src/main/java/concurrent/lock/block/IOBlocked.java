package concurrent.lock.block;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/15 0015 17:23
 */
public class IOBlocked implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private InputStream in;

	public IOBlocked(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			logger.info("Waiting for read()");
			in.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				logger.info("Interrupter from blocked I/O");
			} else {
				throw new RuntimeException(e);
			}
		}
		logger.info("Exiting IOBlocked.run()");
	}
}

package concurrent.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/9/24 0024 17:23
 */
public class Joiner extends Thread {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();//WARN
	}

	@Override
	public void run() {
		logger.info(getName() + " was run");
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			logger.info("Interrupted");
		}
		logger.info(getName() + " join completer");
	}
}

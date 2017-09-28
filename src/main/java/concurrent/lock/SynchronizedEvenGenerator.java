package concurrent.lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/9/24 0024 22:03
 */
public class SynchronizedEvenGenerator extends IntGenerator {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private int currentEvenValue = 0;

	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield(); //Cause failure faster. 实际上毫无用处
		++currentEvenValue;
		logger.info("currentEvenValue : " + currentEvenValue);
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}

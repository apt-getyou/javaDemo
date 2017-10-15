package concurrent.lock.wait;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/15 0015 20:10
 */
public class WaxOn implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private Car car;

	public WaxOn(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				logger.info("Wax ON");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waifForBuffubg();
			}
		} catch (InterruptedException e) {
			logger.info("Exiting via interrupt");
		}
		logger.info("Ending Wax On task");
	}
}

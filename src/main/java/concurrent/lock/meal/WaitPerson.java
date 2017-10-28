package concurrent.lock.meal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 15:21
 */
public class WaitPerson implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
		restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						wait();
					}
				}
				logger.info("WaitPerson got " + restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			logger.info("WaitPerson interrupted");
		}
	}
}

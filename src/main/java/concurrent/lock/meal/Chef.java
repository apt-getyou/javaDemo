package concurrent.lock.meal;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 15:37
 */
public class Chef implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private Restaurant restaurant;

	private int count = 0;

	public Chef(Restaurant r) {
		restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						wait();
					}
				}
				if (++count == 10) {
					logger.info("Out of food,closing");
					restaurant.exec.shutdownNow();
				}
				logger.info("Order up!");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notify();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			logger.info("Chef interrupted");
		}
	}
}

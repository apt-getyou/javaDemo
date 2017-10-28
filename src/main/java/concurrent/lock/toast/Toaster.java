package concurrent.lock.toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 18:03
 */
public class Toaster implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);

	public Toaster(ToastQueue tq) {
		toastQueue = tq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				logger.info(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			logger.warn("Toaster interrupted");
		}
		logger.info("Toaster off");
	}
}

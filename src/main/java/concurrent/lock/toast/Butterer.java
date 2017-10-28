package concurrent.lock.toast;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 18:07
 */
public class Butterer implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dry, ToastQueue buttered) {
		this.dryQueue = dry;
		this.butteredQueue = buttered;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = dryQueue.take();
				t.butter();
				logger.info(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			logger.info("Butterer interupted");
		}
		logger.info("Butterer off");
	}
}

package concurrent.lock.toast;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 18:11
 */
public class Jammer implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private ToastQueue butteredQueue, finishedQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = butteredQueue.take();
				t.jam();
				logger.info(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			logger.info("Jammer interrupted");
		}
		logger.info("Jammer off");
	}
}

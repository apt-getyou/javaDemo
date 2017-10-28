package concurrent.lock.toast;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 18:14
 */
public class Eater implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private ToastQueue finishedQueue;
	private int counter = 0;

	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if (t.getId() != counter++
						|| t.getStatus() != Toast.Status.JAMMED) {
					logger.error("Error " + t);
					System.exit(1);
				} else {
					logger.info("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			logger.info("Eater interrupted");
		}
		logger.info("Eater off");
	}
}

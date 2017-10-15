package concurrent.lock.wait;

/**
 * @author banhujiu
 * @date 2017/10/15 0015 20:06
 */
public class Car {
	private boolean waxOn = false;

	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	}

	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {
		while (!waxOn) {
			wait();
		}
	}

	public synchronized void waifForBuffubg() throws InterruptedException {
		while (waxOn) {
			wait();
		}
	}
}

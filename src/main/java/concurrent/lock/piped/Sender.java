package concurrent.lock.piped;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 19:36
 */
public class Sender implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private Random random = new Random(47);
	private PipedWriter out = new PipedWriter();

	public PipedWriter getPipedWriter() {
		return out;
	}

	@Override
	public void run() {
		try {
			while (true) {
				for (char c = 'A'; c <= 'z'; c++) {
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
				}
			}
		} catch (IOException e) {
			logger.info(e + " Sender write exceprion");
		} catch (InterruptedException e) {
			logger.info(e + " Sender sleep interrupted");
		}
	}
}

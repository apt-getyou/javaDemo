package concurrent.lock.piped;

import java.io.IOException;
import java.io.PipedReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/10/22 0022 19:43
 */
public class Receiver implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private PipedReader in;

	public Receiver(Sender sender) throws IOException {
		in = new PipedReader(sender.getPipedWriter());
	}

	@Override
	public void run() {
		try {
			while (true) {
				logger.info("Red: " + (char) in.read() + ",");
			}
		} catch (IOException e) {
			logger.info(e + " Receiver read exception");
		}
	}
}

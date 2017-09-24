package concurrent.thread;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 刘博文
 * @date 2017/9/24 0024 17:17
 */
public class Sleeper extends Thread {
	private final Logger logger = LogManager.getLogger(this.getClass());

	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		this.duration = sleepTime;
		start();//WARN 该写法是存在问题的，现在只是示例足够简单才这么写
	}

	@Override
	public void run() {
		logger.info(getName() + " was run");
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			logger.info((getName() + " was interrupted. " +
					"isInterrupted(): " + isInterrupted()));
		}
		logger.info(getName() + " has awakened");
	}
}

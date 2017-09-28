package concurrent.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/9/12 0012 23:06
 */
public class LiftOff implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());

	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" +
				(countDown > 0 ? countDown : "Liftoff!") + "),";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			logger.info(status());
			/*
			告诉线程调度器，自己已处理好程序最主要的部分，可以让其他的线程使用CPU了,
			即让步、但没有任何机制保障它会被采纳。
			大体上，对应任何重要的的控制或在调整应用时，都不能依赖于yield()。
			 */
			Thread.yield();
		}
	}

}

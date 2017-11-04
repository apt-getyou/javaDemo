package calc24.queue;

import calc24.Calc24Model;
import calc24.CalcConf;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author banhujiu
 * @date 2017/10/27 0027 14:37
 */
public class BaseModeQueue<T extends Comparable<T>> extends LinkedBlockingQueue<Calc24Model<T>> {
	public BaseModeQueue() {
		super(CalcConf.maxQueueSize);
	}
}

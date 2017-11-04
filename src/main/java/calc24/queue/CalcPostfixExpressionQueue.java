package calc24.queue;

import calc24.Calc24Model;
import calc24.CalcConf;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author banhujiu
 * @date 2017/11/4 0004 20:19
 */
public class CalcPostfixExpressionQueue<T extends Comparable<T>> extends LinkedBlockingQueue<Map<Calc24Model<T>, List<List<String>>>> {
	public CalcPostfixExpressionQueue() {
		super(CalcConf.maxQueueSize);
	}
}

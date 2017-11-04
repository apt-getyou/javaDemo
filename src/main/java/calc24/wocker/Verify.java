package calc24.wocker;

import calc.utils.CalcExpression;
import calc24.Calc24Model;
import calc24.CalcConf;
import calc24.queue.CalcPostfixExpressionQueue;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/11/4 0004 20:55
 */
public class Verify<T extends Comparable<T>> implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private CalcPostfixExpressionQueue<T> calcPostfixExpressionQueue;
	private ConcurrentMap<Calc24Model<T>, Vector<String>> result;

	public Verify(CalcPostfixExpressionQueue<T> calcPostfixExpression, ConcurrentMap<Calc24Model<T>, Vector<String>> result) {
		this.calcPostfixExpressionQueue = calcPostfixExpression;
		this.result = result;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Map<Calc24Model<T>, List<List<String>>> take = calcPostfixExpressionQueue.take();
				for (Map.Entry<Calc24Model<T>, List<List<String>>> entry : take.entrySet()) {
					for (List<String> list : entry.getValue()) {
						int resultInt = 0;
						try {
							resultInt = CalcExpression.calcPostfixExpression(list);
						} catch (Exception e) {
//							logger.info("无法计算");
						}
						if (resultInt == CalcConf.total) {
							result.computeIfAbsent(entry.getKey(), k -> new Vector<>());
							result.get(entry.getKey()).add(list.toString());
						}
					}
				}
			}
		} catch (InterruptedException e) {
			logger.info("Verify InterruptedException");
		}
		logger.info("Verify off");
	}
}

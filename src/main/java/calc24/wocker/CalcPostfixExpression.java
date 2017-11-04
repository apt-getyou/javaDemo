package calc24.wocker;

import calc.utils.CalcRulesEnums;
import calc.utils.Permutations;
import calc24.Calc24Model;
import calc24.queue.BaseModeQueue;
import calc24.queue.CalcPostfixExpressionQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author banhujiu
 * @date 2017/11/4 0004 20:35
 */
public class CalcPostfixExpression<T extends Comparable<T>> implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private BaseModeQueue<T> baseModeQueue;
	private CalcPostfixExpressionQueue<T> calcPostfixExpressionQueue;

	public CalcPostfixExpression(BaseModeQueue<T> baseModeQueue, CalcPostfixExpressionQueue<T> calcPostfixExpressionQueue) {
		this.baseModeQueue = baseModeQueue;
		this.calcPostfixExpressionQueue = calcPostfixExpressionQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Calc24Model<T> take = baseModeQueue.take();
				List<String> list = new ArrayList<>();
				for (T o : take.getNums()) {
					list.add(o.toString());
				}
				for (CalcRulesEnums enums : take.getRulesEnumsList()) {
					list.add(String.valueOf(enums.getCalc()));
				}
				List<List<String>> permute = Permutations.permute(list);
				Map<Calc24Model<T>, List<List<String>>> temp = new HashMap<>();
				temp.put(take, permute);
				calcPostfixExpressionQueue.put(temp);
			}
		} catch (InterruptedException e) {
			logger.info("CalcPostfixExpression InterruptedException");
		}
		logger.info("CalcPostfixExpression off");
	}
}

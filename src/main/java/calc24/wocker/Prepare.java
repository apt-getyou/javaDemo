package calc24.wocker;

import calc.utils.CalcRulesEnums;
import calc24.Calc24Model;
import calc24.queue.BaseModeQueue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author banhujiu
 * @date 2017/11/4 0004 19:30
 */
public class Prepare<T extends Comparable<T>> implements Runnable {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private List<List<T>> sourceList;
	private List<List<CalcRulesEnums>> calcRulesList;
	private BaseModeQueue<T> baseModeQueue;

	public Prepare(List<List<T>> sourceList, List<List<CalcRulesEnums>> calcRulesList, BaseModeQueue<T> baseModeQueue) {
		this.sourceList = sourceList;
		this.calcRulesList = calcRulesList;
		this.baseModeQueue = baseModeQueue;
	}

	@Override
	public void run() {
		try {
			for (List<T> ts : sourceList) {
				for (List<CalcRulesEnums> calcRulesEnums : calcRulesList) {
					Calc24Model<T> model = Calc24Model.initByIntegerList(ts, calcRulesEnums);
//						logger.info("产生初始数据: {}", model);
					baseModeQueue.put(model);
				}
			}
		} catch (InterruptedException e) {
			logger.error("Prepare InterruptedException");
		}
		logger.info("Prepare off");
	}
}

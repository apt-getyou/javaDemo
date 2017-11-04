package calc24;

import calc.utils.CalcRulesEnums;
import calc.utils.Combination;
import calc24.queue.BaseModeQueue;
import calc24.queue.CalcPostfixExpressionQueue;
import calc24.wocker.CalcPostfixExpression;
import calc24.wocker.Prepare;
import calc24.wocker.Verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author banhujiu
 * @date 2017/11/4 0004 17:30
 */
public class Bootstrap {
	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new ArrayList<>();
		for (int i = CalcConf.min; i <= CalcConf.max; i++) {
			list.add(i);
		}
		ConcurrentMap<Calc24Model<Integer>, Vector<String>> result = new ConcurrentHashMap<>();
		List<List<Integer>> sourceList = Combination.combinationWithRepetition(list, CalcConf.num);
		List<List<CalcRulesEnums>> calcRulesList = Combination.combinationWithRepetition(CalcRulesEnums.toList(), CalcConf.num - 1);

		ExecutorService exec = Executors.newCachedThreadPool();
		BaseModeQueue<Integer> baseModeQueue = new BaseModeQueue<>();
		CalcPostfixExpressionQueue<Integer> calcPostfixExpressionQueue = new CalcPostfixExpressionQueue<>();
		exec.execute(new Prepare<>(sourceList, calcRulesList, baseModeQueue));
		for (int i = 0; i < 10; i++) {
			exec.execute(new CalcPostfixExpression<>(baseModeQueue, calcPostfixExpressionQueue));
		}
		for (int i = 0; i < 10; i++) {
			exec.execute(new Verify<>(calcPostfixExpressionQueue, result));
		}
		List<Integer> testList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			testList.add(8);
		}
		testList.add(1);
		Calc24Model<Integer> model = Calc24Model.initByIntegerList(testList, calcRulesList.get(0));
		while (result.get(model) == null) {
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(result.get(model));
	}
}

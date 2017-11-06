package calc24;

import calc.utils.CalcExpression;
import calc.utils.CalcRulesEnums;
import calc.utils.Combination;
import calc.utils.Permutations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author banhujiu
 * @date 2017/11/6 0006 12:13
 */
public class Calc24Util {
	public static Map<Calc24Model<BigDecimal>, List<String>> getCalcMap() {
		Map<Calc24Model<BigDecimal>, List<String>> result = new HashMap<>();
		List<BigDecimal> list = new ArrayList<>();
		for (int i = CalcConf.min; i <= CalcConf.max; i++) {
			list.add(new BigDecimal(i));
		}
		List<List<BigDecimal>> sourceList = Combination.combinationWithRepetition(list, CalcConf.num);
		List<List<CalcRulesEnums>> calcRulesList = Combination.combinationWithRepetition(CalcRulesEnums.toList(), CalcConf.num - 1);
		BigDecimal total = new BigDecimal(CalcConf.total);
		for (List<BigDecimal> ts : sourceList) {
			Calc24Model<BigDecimal> model = Calc24Model.initByIntegerList(ts, null);
			result.computeIfAbsent(model, k -> new ArrayList<>());
			List<String> baseStringList = new ArrayList<>();
			for (BigDecimal t : ts) {
				baseStringList.add(t.toString());
			}
			for (List<CalcRulesEnums> calcRulesEnums : calcRulesList) {
				List<String> tempList = new ArrayList<>(baseStringList);
				for (CalcRulesEnums calcRulesEnum : calcRulesEnums) {
					tempList.add(String.valueOf(calcRulesEnum.getCalc()));
				}
				List<List<String>> calcList = Permutations.permute(tempList);
				for (List<String> strings : calcList) {
					BigDecimal resultInt;
					try {
						resultInt = CalcExpression.calcPostfixExpression(strings);
					} catch (Exception e) {
						continue;
					}
					if (resultInt.compareTo(total) == 0) {
						result.get(model).add(strings.toString());
						System.out.println(strings.toString());
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		Map<Calc24Model<BigDecimal>, List<String>> calcMap = Calc24Util.getCalcMap();
		System.out.println(System.currentTimeMillis() - currentTime);
		System.out.println(calcMap);
	}
}

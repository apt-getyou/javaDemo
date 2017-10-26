package calc24;

import java.util.HashMap;
import java.util.Map;

/**
 * @author banhujiu
 * @date 2017/10/18 0018 16:27
 */
public enum CalcRulesEnums {
	ADD('+', (short) 1),
	SUB('-', (short) 1),
	MUL('*', (short) 2),
	DIV('/', (short) 2);


	private char calc;

	private short priority;

	CalcRulesEnums(char calc, short priority) {
		this.calc = calc;
		this.priority = priority;
	}

	public char getCalc() {
		return calc;
	}

	private short getPriority() {
		return priority;
	}

	public static boolean diffPriority(char source, char target) {
		Map<Character, CalcRulesEnums> map = new HashMap<>();
		for (CalcRulesEnums rules : CalcRulesEnums.values()) {
			map.put(rules.getCalc(), rules);
		}
		CalcRulesEnums sourceEnums = map.get(source);
		CalcRulesEnums targetEnums = map.get(target);
		if (sourceEnums == null || targetEnums == null) {
			throw new RuntimeException("存在不支持的运算方式");
		}
		return sourceEnums.getPriority() > targetEnums.getPriority();
	}


	@Override
	public String toString() {
		return String.valueOf(calc);
	}
}

package calc.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author banhujiu
 * @date 2017/10/18 0018 16:27
 */
public enum CalcRulesEnums {
	ADD('+', (short) 1) {
		@Override
		public BigDecimal calc(BigDecimal leftNum, BigDecimal rightNum) {
			return leftNum.add(rightNum);
		}
	},
	SUB('-', (short) 1) {
		@Override
		public BigDecimal calc(BigDecimal leftNum, BigDecimal rightNum) {
			return leftNum.subtract(rightNum);
		}
	},
	MUL('*', (short) 2) {
		@Override
		public BigDecimal calc(BigDecimal leftNum, BigDecimal rightNum) {
			return leftNum.multiply(rightNum);
		}
	},
	DIV('/', (short) 2) {
		@Override
		public BigDecimal calc(BigDecimal leftNum, BigDecimal rightNum) {
			if (rightNum.compareTo(BigDecimal.ZERO) == 0) {
				throw new RuntimeException("除法，被除数不能为0");
			}
			return leftNum.divide(rightNum, BigDecimal.ROUND_HALF_DOWN);
		}
	};


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

	public static List<CalcRulesEnums> toList() {
		List<CalcRulesEnums> list = new ArrayList<>();
		list.addAll(Arrays.asList(CalcRulesEnums.values()));
		return list;
	}

	@Override
	public String toString() {
		return String.valueOf(calc);
	}

	public static BigDecimal calc(String s, BigDecimal leftNum, BigDecimal rightNum) {
		if (s == null || leftNum == null || rightNum == null) {
			throw new NullPointerException();
		}
		CalcRulesEnums enums = null;
		for (CalcRulesEnums calcRulesEnums : CalcRulesEnums.values()) {
			if (s.equals(String.valueOf(calcRulesEnums.getCalc()))) {
				enums = calcRulesEnums;
			}
		}
		if (enums == null) {
			throw new RuntimeException("存在不支持的运算方式");
		}
		return enums.calc(leftNum, rightNum);
	}

	public abstract BigDecimal calc(BigDecimal leftNum, BigDecimal rightNum);
}

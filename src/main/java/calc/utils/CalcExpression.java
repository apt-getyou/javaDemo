package calc.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author banhujiu
 * @date 2017/10/18 0018 17:00
 */
public class CalcExpression {
	/**
	 * 中缀表达式转换为后缀表达式
	 *
	 * bug1 连续两个数， 未报异常 ， 即 3 4 两个数会被认为34
	 */
	public static String infixExpressionToPostfixExpression(String input) {
		if (input == null) {
			throw new NullPointerException();
		}
		char[] split = input.trim().toCharArray();
		Stack<Character> s1 = new Stack<>();
		Stack<String> s2 = new Stack<>();
		StringBuilder num = new StringBuilder();
		boolean flag;
		for (int i = 0; i < split.length; i++) {
			char c = split[i];
			if (c == ' ') {
				continue;
			}
			if (NumberUtils.checkCharIsNumber(c)) {
				num.append(c);
				if (i == split.length - 1) {
					s2.push(num.toString());
				}
			} else {
				if (num.length() > 0) {
					s2.push(num.toString());//遇到运算符
					num.delete(0, num.length());
				}
				if (c == '(' || c == ')') {
					if (c == '(') {
						s1.push(c);
					} else {
						while (s1.lastElement() != '(') {
							s2.push(s1.pop().toString());
						}
						s1.pop();
					}
				} else {
					flag = true;
					while (flag) {
						if (s1.isEmpty() || s1.lastElement() == '(') {
							s1.push(c);
							flag = false;
						} else if (CalcRulesEnums.diffPriority(c, s1.lastElement())) {
							s1.push(c);
							flag = false;
						} else {
							s2.push(s1.pop().toString());
						}
					}
				}
			}
		}
		while (!s1.isEmpty()) {
			s2.push(s1.pop().toString());
		}
		String[] result = new String[s2.size()];

		for (int i = result.length - 1; i >= 0; i--) {
			result[i] = s2.pop();
		}
		StringBuilder resultSb = new StringBuilder();
		for (String s : result) {
			resultSb.append(s).append(" ");
		}
		return resultSb.toString();
	}

	public static BigDecimal calcPostfixExpression(List<String> list) {
		Stack<BigDecimal> s1 = new Stack<>();
		for (String s : list) {
			if (NumberUtils.checkStringIsNumber(s)) {
				s1.push(new BigDecimal(s));
			} else {
				if (s1.size() < 2) {
					throw new RuntimeException("表达式错误");
				}
				BigDecimal rightNum = s1.pop();
				BigDecimal leftNum = s1.pop();
				s1.push(CalcRulesEnums.calc(s, leftNum, rightNum));
			}
		}
		return s1.pop();
	}

	public static BigDecimal calcPostfixExpression(String expression , String split){
		if(expression == null || split == null){
			throw new NullPointerException();
		}
		List<String> list = Arrays.asList(expression.split(split));
		return calcPostfixExpression(list);
	}
}

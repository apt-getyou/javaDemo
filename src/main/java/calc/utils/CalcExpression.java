package calc.utils;

import java.util.List;
import java.util.Stack;

/**
 * @author banhujiu
 * @date 2017/10/18 0018 17:00
 */
public class CalcExpression {
	private static final String number = "0123456789";

	/**
	 * 中缀表达式转换为后缀表达式
	 *
	 * bug1 连续两个数， 未报异常 ， 即 3 4 两个数会被认为34
	 */
	public static String toPostfixExpression(String input) {
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
			if (number.indexOf(c) != -1) {
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

	public static int calcPostfixExpression(List<String> list) {
		Stack<Integer> s1 = new Stack<>();
		for (String s : list) {
			if (number.contains(s)) {
				s1.push(Integer.valueOf(s));
			} else {
				if (s1.size() < 2) {
					throw new RuntimeException("表达式错误");
				}
				Integer rightNum = s1.pop();
				Integer leftNum = s1.pop();
				s1.push(CalcRulesEnums.calc(s, leftNum, rightNum));
			}
		}
		return s1.pop();
	}

}

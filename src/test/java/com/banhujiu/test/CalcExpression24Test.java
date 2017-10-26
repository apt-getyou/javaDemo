package com.banhujiu.test;

import calc24.CalcExpression;

import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/10/20 0020 15:06
 */
public class CalcExpression24Test {
	@Test
	public void test_calc_expression() {
		String a1 = "15 - 6 * 3 6 + 693 + (1 +  2) + 233  ";
		String a2 = "15  - 36 * 6 + 693 + (1+2)";
		String a3 = "1+((2+3)×4)-5";
		System.out.println(CalcExpression.calcPostfixExpression(a1));
		System.out.println(CalcExpression.calcPostfixExpression(a2));
		System.out.println(CalcExpression.calcPostfixExpression(a3));
	}
}

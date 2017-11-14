package com.banhujiu.test;

import calc.utils.CalcExpression;

import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/11/14 0014 16:35
 */
public class CalcExpressionTest {


	@Test
	public void test_calc_(){
		String expression = "8, 1, 4, /, -, 3, *";
		System.out.println(CalcExpression.calcPostfixExpression(expression, ", "));
	}
}

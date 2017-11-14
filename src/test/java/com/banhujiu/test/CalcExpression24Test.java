package com.banhujiu.test;

import calc.utils.CalcExpression;
import calc.utils.Permutations;

import java.util.ArrayList;
import java.util.List;

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
		String a5 = "((1+1) + 2) * 6";
		String a6 = "((8-5) + 1) * 6";
		System.out.println(CalcExpression.toPostfixExpression(a1));
		System.out.println(CalcExpression.toPostfixExpression(a2));
		System.out.println(CalcExpression.toPostfixExpression(a3));
		System.out.println(CalcExpression.toPostfixExpression(a5));
		System.out.println(CalcExpression.toPostfixExpression(a6));
	}

	@Test
	public void test_calc_permutations() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			list.add(i + 1);
		}
		System.out.println("排列");
		for (List<Integer> integers : Permutations.permute(list)) {
			System.out.println(integers);
		}
		System.out.println("可重排列");
		for (List<Integer> integers : Permutations.permutationWithRepetition(list, 3)) {
			System.out.println(integers);
		}
	}
}

package com.banhujiu.test;

import calc24.Calc24Model;
import calc24.Calc24Util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Test;


/**
 * @author banhujiu
 * @date 2017/11/6 0006 12:13
 */
public class Calc24UtilTest {

	@Test
	public void test_get_calc_map() {
		long currentTime = System.currentTimeMillis();
		Map<Calc24Model<BigDecimal>, List<String>> calcMap = Calc24Util.getCalcMap();
		System.out.println(System.currentTimeMillis() - currentTime);
		System.out.println(calcMap);
	}
}

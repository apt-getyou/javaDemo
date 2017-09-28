package com.banhujiu.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author 刘博文
 * @date 2017/9/26 0026 22:15
 */
public class MapTest {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void test_Map_merge() {
		Map<Long, BigDecimal> map = new HashMap<>();
		for (int j = 0; j < 5; j++) {
			int finalJ = j;
			map.merge((long) j, BigDecimal.ZERO, (a, b) -> (a.add(new BigDecimal(finalJ))));
		}
		for (int j = 0; j < 5; j++) {
			int finalJ = j;
			map.merge((long) j, BigDecimal.ZERO, (a, b) -> (a.add(new BigDecimal(finalJ))));
		}
		for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
			logger.info("id : {} , value : {} ", entry.getKey(), entry.getValue());
		}
	}
}

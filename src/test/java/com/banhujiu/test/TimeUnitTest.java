package com.banhujiu.test;

import utils.DateUtils;
import utils.enums.DateFormatEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author banhujiu
 * @date 2017/9/8 0008 15:01
 */
public class TimeUnitTest {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void testToNanos() {
		long time = TimeUnit.MICROSECONDS.toNanos(2);
		System.out.println(time);
	}

	@Test
	public void test_getYearMonth() {
		List<Integer> list = getLastYearToNow();
		logger.info(list);
	}

	private List<Integer> getLastYearToNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date endMonth = calendar.getTime();

		calendar.add(Calendar.YEAR, -1);
		Date beginMonth = calendar.getTime();

		return DateUtils.getYearMonthList(beginMonth, endMonth);
	}


	@Test
	public void test_dataFormatEnum() {
		logger.info("时间为" + DateFormatEnum.formatDate("456555"));
	}

	@Test
	public void test_integerToYearMonth(){
		logger.info(DateUtils.integerToYearMonth(201806));
		logger.info(DateUtils.integerToYearMonth(201807));
		logger.info(DateUtils.integerToYearMonth(201812));
	}

	@Test
	public void test_draw_table_for_month_and_customer() {
		List<Integer> monthList = getLastYearToNow();
		List<Long> idList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			idList.add((long) i);
		}
		TreeMap<Long, TreeMap<Integer, BigDecimal>> map = new TreeMap<>();
		for (Long id : idList) {
			map.put(id, new TreeMap<>());
		}
		Random random = new Random();
		for (Map.Entry<Long, TreeMap<Integer, BigDecimal>> entry : map.entrySet()) {
			for (Integer month : monthList) {
				entry.getValue().put(month, new BigDecimal(random.nextInt(9)));
			}
		}
		StringBuilder sb = new StringBuilder("| 客户\\月份 |");
		for (Integer month : monthList) {
			sb.append(" ").append(month).append(" |");
		}
		System.out.println(sb.toString());
		for (Map.Entry<Long, TreeMap<Integer, BigDecimal>> entry : map.entrySet()) {
			StringBuilder tempSb = new StringBuilder("| 客户id：").append(entry.getKey().toString()).append(" |");
			for (Map.Entry<Integer, BigDecimal> decimalEntry : entry.getValue().entrySet()) {
				tempSb.append("   ").append(decimalEntry.getValue()).append("    |");
			}
			System.out.println(tempSb.toString());
		}
	}


	@Test
	public void test(){
		logger.info(DateUtils.getTheEndDayFotMoth(new Date()));
		logger.info(DateUtils.getTheBeginDayFotMoth(new Date()));
	}

}

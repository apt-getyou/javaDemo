package com.banhujiu.test;

import utils.DateUtils;
import utils.enums.DateFormatEnum;

import java.util.Calendar;
import java.util.Date;
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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date endMonth = calendar.getTime();

		calendar.add(Calendar.YEAR, -1);
		Date beginMonth = calendar.getTime();

		logger.info(DateUtils.getYearMonthList(beginMonth, endMonth));
	}


	@Test
	public void test_dataFormatEnum() {
		logger.info("时间为" + DateFormatEnum.formatDate("456555"));
	}

}

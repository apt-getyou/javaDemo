package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author banhujiu
 * @date 2017/9/29 0029 10:43
 */
public class DateUtils {
	public static List<Integer> getYearMonthList(Date beginMonth, Date endMonth) {
		List<Integer> result = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		if (beginMonth == null || endMonth == null) {
			return result;
		}
		if (beginMonth.after(endMonth)) {
			return result;
		}
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(beginMonth);
		max.setTime(endMonth);

		while (min.compareTo(max) <= 0) {
			Integer format = Integer.valueOf(sdf.format(min.getTime()));
			result.add(format);
			min.add(Calendar.MONTH, 1);
		}
		return result;
	}

	public static String integerToYearMonth(Integer date) {
		if (date == null) {
			return "";
		}
		String yearMonth = String.valueOf(date);
		if (yearMonth.length() != 6) {
			throw new RuntimeException("数据格式错误");
		}
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(4, 6);
		if (month.startsWith("0")) {
			month = month.substring(1, 2);
		}
		return year + "年" + month + "月";
	}


	public static String getTheBeginDayFotMoth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, -12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	public static String getTheEndDayFotMoth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
}

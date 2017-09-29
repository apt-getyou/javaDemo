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
			System.out.println(format);
			result.add(format);
			min.add(Calendar.MONTH, 1);
		}
		return result;
	}

}

package utils.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author banhujiu
 * @date 2017/9/29 0029 11:03
 */
public enum DateFormatEnum {
	DATE_FORMAT1(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),
	DATE_FORMAT2(new SimpleDateFormat("yyyy-MM-dd")),
	DATE_FORMAT3(new SimpleDateFormat("yyyy-MM")),
	DATE_FORMAT4(new SimpleDateFormat("yyyy/MM")),
	DATE_FORMAT5(new SimpleDateFormat("yyyy/MM/dd")),
	DATE_FORMAT6(new SimpleDateFormat("yyyyMMdd")),
	DATE_FORMAT7(new SimpleDateFormat("yyMMddHHmm")),
	DATE_FORMAT8(new SimpleDateFormat("yyyyMM")),;

	public SimpleDateFormat format;

	DateFormatEnum(SimpleDateFormat format) {
		this.format = format;
	}

	public static Date formatDate(String sourceDate) {
		if (sourceDate == null) {
			throw new NullPointerException();
		}
		Date parse = null;
		for (DateFormatEnum anEnum : DateFormatEnum.values()) {
			try {
				parse = anEnum.format.parse(sourceDate);
			} catch (ParseException e) {
				continue;
			}
			break;
		}
		if (parse == null) {
			throw new RuntimeException("不支持的时间格式");
		}
		return parse;
	}

	@Override
	public String toString() {
		return "DateFormatEnum{" + "format=" + format.toPattern() +
				'}';
	}
}

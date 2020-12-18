package cn.com.coding4fun.logging.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtils {

	public static final String YYYY_MM_DD = "yyyy-MM-dd HH:mm:ss";
	public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD);

	public static String format(LocalDateTime localDateTime) {
		return YYYY_MM_DD_HH_MM_SS_FORMATTER.format(localDateTime);
	}
}

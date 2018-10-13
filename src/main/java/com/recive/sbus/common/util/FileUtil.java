package com.recive.sbus.common.util;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class FileUtil {
	/**
	 * 获取转换后的文件路径 转换规则为： s年/月/日/时/时间戳 默认东八区
	 * 
	 * @param filename
	 * @return
	 */
	public static String getTransPath(String suffixName) {

		LocalDateTime nowTime = LocalDateTime.now();
		long l_file = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();// LocalDateTime转时间戳
																						// 默认东八区
		// 获取年月日
		int year = nowTime.getYear();
		int month = nowTime.getMonthValue();
		String sMonth = "" + month;
		if (month < 10) {
			sMonth = "0" + month;
		}
		int day = nowTime.getDayOfMonth();
		String sDay = "" + day;
		if (day < 10) {
			sDay = "0" + day;
		}

		return year + File.separator + sMonth + File.separator + sDay + File.separator + l_file + suffixName;
	}

	/**
	 * 根据时间戳获取文件路径 默认东八区
	 * 
	 * @param timestamp
	 *            suffixName
	 * @return
	 */
	public static String getFilePathByTimestamp(Long timestamp, String suffixName) {
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZoneId zone = ZoneId.of("+8");
		LocalDateTime time = LocalDateTime.ofInstant(instant, zone);

		int year = time.getYear();
		int month = time.getMonthValue();
		String sMonth = "" + month;
		if (month < 10) {
			sMonth = "0" + month;
		}
		int day = time.getDayOfMonth();
		String sDay = "" + day;
		if (day < 10) {
			sDay = "0" + day;
		}
		return year + File.separator + sMonth + File.separator + sDay + File.separator + timestamp + suffixName;
	}

	/**
	 * 根据文件名获取文件路径
	 * 
	 * @param filename
	 *            符合系统命名规则的文件名
	 * @return
	 */
	public static String getFilePathByFileName(String fileName) {
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String stamp = fileName.substring(0, fileName.indexOf("."));
		long timestamp = Long.parseLong(stamp);
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZoneId zone = ZoneId.of("+8");
		LocalDateTime time = LocalDateTime.ofInstant(instant, zone);

		int year = time.getYear();
		int month = time.getMonthValue();
		String sMonth = "" + month;
		if (month < 10) {
			sMonth = "0" + month;
		}
		int day = time.getDayOfMonth();
		String sDay = "" + day;
		if (day < 10) {
			sDay = "0" + day;
		}
		return year + File.separator + sMonth + File.separator + sDay + File.separator + timestamp + suffixName;
	}

	public static void main(String[] args) {
		System.out.println(getTransPath(".txt"));

		long timestamp = 1527509482165L;

		System.out.println(getFilePathByTimestamp(timestamp, ".exe"));
	}
}

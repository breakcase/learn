package com.recive.sbus.common.util;

public class StringUtil {

	/**
	 * 字符串赚数组
	 * 
	 * @param source
	 * @return
	 */
	public static String[] split(String source) {
		return source.split(",");
	}

	/**
	 * 字符串按指定分隔符转数组
	 * 
	 * @param source
	 * @param separator
	 * @return
	 */
	public static String[] split(String source, String regex) {
		return source.split(regex);
	}

	public static boolean isEmpty(String source) {
		if (null == source || source.length() == 0 || source.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(final String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}

		char firstChar = str.charAt(0);
		if (Character.isTitleCase(firstChar)) {
			// already capitalized
			return str;
		}

		return new StringBuilder(strLen).append(Character.toTitleCase(firstChar)).append(str.substring(1)).toString();
	}

}

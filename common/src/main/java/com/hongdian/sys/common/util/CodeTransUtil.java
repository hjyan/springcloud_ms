/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.sys.common.util;

/**
 *
 * @author win 10
 */
public class CodeTransUtil {

	/**
	 * 方法只适用于预警广播 unicode 编码 和 中文转换，实例：
	 * 1a你好 == 31 00 61 00 60 4F 7D 59
	 * @param unicode
	 * @return 
	 */
	public static String unicodeToCn(String unicode) {
		String returnStr = "";
		unicode = unicode.replace(" ", "");
		for (int i = 0; i < unicode.length(); i = i + 4) {
			String str = unicode.substring(i, i + 4);
			returnStr += (char) Integer.valueOf(reverseBeginAndEnd(str), 16).intValue();
		}
		return returnStr;
	}

	public static String cnToUnicode(String cn) {
		char[] chars = cn.toCharArray();
		String returnStr = "";
		for (int i = 0; i < chars.length; i++) {
			returnStr += reverseBeginAndEndWidthBlank(Integer.toString(chars[i], 16)) + " ";
		}
		return returnStr.substring(0, returnStr.length() - 1);
	}

	public static String reverseBeginAndEnd(String str) {
		String begin = str.substring(0, 2);
		String end = str.substring(2, 4);
		return end + begin;
	}

	public static String reverseBeginAndEndWidthBlank(String str) {
		String begin = str.substring(0, 2);
		if (str.length() == 2) {
			return begin + " " + "00";
		}
		String end = str.substring(2, 4);
		return end + " " + begin;
	}
}

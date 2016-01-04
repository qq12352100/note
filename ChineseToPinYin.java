package com.bkk.base;

import java.io.IOException;

public class ChineseToPinYin {
	/**
	 * 把中文转成Unicode码
	 * 
	 * @param str
	 * @return
	 */
	public String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	/**
	 * 判断是否为中文字符
	 * 
	 * @param c
	 * @return
	 */
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	// ---------------------------------------------------ASCII码转换为字符串
	public static char ascii2Char(int ASCII) {
		return (char) ASCII;
	}

	public static int char2ASCII(char c) {
		return (int) c;
	}

	public static String ascii2String(int[] ASCIIs) {// ASCII码转换为字符串
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ASCIIs.length; i++) {
			sb.append((char) ascii2Char(ASCIIs[i]));
		}
		return sb.toString();
	}

	public static int[] string2ASCII(String s) {// 字符串转换为ASCII码
		if (s == null || "".equals(s)) {
			return null;
		}

		char[] chars = s.toCharArray();
		int[] asciiArray = new int[chars.length];

		for (int i = 0; i < chars.length; i++) {
			asciiArray[i] = char2ASCII(chars[i]);
		}
		return asciiArray;
	}

	public static void showIntArray(int[] intArray, String delimiter) {
		for (int i = 0; i < intArray.length; i++) {
			System.out.print(intArray[i] + delimiter);
		}
	}

	public static void main(String[] args) throws IOException {
		String s = "好好学习！！!!——2016年1月4日";
		showIntArray(string2ASCII(s), " ");
		System.out.println();
		System.out.println(ascii2String(string2ASCII(s)));

	}

}

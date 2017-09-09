package com.spider.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Utils {
	/**
	 * 从字符串中获取数字
	 * 
	 * @param str
	 * @return
	 */
	public static int getNumFromStr(String str) {
		if (StringUtils.isNotBlank(str)) {
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			String numStr = m.replaceAll("").trim();
			return Integer.parseInt(numStr);
		} else {
			throw new NullPointerException("从字符串中获取数字字符串为空！");
		}
	}

	/**
	 * 从字符串中获取金额
	 * 
	 * @param str
	 * @return
	 */
	public static BigDecimal getMoneyFromStr(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.trim().replace("￥", "");
			return new BigDecimal(str);
		} else {
			throw new NullPointerException("从字符串中获取金额为空！");
		}
	}
}

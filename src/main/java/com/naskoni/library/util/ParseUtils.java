package com.naskoni.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParseUtils {

	private ParseUtils() {
	}

	public static boolean tryParseNumber(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean tryParseDate(String value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}

package com.practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordBoundary {
	public static void main(String[] args) {
		String regex = "\\b(\\d{3})(\\d{3})(\\d{4})\\b";

		Pattern p = Pattern.compile(regex);
		String source = "1234567890, 12345,  and  9876543210";

		Matcher m = p.matcher(source);

		while (m.find()) {
			System.out.println("Phone: " + m.group(0) + ", Formatted Phone:  ("
					+ m.group(1) + ") " + m.group(2) + "-" + m.group(3));
		}
	}
}

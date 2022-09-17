package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	// This Class is used to generate random data which is supplied as test input
	// data for Emp Creation and updation classes

	public static String empName() {

		String generatedString = RandomStringUtils.randomAlphabetic(2);

		return ("PariTester " + generatedString);

	}

	public static String empSal() {

		String generatedString = RandomStringUtils.randomNumeric(5);

		return (generatedString);
	}

	public static String empAge() {
		String generatedString = RandomStringUtils.randomNumeric(2);

		return (generatedString);

	}

}

package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;

	public static Response response;

	public String empID = "3";      // Hardcoded string value which is used in Get Single Emp & Update Emp classes

	public Logger logger;

	@BeforeClass
	public void setup() {

		logger = Logger.getLogger("Employee Rest API Automation Testing");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);

	}

}
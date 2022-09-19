package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC005_Delete_Employee extends TestBase {

	@BeforeClass
	void deleteEmployee() throws InterruptedException {

		logger.info("******** Started TC005_Delete_Employee************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.DELETE, "/delete/8");

		Thread.sleep(3000);

	}

	@Test
	void statusCodeTest() {
		logger.info("**********Checking Status code Test*****************");
		logger.info("Status code is =======>" + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test
	void deleteMessageTest() {
		logger.info("**********Checking Delete Message in the response body*****************");

		Assert.assertEquals(response.jsonPath().get("message"), "Successfully! Record has been deleted");
	}

	@AfterClass
	void tearDown() {

		logger.info("**************Completed executing TC005_Delete_Employee**************************************");
	}

}

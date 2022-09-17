package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employee extends TestBase {

	@BeforeClass
	void getSingleEmployee() throws InterruptedException {

		logger.info("****************Started TC002_Get_Single_Employee**************************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);

		Thread.sleep(3000);

	}

	@Test
	void statusCodeTest() {
		logger.info("***********Checking Status Code Test********************");

		int statusCode = response.getStatusCode();
		logger.info("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void validateSuccessStatus() {

		logger.info("****************checking success status in the response body******************************");

		String responseStatus = response.jsonPath().get("status");
		logger.info("Response status is ==> " + responseStatus);
		Assert.assertEquals(responseStatus, "success");

	}

	@Test
	void validateSuccessMessage() {

		logger.info("*********************checking success message in the response body*******************");

		Assert.assertEquals(response.jsonPath().get("message"), "Successfully! Record has been fetched.");

	}

	@Test
	void checkEmpId() {

		logger.info("******************Checking Employee ID ****************************************");

		logger.info("Emp ID is: " + response.jsonPath().get("data.id"));
		Assert.assertEquals(response.jsonPath().get("data.id").toString(), empID);

	}

	@AfterClass
	void tearDown() {

		logger.info("************ Completed TC002_Get_Single_Employee**********************");
	}

}

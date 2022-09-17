package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {

		logger.info("************ Started TC001_Get_All_Employees *****************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		Thread.sleep(5000);

	}

	@Test
	void statusCodeTest() {

		logger.info("********Checking Status Code test ***************");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==> " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void validateSuccessStatus() {

		logger.info("***********checking success status in response body ***************");

		String responseStatus = response.jsonPath().get("status");
		logger.info("Response status is: " + responseStatus);
		Assert.assertEquals(responseStatus, "success");

	}

	@Test
	void validateSuccessMessage() {

		logger.info("***********checking success message in response body ***************");

		String responseMessage = response.jsonPath().get("message");
		logger.info("Response message is: " + responseMessage);
		Assert.assertEquals(responseMessage, "Successfully! All records has been fetched.");

	}

	@AfterClass
	void tearDown() {

		logger.info("******* Completed TC001 **************");
	}

}

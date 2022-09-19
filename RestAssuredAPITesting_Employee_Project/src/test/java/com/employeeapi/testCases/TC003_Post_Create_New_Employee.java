package com.employeeapi.testCases;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_Create_New_Employee extends TestBase {

	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void newEmployee() throws InterruptedException {

		logger.info("*********  Started TC003_Post_Create_New_Employee********************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);

		httpRequest.header("content-type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(5000);

	}

	@Test
	void statusCodeTest() {

		logger.info("************checking status code****************");
		logger.info("Status code is ==> " + response.statusCode());

		Assert.assertEquals(response.statusCode(), 200);

	}

	@Test
	void validateSuccessStatus() {

		logger.info("*************** Checking Success status in response body**********************************");

		Assert.assertEquals(response.jsonPath().get("status"), "success");
	}

	@Test
	void empName() {

		logger.info("*******************checking Employee Name *******************************************");

		logger.info("Created Employee Name is ====> " + response.jsonPath().get("data.name"));

		Assert.assertEquals(response.jsonPath().get("data.name"), empName);
	}

	@AfterClass
	void tearDown() {
		logger.info("******************Completed executing TC003_Post_Create_New_Employee**************************");
	}

}

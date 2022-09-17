package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_PUT_Update_Existing_Employee extends TestBase {

	String empName = RestUtils.empName();
	String empAge = RestUtils.empAge();
	String empSal = RestUtils.empSal();

	@BeforeClass
	void updateEmployee() throws InterruptedException {

		logger.info("**********Started executing TC004_PUT_Update_Existing_Employee****************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);

		httpRequest.header("content-type", "application/json");
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.PUT, "/update/" + empID);

		Thread.sleep(3000);

	}

	@Test
	void statusCodeTest() {
		logger.info("*******Checking status code*********");

		logger.info("Status code is ==> " + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test
	void updatedEmpNameTest() {

		logger.info("*******Checking Updated Employee Name*********");

		logger.info("Updated Employee Name is ====> " + response.jsonPath().get("data.name"));
		Assert.assertEquals(response.jsonPath().get("data.name"), empName);

	}

	@AfterClass
	void tearDown() {

		logger.info("****************Completed executing TC004_PUT_Update_Existing_Employee *********************");
	}

}

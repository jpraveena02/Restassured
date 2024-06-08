package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import Constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import listeners.TestListener;
import Utils.DataUtils;
import Utils.RestUtils;
import Utils.UserPojo;

@Listeners(TestListener.class)
public class APIPortalTest extends BaseTest {
	@BeforeTest
	public void intialize() throws IOException {
		RestAssured.baseURI = DataUtils.getTestData("$.uri").toString();
	}

	@Test(priority = 1)
	public void loginTest_TC01() throws IOException {
		ExtentTest test = BaseTest.threadExtentTest.get();
		Object payload = DataUtils.getTestData("$.payloads.login");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String endpoint = DataUtils.getTestData("$.endpoints.login").toString();
		Response loginRes = RestUtils.postReq(headers, payload, endpoint);
		RestUtils.validateSchema(loginRes, FileConstants.LOGIN_SCHEMA_FILE_PATH);
		test.log(Status.PASS, "Login in with valid credentials response schema validated");
		assertEquals(loginRes.statusCode(), 201);
		test.log(Status.PASS, "Login with valid credentials response status code matched");
		logger.info("Pass, Login with valid credentials validated");
	}

	@Test(priority = 2)
	public void loginTestWithWrongCredentials_TC02() throws IOException {
		ExtentTest test = BaseTest.threadExtentTest.get();
		Object payload = DataUtils.getTestData("$.payloads.loginwithwrong");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String endpoint = DataUtils.getTestData("$.endpoints.login").toString();
		Response loginRes = RestUtils.postReqInvalid(headers, payload, endpoint);
		RestUtils.validateSchema(loginRes, FileConstants.LOGIN_WRONGCREDENTIAL_SCHEMA_PATH);
		test.log(Status.PASS, "Login with wrong credentials schema validated");
		Assert.assertEquals(loginRes.statusCode(), 401);
		test.log(Status.PASS, "Login with wrong credentials status code matched");
		logger.info("Pass, Login with wrong credentials validated");
	}

	@Test(priority = 3)
	public void loginTestWithEmptyCredentials_TC03() throws IOException {
		ExtentTest test = BaseTest.threadExtentTest.get();
		Object payload = DataUtils.getTestData("$.payloads.loginwithempty");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String endpoint = DataUtils.getTestData("$.endpoints.login").toString();
		Response loginRes = RestUtils.postReqInvalid(headers, payload, endpoint);
		RestUtils.validateSchema(loginRes, FileConstants.LOGIN_WRONGCREDENTIAL_SCHEMA_PATH);
		test.log(Status.PASS, "Login with empty credentials schema validated");
		Assert.assertEquals(loginRes.statusCode(), 401);
		test.log(Status.PASS, "Login with empty credentials statuscode matched");
		logger.info("Pass, Login with empty credentilas");
	}

	@Test(priority = 4)
	public void addUSer_TC04() throws IOException {
		ExtentTest test = BaseTest.threadExtentTest.get();
		String payload = requestPayload.addUserPayload();
		HashMap<String, String> addUserHeaders = new HashMap<String, String>();
		addUserHeaders.put("Content-Type", "application/json");
		addUserHeaders.put("token", BaseTest.generateToken());
		String endpoint = DataUtils.getTestData("$.endpoints.adddata").toString();
		Response addUserRes = RestUtils.postReq(addUserHeaders, payload, endpoint);
		RestUtils.validateSchema(addUserRes, FileConstants.ADD_USER_SCHEMA_FILE_PATH);
		test.log(Status.PASS, "Add user schema validated");
		// addUserRes.prettyPrint();
		Assert.assertEquals(addUserRes.statusCode(), 201);
		test.log(Status.PASS, "Add user status code matched");
		logger.info("Pass, Add user response validated");

	}

	
	@Test(priority = 5)
	public void getUser_TC05() throws IOException {

		ExtentTest test = BaseTest.threadExtentTest.get();
		ObjectMapper objMapper = new ObjectMapper();

		HashMap<String, String> getHeaders = new HashMap<String, String>();
		getHeaders.put("Content-Type", "application/json");
		getHeaders.put("token", BaseTest.generateToken());
		String endpoint = DataUtils.getTestData("$.endpoints.getdata").toString();

		Response getUserResponse = RestUtils.getReq(getHeaders, endpoint);
		// getUserResponse.prettyPrint();

		logger.info("Full JSON Response: " + getUserResponse.asString());
		JsonPath jsonPath = getUserResponse.jsonPath();
		Map<String, Object> firstElement = jsonPath.getMap("[0]");

		logger.info("Extracted First Element: " + firstElement);
		String firstElementJsonString = objMapper.writeValueAsString(firstElement);

		logger.info("First element JSON string: " + firstElementJsonString);

		// Deserialize JSON to UserPojo
		UserPojo getUser = objMapper.convertValue(firstElement, Utils.UserPojo.class);
		logger.info(getUser.getAccountno());
		logger.info("getUser:" + getUser);

		assertThat(getUser.getAccountno(), is(notNullValue()));
		assertThat(getUser.getAccountno(), matchesPattern("TA-\\d+"));

		/*
		 * RestUtils.validateFirstElementOfSchema(getUserResponse,
		 * FileConstants.GET_USER_SCHEMA_FILE_PATH);
		 * 
		 * test.log(Status.PASS, "addUserschema validated");
		 */

		Assert.assertEquals(getUserResponse.statusCode(), 200);
		test.log(Status.PASS, "Get user response status code matched");
		logger.info("Pass, Get user response validated");

	}

	@Test(priority = 6)
	public void updateUser_TC06() throws IOException {
		ExtentTest test = BaseTest.threadExtentTest.get();

		HashMap<String, String> updateHeaders = new HashMap<String, String>();
		updateHeaders.put("Content-Type", "application/json");
		updateHeaders.put("token", BaseTest.generateToken());
		String payload = requestPayload.updateUserPayload();
		String endpoint = DataUtils.getTestData("$.endpoints.updatedata").toString();
		Response updateUserRes = RestUtils.putReq(updateHeaders, payload, endpoint);
		// updateUserRes.prettyPrint();
		RestUtils.validateSchema(updateUserRes, FileConstants.UPDATE_USER_SCHEMA_FILE_PATH);
		test.log(Status.PASS, "Update user response Schema validated");
		Assert.assertEquals(updateUserRes.statusCode(), 200);
		test.log(Status.PASS, "Update user status code matched");
		logger.info("Pass, Update user response validated");
	}

	@Test(priority = 7)
	public void deleteUser_TC07() throws IOException {
		ExtentTest test = BaseTest.threadExtentTest.get();

		HashMap<String, String> delHeaders = new HashMap<String, String>();
		delHeaders.put("Content-Type", "application/json");
		delHeaders.put("token", BaseTest.generateToken());

		String payload = requestPayload.deleteUserPayload();
		String endpoint = DataUtils.getTestData("$.endpoints.deletedata").toString();
		Response deleteUserRes = RestUtils.deleteReq(delHeaders, payload, endpoint);
		// deleteUserRes.prettyPrint();
		RestUtils.validateSchema(deleteUserRes, FileConstants.DELETE_USER_SCHEMA_FILE_PATH);
		test.log(Status.PASS, "Delete user Response schema vaidated");
		Assert.assertEquals(deleteUserRes.statusCode(), 200);
		test.log(Status.PASS, "Delete user response status code matched");
		logger.info("Pass, Delete user response validated");

	}

	@Test(priority = 8)
	public void logout_TC08() throws IOException {
		ExtentTest test = BaseTest.threadExtentTest.get();

		HashMap<String, String> logoutHeaders = new HashMap<String, String>();
		logoutHeaders.put("Content-Type", "application/json");
		logoutHeaders.put("token", BaseTest.generateToken());

		Object payload = DataUtils.getTestData("$.payloads.logout");
		String endpoint = DataUtils.getTestData("$.endpoints.logout").toString();

		Response logoutUserRes = RestUtils.postReqLogout(logoutHeaders, payload, endpoint);
		// logoutUserRes.prettyPrint();
		RestUtils.validateSchema(logoutUserRes, FileConstants.LOGOUT_USER_SCHEMA_FILE_PATH);
		test.log(Status.PASS, "Logout user response schema vaidated");
		Assert.assertEquals(logoutUserRes.statusCode(), 200);
		test.log(Status.PASS, "Logout user response status code matched");
		logger.info("Pass, Logout user response Validated");
	}
}
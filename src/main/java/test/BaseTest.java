package test;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utils.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;



public class BaseTest {
	static String token = null;
	
	static Logger logger = LogManager.getLogger(BaseTest.class);

	public static ThreadLocal<ExtentTest> threadExtentTest = new ThreadLocal<ExtentTest>();
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void doConfiguration() {
		extent = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(Constants.FileConstants.REPORT_PATH);
		extent.attachReporter(sparkReporter);
	}

	@AfterSuite
	public void tearDownConfig() {
		extent.flush();
	}

	public static String generateToken() {
		String token = "";
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String payload = "{\"username\": \"jpraveenarao@tekarch.com\", \"password\": \"Admin123\"}";
		String path = "/login";

		Response loginRes = Utils.RestUtils.postReq(headers, payload, path);
		token = loginRes.jsonPath().get("[0].token");
		return token;
	}
	protected RequestPayload requestPayload=new RequestPayload();
	

}

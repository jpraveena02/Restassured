package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import test.BaseTest;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult name) {
		BaseTest.test = BaseTest.extent.createTest(name.getName());
		BaseTest.threadExtentTest.set(BaseTest.test);
	}

	@Override
	public void onFinish(ITestContext context) {
		BaseTest.extent.flush();
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		BaseTest.threadExtentTest.get()
				.pass(MarkupHelper.createLabel("PASSED: " + result.getName(), ExtentColor.GREEN));

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		BaseTest.threadExtentTest.get()
				.skip(MarkupHelper.createLabel("SKIPPED: " + result.getName(), ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//BaseTest.threadExtentTest.get().addScreenCaptureFromPath(CommonUtils.captureScreenshot(BaseTest.getDriver()));
		BaseTest.threadExtentTest.get().fail(MarkupHelper.createLabel("FAILED: " + result.getName(), ExtentColor.RED));
		
	}
}

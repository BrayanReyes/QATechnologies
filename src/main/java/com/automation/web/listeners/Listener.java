package com.automation.web.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Class listeners.
 * 
 * @author july.moreno
 */

public class Listener implements ITestListener {

	private Logger log = Logger.getLogger(Listener.class);

	@Override
	public void onFinish(ITestContext context) {
		log.info("THE TEST FINISHED");

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("onTestFailedButWithinSuccessPercentage");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("THE TEST FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("onTestSkipped");

	}

	@Override
	public void onTestStart(ITestResult result) {
		log.info("THE TEST STARTED");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("THE TEST PASSED");
	}

}

package org.bookingSuite.web.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Listener class
 * @author: july.moreno
 * @version: 07/06/2020
 */


public class Listener implements ITestListener {

    private Logger log = Logger.getLogger(Listener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("THE TEST STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("THE TEST PASSED\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("THE TEST FAILED\n");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("THE TEST WAS SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("THE TEST GROUP STARTED\n");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("THE TEST GROUP FINISHED\n");
    }
}

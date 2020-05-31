package com.espn.test.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    private Logger log = Logger.getLogger(Listener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("[Listener] onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("[Listener] onTestSuccess");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("[Listener] onTestFailure");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("[Listener] onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("[Listener] onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("[Listener] onStart");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("[Listener] onFinish");
    }
}
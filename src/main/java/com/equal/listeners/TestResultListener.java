package com.equal.listeners;

import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.equal.common.Driver;
import com.equal.common.TestBase;
import com.equal.logging.CustomReport;

public class TestResultListener implements ITestListener {

    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext arg0) {
        System.out.println("INSIDE NEW LISTENER");

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    public void onTestFailure(ITestResult arg0) {
        StackTraceElement[] stackTrace = arg0.getThrowable().getStackTrace();
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            builder.append(stackTraceElement.toString() + "<br>");
        }
//        try {//This is done for debug purpose
//        System.out.println("Fail Page Info: Current URL ="+Driver.getCurrentDriver().getCurrentUrl());
//        System.out.println("Fail Page Info: Current Source ="+Driver.getCurrentDriver().getPageSource());
//        } finally{
//        	System.out.println("Fail Page Info: END");
//        }
        
        TestBase.reports.writeIntoFile(Driver.getCurrentDriver(), arg0.getTestClass().getRealClass().getSimpleName(), arg0.getTestClass().getRealClass().getSimpleName(), "Exception", arg0.getThrowable().getMessage(), CustomReport.fail, builder.toString(),
                new Date(System.currentTimeMillis()).toGMTString());
    }

    public void onTestSkipped(ITestResult arg0) {
        CustomReport.skipCount = CustomReport.skipCount + 1;
        CustomReport.skippedTests = CustomReport.skippedTests + 1;

    }

    public void onTestStart(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    public void onTestSuccess(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

}

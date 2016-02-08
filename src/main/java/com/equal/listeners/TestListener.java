package com.equal.listeners;

import java.util.ArrayList;
import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.logging.CustomReport;

public class TestListener implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getMethodName();
        if (methodName != null && !exceptionsList().contains(methodName)) {
            Logger.logDebug("START TEST CASE: " + methodName);
            Logger.setTest(true);
        }
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        Logger.setTest(false);
        String methodName = iTestResult.getMethod().getMethodName();
        if (methodName != null && !exceptionsList().contains(methodName)) {
            Logger.logDebug("END TEST CASE: " + methodName);

            ///////////////////////////////////////////

        }

    }


    private List<String> exceptionsList() {
        List<String> exceptions = new ArrayList<String>();
        exceptions.add("start");
        exceptions.add("end");
        exceptions.add("createUsers");
        exceptions.add("printClassName");
        exceptions.add("printClassNameBeforeTest");
        return exceptions;
    }
}

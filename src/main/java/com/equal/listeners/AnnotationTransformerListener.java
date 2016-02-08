package com.equal.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.equal.common.Config;
import com.equal.common.Logger;

public class AnnotationTransformerListener implements org.testng.internal.annotations.IAnnotationTransformer {


    public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
        System.out.println("Inside Annotation transformer");
        Logger.logInfo("Inside Annotation transformer");
        if (Config.getProperty("skip.tests").contains(arg3.getDeclaringClass().getSimpleName())) {
            Logger.logInfo("Skip test case");
            arg0.setEnabled(false);
        } else {
            Logger.logInfo("Test is not skipped");

        }
    }
}

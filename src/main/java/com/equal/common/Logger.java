package com.equal.common;

import java.io.File;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.equal.logging.CustomReport;
import com.equal.logging.LogMarkers;

public class Logger {

    private static final org.slf4j.Logger logger = LoggerFactory
            .getLogger("WD");

    private static String tabPrint = "";
    private static LogLevel logLevel;
    private static boolean isTest = false;
    private static boolean isHidden = false;
    private static HashMap<String, StringBuilder> logs = new HashMap<String, StringBuilder>();
    private static HashMap<String, Boolean> states = new HashMap<String, Boolean>();

    public enum LogLevel {

        INFO(1), PASS(2), WARNING(3), ENV(4), FAIL(5), ERROR(6);

        public final int level;

        LogLevel(int logLevel) {
            level = logLevel;
        }
    }

    public static String makeScreenshot(String fileName) {
        fileName = fileName.replaceAll(" ", "_").replaceAll(":", "_");
        Logger.logInfo("Screenshot name =" + fileName);
        try {
            if (Driver.getCurrentDriver().getClass().getSimpleName()
                    .contains("Remote")
                    ) {
                return null;
            }

            File screenshot = ((TakesScreenshot) Driver.getCurrentDriver())
                    .getScreenshotAs(OutputType.FILE);
            File outputFile = new File("LoggerScreenshots/" + fileName + ".jpg");

            FileUtils.copyFile(screenshot, outputFile);

            return outputFile.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void logSubOpen() {
        tabPrint += "       ";
    }

    public static void logSubClose() {
        if (tabPrint.length() >= 7) {
            tabPrint = tabPrint.substring(0, tabPrint.length() - 7);
        } else {
            System.out
                    .println("You need call function 'logSubOpen()' before call  function 'logSubClose()'");
        }

    }

    public static void logPass(String message) {
        logMessage(message, "PASS:");
        verifyLogLevel(LogLevel.PASS);
    }

    public static void logFail(String message) {
        logMessage(message, "FAIL:");
        verifyLogLevel(LogLevel.FAIL);
        if (!Driver.getCurrentDriver().getClass().getSimpleName()
                .contains("Remote")
                && !Driver.getCurrentDriver().getClass().getSimpleName()
                .contains("Chrome")) {
        }
        if (isTest) {
            hideHtmlDetails();
        }
    }

    public static void logInfo(String message) {
        logMessage(message, "INFO:");
        verifyLogLevel(LogLevel.INFO);
    }

    public static void logEnvironment(String message) {
        logMessage(message, "ENVIRONMENT:");
        verifyLogLevel(LogLevel.ENV);
    }

    public static void logError(String message) {
        logMessage(message, "ERROR:");
        verifyLogLevel(LogLevel.ERROR);
        if (!Driver.getCurrentDriver().getClass().getSimpleName()
                .contains("Remote")
                && !Driver.getCurrentDriver().getClass().getSimpleName()
                .contains("Chrome")) {
        }
        if (isTest) {
            hideHtmlDetails();
        }
    }

    public static void logWarning(String message) {
        logMessage(message, "WARNING:");
        verifyLogLevel(LogLevel.WARNING);
    }

    public static void logSkipped(String message) {
        logMessage(message, "SKIPPED:");
    }

    public static void logDebug(String message) {
        logMessage(message, "DEBUG:");
    }

    private static void logMessage(String message, String level) {
        if (level.equalsIgnoreCase("PASS:")) {
            logger.info(LogMarkers.MARKER_PASS, message);
        }
        if (level.equalsIgnoreCase("WARNING:")) {
            logger.warn(LogMarkers.MARKER_WARNING, message);
        }
        if (level.equalsIgnoreCase("ERROR:")) {
            logger.error(LogMarkers.MARKER_ERROR, message);
        }
        if (level.equalsIgnoreCase("FAIL:")) {
            logger.error(LogMarkers.MARKER_FAIL, message);
        }
        if (level.equalsIgnoreCase("DEBUG:")) {
            logger.debug(LogMarkers.MARKER_DEBUG, message);
        }
        if (level.equalsIgnoreCase("INFO:")) {
            logger.info(LogMarkers.MARKER_INFO, message);
        }

        Reporter.log(htmlMessage("", level, message));
        if (isTest) {
            hideHtmlDetails();
        }

    }

    public static void hideHtmlDetails() {
        if (!isHidden) {
            Reporter.log("<script type='text/javascript'>"
                    + "$(document).ready(function(){"
                    + "$('.debug').hide();"
                    + "$('.reporter-method-div').first().before('<input type=\"checkbox\" class=\"showDebug\"/><label for=\"showDebug\">Show debug details</label>');"
                    + "if(typeof(imagePreview) == \"function\"){"
                    + "imagePreview();"
                    + "}"
                    + "$('.reporter-method-output-div').hide();"
                    + "$('.showDebug').click( function() {"
                    + "debugOption();"
                    + "});"
                    + "$('.reporter-method-div').click( function() {"
                    + "$(this).find('.reporter-method-output-div').slideToggle('slow');"
                    + "debugOption();"
                    + "});"
                    + "});"
                    + "this.debugOption = function() { "
                    + "$('.showDebug').is(':checked') ? $('.debug').slideDown('slow') : $('.debug').slideUp('slow');"
                    + "};" + "</script>");
            isHidden = true;
        }
    }

    private static String htmlMessage(String date, String level, String message) {
        String pattern = "<div class=\"{4}\"><b><font color = \"{0}\">{1} {2} {3}</font></b></div>";
        if (level.equalsIgnoreCase("PASS:")) {
            return MessageFormat.format(pattern, "green", date, level, message,
                    "pass");
        }
        if (level.equalsIgnoreCase("ERROR:") || level.equalsIgnoreCase("FAIL:")) {
            return MessageFormat.format(pattern, "red", date, level, message,
                    "error");
        }
        if (level.equalsIgnoreCase("SKIPPED:")) {
            return MessageFormat.format(pattern, "yellow", date, level,
                    message, "skipped");
        }
        if (level.equalsIgnoreCase("DEBUG:")) {
            return MessageFormat.format(pattern, "LightGray", date, level,
                    message, "debug");
        } else {
            return MessageFormat.format(pattern, "black", date, level, message,
                    "other");
        }
    }

    public static LogLevel getLogLevel() {
        return logLevel;
    }

    public static boolean isTest() {
        return isTest;
    }

    public static void setTest(boolean test) {
        isTest = test;
    }

    private static void verifyLogLevel(LogLevel logLevel) {
        if (Logger.logLevel == null) {
            Logger.logLevel = logLevel;
        } else if (Logger.logLevel.level < logLevel.level) {
            Logger.logLevel = logLevel;
        }
    }

    public static void updateLog() {
        logs.put(Thread.currentThread().getId() + "", new StringBuilder());
        states.put(Thread.currentThread().getId() + "", new Boolean(true));
    }

    public static void append(String log) {
        logs.get(Thread.currentThread().getId() + "").append(log);
    }

    public static String getLog() {
        if (logs.get(Thread.currentThread().getId() + "") == null) {
            return "";
        } else {
            return logs.get(Thread.currentThread().getId() + "").toString();
        }
    }

    public static void setState(boolean state) {
        states.put(Thread.currentThread().getId() + "", new Boolean(state));
    }

    public static Boolean getState() {
        if (logs.get(Thread.currentThread().getId() + "") == null) {
            return new Boolean(false);
        } else {
            return states.get(Thread.currentThread().getId() + "");
        }
    }

}

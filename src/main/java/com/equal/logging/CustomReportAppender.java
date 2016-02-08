package com.equal.logging;

import java.util.Date;

import com.equal.common.Asserter;
import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.common.TestBase;
import com.equal.logging.CustomReport;

import ch.qos.logback.core.AppenderBase;

public class CustomReportAppender extends AppenderBase {

    @Override
    protected void append(Object arg0) {
        try {
            if (Logger.isTest()) {
                if (Asserter.isAsserter) {
                    if (arg0.toString().contains("ERROR") || arg0.toString().contains("FAIL")) {
                        TestBase.reports.writeIntoFile(Driver.getCurrentDriver(), "not implemented1", "assertion",
                                arg0.toString(), CustomReport.fail, arg0.toString(), "not implemented2",
                                new Date(System.currentTimeMillis()).toGMTString());
                    } else {
                        TestBase.reports.writeIntoFile(Driver.getCurrentDriver(), "not implemented1", "assertion",
                                arg0.toString(), CustomReport.pass, arg0.toString(), "not implemented2",
                                new Date(System.currentTimeMillis()).toGMTString());
                    }
                    return;
                }

                TestBase.reports.writeIntoFile(Driver.getCurrentDriver(), "not implemented1", "action", arg0.toString(),
                        CustomReport.pass, arg0.toString(), "not implemented2",
                        new Date(System.currentTimeMillis()).toGMTString());
            }
        } catch (Exception e) {
        }
    }

}

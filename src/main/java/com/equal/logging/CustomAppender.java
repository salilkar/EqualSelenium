package com.equal.logging;

import com.equal.common.Logger;

import ch.qos.logback.core.AppenderBase;

public class CustomAppender extends AppenderBase {

    @Override
    protected void append(Object arg0) {
        try {
            Logger.append(arg0.toString());
        } catch (Exception e) {
        }
    }

}

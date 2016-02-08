package com.equal.common;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotUtilities {

    public void sendKeysCombo(String stringToSend) {

        StringSelection stringSelection = new StringSelection(stringToSend);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(5000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(500);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public void pressEnter() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(2000);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

}

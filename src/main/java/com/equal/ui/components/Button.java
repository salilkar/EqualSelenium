package com.equal.ui.components;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.localization.Localization;
import com.equal.ui.components.NavigationLink;


public class Button extends NavigationLink {

    public Button() {
    }

    public Button(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public void submit() {
        if (wrappedElement != null) {
            highlightElement();
            wrappedElement.submit();
            Logger.logInfo(Localization.getMessage(Localization.BUTTON_SUBMIT, name, page));
        } else
            Logger.logError(Localization.getMessage(Localization.NO_BUTTON, name));
    }

    public boolean checkboxIsChecked() {
        highlightElement();
        JavascriptExecutor exec = (JavascriptExecutor) Driver.getCurrentDriver();
        Boolean bool = (Boolean) exec.executeScript("return $(arguments[0]).is(\":checked\");", wrappedElement);
        return bool.booleanValue();
    }

}

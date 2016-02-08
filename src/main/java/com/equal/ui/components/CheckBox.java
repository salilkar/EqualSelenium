package com.equal.ui.components;

import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.ui.components.AbstractPageElement;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CheckBox extends AbstractPageElement {

    public CheckBox(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public boolean isSelected() {
        return wrappedElement.isSelected();
    }

    public void select() {
        if (!isSelected()) {
            wrappedElement.click();
            Logger.logInfo(String.format("Select %s checkbox on %s page", name, page));
        }
    }

    public void deselect() {
        if (isSelected()) {
            wrappedElement.click();
            Logger.logInfo(String.format("Deselect %s checkbox on %s page", name, page));
        }
    }

    public void set(boolean flag) {
        if (flag) {
            select();
        } else {
            deselect();
        }
    }

    public WebElement getLabel() {
        try {
            return Driver.getCurrentDriver()
                    .findElementByXPath("following-sibling::label");
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getLabelText() {
        WebElement label = getLabel();
        return label == null ? null : label.getText();
    }

    public String getText() {
        return getLabelText();
    }
}

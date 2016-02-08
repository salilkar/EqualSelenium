package com.equal.ui.components;

import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.localization.Localization;
import com.equal.ui.components.AbstractPageElement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavigationLink extends AbstractPageElement {

    public NavigationLink() {
        // TODO Auto-generated constructor stub
    }

    public NavigationLink(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public void click() {
        if (wrappedElement != null) {

            highlightElement();
            if (wrappedElement.getTagName().equals("a")) {
                if (Driver.getCurrentDriver().getClass().getSimpleName()
                        .contains("Explorer")) {
                	try {
                		  Driver.getCurrentDriver().executeScript(
                                  "$(arguments[0]).focus();", wrappedElement);
					} catch (Exception e) {
					}
                    wrappedElement.click();
                    Logger.logDebug("we passed to:"
                            + Driver.getCurrentDriver().getCurrentUrl());
                    Logger.logInfo(Localization.getMessage(Localization.CLICK_BUTTON,
                            name, page));
                    return;
                }
                Driver.getCurrentDriver().executeScript(
                        "$(arguments[0]).focus();", wrappedElement);
                wrappedElement.click();
            } else {
                if (wrappedElement.getTagName().equals("input") || wrappedElement.getTagName().equals("button")) {
                    if (Driver.getCurrentDriver().getClass().getSimpleName()
                            .contains("Remote")) {
                        Driver.getCurrentDriver().executeScript(
                                "$(arguments[0]).focus().trigger('click');",
                                wrappedElement);
                    } else {
                        wrappedElement.click();
                    }
                } else {
                    wrappedElement.click();
                }
            }
            Logger.logDebug("we passed to:"
                    + Driver.getCurrentDriver().getCurrentUrl());
            Logger.logInfo(Localization.getMessage(Localization.CLICK_BUTTON,
                    name, page));
        } else
            Logger.logError(Localization.getMessage(Localization.NO_BUTTON,
                    name));
    }

    public void doubleClick() {
        Actions act = new Actions(Driver.getCurrentDriver());
        if (wrappedElement != null) {

            highlightElement();
            if (wrappedElement.getTagName().equals("a")) {
                if (Driver.getCurrentDriver().getClass().getSimpleName()
                        .contains("Explorer")) {
                    act.moveToElement(wrappedElement).build().perform();
                    act.doubleClick().build().perform();
                    return;
                }
                Driver.getCurrentDriver().executeScript(
                        "$(arguments[0]).focus();", wrappedElement);
                act.moveToElement(wrappedElement).build().perform();
                act.doubleClick().build().perform();
            } else {
                if (wrappedElement.getTagName().equals("input") || wrappedElement.getTagName().equals("button")) {
                    if (Driver.getCurrentDriver().getClass().getSimpleName()
                            .contains("Remote")) {
                        Driver.getCurrentDriver().executeScript(
                                "$(arguments[0]).focus().trigger('click');",
                                wrappedElement);
                    } else {
                        act.moveToElement(wrappedElement).build().perform();
                        act.doubleClick().build().perform();
                    }
                } else {
                    act.moveToElement(wrappedElement).build().perform();
                    act.doubleClick().build().perform();
                }
            }
            Logger.logDebug("we passed to:"
                    + Driver.getCurrentDriver().getCurrentUrl());
            Logger.logInfo(Localization.getMessage(Localization.CLICK_BUTTON,
                    name, page));
        } else
            Logger.logError(Localization.getMessage(Localization.NO_BUTTON,
                    name));
    }

    public void clickJquery() {
        if (wrappedElement != null) {

            highlightElement();
            Driver.getCurrentDriver().executeScript(
                    " return $(arguments[0]).focus().trigger('click');",
                    wrappedElement);

            Logger.logDebug("we passed to:"
                    + Driver.getCurrentDriver().getCurrentUrl());
            Logger.logInfo(Localization.getMessage(Localization.CLICK_JQUERY,
                    name, page));
        } else
            Logger.logError(Localization.getMessage(Localization.NO_BUTTON,
                    name));
    }
}

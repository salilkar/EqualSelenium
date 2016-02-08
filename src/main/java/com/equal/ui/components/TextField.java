package com.equal.ui.components;

import org.openqa.selenium.WebElement;

import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.localization.Localization;
import com.equal.ui.components.AbstractPageElement;

public class TextField extends AbstractPageElement {
    public TextField() {
        // constructor stub
    }

    public TextField(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public void sendText(String text) {
        if (wrappedElement != null) {
            highlightElement();
            if (!(Driver.getCurrentDriver().getClass().getSimpleName()
                    .contains("Remote") || Driver.getCurrentDriver().getClass().getSimpleName()
                    .contains("Explorer"))) {
            }
            clear();
            if (!Driver.getCurrentDriver().getClass().getSimpleName()
                    .contains("Remote") && !Driver.getCurrentDriver().getClass().getSimpleName()
                    .contains("Selendroid")) {
                wrappedElement.sendKeys(text);
                Logger.logInfo(Localization.getMessage(
                        Localization.INPUT_SET_VALUE, text, name, page));
                return;

            } else {
                Driver.getCurrentDriver().executeScript(
                        "$(arguments[0]).val('" + text + "').change();", wrappedElement);
                Logger.logInfo(Localization.getMessage(
                        Localization.INPUT_SET_VALUE, text, name, page));
                return;
            }

        } else
            Logger.logError(Localization.getMessage(Localization.NO_INPUT,
                    name, page));
    }

    public void sendTextJquery(String text) {
        Driver.getCurrentDriver().executeScript(
                "$(arguments[0]).val('" + text + "');", wrappedElement);
    }

    public void clear() {
        if (wrappedElement != null) {
            wrappedElement.clear();
            Logger.logDebug("Clear element");
        } else
            Logger.logError(Localization.getMessage(Localization.NO_INPUT,
                    name, page));
    }

    public void clearJquery() {
        Driver.getCurrentDriver()
                .executeScript(
                        "$(arguments[0]).unmask().attr(\"value\",\"\").val(\"\").mask(\"999-999-999\");",
                        wrappedElement);
    }
}

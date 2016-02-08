package com.equal.ui.components;

import org.openqa.selenium.WebElement;

import com.equal.common.Logger;
import com.equal.localization.Localization;

public class TextContainer extends AbstractPageElement {
    public TextContainer() {
        // TODO Auto-generated constructor stub
    }

    public TextContainer(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public String getText() {
        if (wrappedElement != null) {
            highlightElement();
//            if (wrappedElement.getTagName().equalsIgnoreCase("input")) {
//				return wr
//			}
            String containerValue = wrappedElement.getText();
//            wrappedElement.get

            Logger.logDebug(Localization.getMessage(Localization.TEXT_CONTAINER_GET, containerValue, name, page));
            return wrappedElement.getText();
        } else
            Logger.logError(Localization.getMessage(Localization.NO_TEXT_CONTAINER, name, page));
        return null;
    }
}

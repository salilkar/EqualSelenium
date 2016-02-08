package com.equal.ui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.localization.Localization;
import com.equal.ui.components.AbstractPageElement;

public class SelectorBox extends AbstractPageElement {
    public SelectorBox() {
        // TODO Auto-generated constructor stub
    }

    public SelectorBox(WebElement wrappedElement, String name, String page) {
        super(wrappedElement, name, page);
    }

    public void selectRandomValue() {
        if (wrappedElement != null) {
            highlightElement();
            String value = "";
            Select select = new Select(wrappedElement);
            Integer size = select.getOptions().size();
            Random random = new Random();
            Integer index = random.nextInt(size - 2) + 1;
            try {
                select.selectByIndex(index);
                value = select.getFirstSelectedOption().getText();
                Logger.logInfo(Localization.getMessage(
                        Localization.SELECT_RANDOM, value, name, page));
            } catch (Exception e) {
                // log
            }
        } else
            Logger.logError(Localization.getMessage(Localization.NO_SELECT,
                    name, page));
    }

    public void selectValue(String valueToSelect) {
        boolean p = false;
        if (wrappedElement != null) {

            if (Driver.getCurrentDriver().getClass().getSimpleName()
                    .contains("Remote") || Driver.getCurrentDriver().getClass().getSimpleName()
                    .contains("Selendroid")) {
                try {
                    Driver.getCurrentDriver().executeScript(
                            "$(arguments[0].options).filter(function() {return $(this).text() == '"
                                    + valueToSelect
                                    + "';}).prop('selected', true);$(arguments[0]).change();",
                            wrappedElement);
                } catch (Exception e) {
                    Driver.getCurrentDriver().executeScript(
                            "$(arguments[0].options).first().prop('selected', true);$(arguments[0]).change();",
                            wrappedElement);

                }
                return;
            }
            highlightElement();

            Select select = new Select(wrappedElement);
            List<WebElement> options = select.getOptions();
//			for (WebElement webElement : options) {
//				System.out.println(webElement.getText());
//			}

            for (WebElement option : options) {

                if (option.getText().equals(valueToSelect)) {
                    if (Driver.getCurrentDriver().getClass().getSimpleName().contains("SelendroidDriver") || Driver.getCurrentDriver().getClass().getSimpleName().contains("Remote")) {
                        Driver.getCurrentDriver().executeScript(
                                "$(arguments[0]).prop('selected', true).change();return;",
                                option);
                        return;
                    } else
                        option.click();
                    Logger.logInfo(Localization.getMessage(
                            Localization.SELECT_VALUE, valueToSelect, name,
                            page));
                    p = true;
                    break;
                } else {

//					if (Driver.getCurrentDriver().getClass().getSimpleName().contains("SelendroidDriver")) {
                    continue;
//					}
//					options.get(0).click();

                }
            }
            if (!p)
                Logger.logError(Localization.getMessage(
                        Localization.SELECT_DATA_WRONG, valueToSelect, name,
                        page));
        } else
            Logger.logError(Localization.getMessage(Localization.NO_SELECT,
                    name, page));
    }

    public String getSelectedOptionText() {
        if (wrappedElement != null) {
            highlightElement();
            Select select = new Select(wrappedElement);
            String value = select.getFirstSelectedOption().getText();
            Logger.logInfo(Localization.getMessage(
                    Localization.SELECT_GET_TEXT, value, name, page));
            return value;
        } else
            Logger.logError(Localization.getMessage(Localization.NO_SELECT,
                    name, page));
        return null;
    }

    public List<String> getOptions() {
        Select select = new Select(wrappedElement);
        List<String> options = new ArrayList<String>();
        for (WebElement option : select.getOptions()) {
            options.add(option.getText());
        }
        return options;
    }


}

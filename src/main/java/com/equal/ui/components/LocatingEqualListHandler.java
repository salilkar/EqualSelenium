package com.equal.ui.components;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.equal.ui.components.AbstractPageElement;

public class LocatingEqualListHandler implements InvocationHandler {

    private final ElementLocator locator;
    private Class<? extends AbstractPageElement> type;
    private String name;
    private String page;

    public LocatingEqualListHandler(Class<? extends AbstractPageElement> type, ElementLocator locator, String name,
                                   String page) {
        this.locator = locator;
        this.type = type;
        this.name = name;
        this.page = page;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> elements = locator.findElements();

        List frameworkElements = new ArrayList();
        for (WebElement element : elements) {
            frameworkElements.add(
                    type.getConstructor(WebElement.class, String.class, String.class).newInstance(element, name, page));
        }

        try {
            return method.invoke(frameworkElements, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }

}

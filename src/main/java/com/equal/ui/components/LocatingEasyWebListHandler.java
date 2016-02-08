package com.equal.ui.components;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.equal.ui.components.AbstractPageElement;

public class LocatingEasyWebListHandler implements InvocationHandler {

    private final ElementLocator locator;
    private Class<? extends AbstractPageElement> type;
    private String name;

    public LocatingEasyWebListHandler(Class<? extends AbstractPageElement> type, ElementLocator locator, String name) {
        this.locator = locator;
        this.type = type;
        this.name = name;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object invoke(Object object, Method method, Object[] objects)
            throws Throwable {
        List<WebElement> elements = locator.findElements();

        List frameworkElements = new ArrayList();
        for (WebElement element : elements) {
            frameworkElements.add(type.getConstructor(WebElement.class, String.class).newInstance(element, name));
        }

        try {
            return method.invoke(frameworkElements, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }

}

package com.equal.dao.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.equal.dao.base.XlsMapping;


public class XlsHelper {

    public static void fillObject(Object object, Map<String, String> map) {
        for (Method method : object.getClass().getMethods()) {
            if (method.getName().startsWith("set")) {
                if (method.isAnnotationPresent(XlsMapping.class)) {
                    try {
                        method.invoke(object, map.get(method.getAnnotation(XlsMapping.class).header()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

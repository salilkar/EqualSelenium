package com.equal.dataprovider.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.equal.common.BaseDP;


public class DataProviderHelper extends BaseDP {

    private DataProviderHelper() {

    }

    public static Object[][] toObject(Object obj) {
        return new Object[][]{new Object[]{obj}};
    }

    public static Iterator<Object[]> toListObject(List items) {
        List<Object[]> data = new ArrayList<Object[]>();
        for (Object item : items) {
            data.add(new Object[]{item});
        }
        return data.iterator();
    }
}

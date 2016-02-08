package com.equal.pages.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.equal.common.Driver;
import com.equal.pages.HomePage;
import com.equal.pages.base.PageObject;


public class SiteMap {

    private static HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>> siteMap;

    private SiteMap() {

    }

    public static HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>> resetSiteMap() {
        Class<? extends PageObject> pageStart = HomePage.class;
        siteMap = new HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>>();
        List<Class<? extends PageObject>> used = new ArrayList<Class<? extends PageObject>>();
        LinkedList<Class<? extends PageObject>> currentlyPages = new LinkedList<Class<? extends PageObject>>();
        try {
            Driver.getCurrentDriver().manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
            PageObject pageStartInst = pageStart.newInstance();
            siteMap.put(pageStart, pageStartInst.getSuccessorKey(pageStartInst.successor));
            used.add(pageStart);
            currentlyPages.add(pageStart);
            while (!currentlyPages.isEmpty()) {
                Class<? extends PageObject> page = currentlyPages.getLast();
                currentlyPages.removeLast();
                PageObject pageInst = page.newInstance();
                if (pageInst.successor != null) {
                    List<Class<? extends PageObject>> listPages = pageInst.getSuccessorKey(pageInst.successor);
                    for (int i = 0; i < listPages.size(); ++i) {
                        Class<? extends PageObject> pageTo = listPages.get(i);
                        if (!used.contains(pageTo)) {
                            used.add(pageTo);
                            currentlyPages.add(pageTo);
                            PageObject pageToInst = pageTo.newInstance();
                            siteMap.put(pageTo, pageToInst.getSuccessorKey(pageToInst.successor));
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            Driver.getCurrentDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        return siteMap;
    }

    public static HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>> getSiteMap() {
        if (siteMap == null) {
            resetSiteMap();
        }
        return siteMap;
    }
}

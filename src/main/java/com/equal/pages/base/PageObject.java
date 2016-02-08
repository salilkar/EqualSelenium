package com.equal.pages.base;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.equal.common.Driver;
import com.equal.common.Logger;
import com.equal.exceptions.NavigationPathNotFoundException;
import com.equal.exceptions.PageNotFoundException;
import com.equal.pages.HomePage;
import com.equal.pages.base.INavigationPage;
import com.equal.pages.base.PageObject;
import com.equal.pages.base.SiteMap;
import com.equal.ui.components.Button;
import com.equal.ui.components.EqualFieldDecorator;


public abstract class PageObject implements INavigationPage {

    //	protected Class<? extends PageObject> startPage = HomePageEasyHome.class;
    private RemoteWebDriver driver = Driver.getCurrentDriver();
    private static HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>> siteMap = SiteMap
            .getSiteMap();
    // private static HashMap<Class<? extends PageObject>, List<Class<? extends
    // PageObject>>> siteMap;
    protected Map<Class<? extends PageObject>, Button> successor = null;
    private static Map<String, Class<? extends PageObject>> mapOfPages = new HashMap<String, Class<? extends PageObject>>();

    protected LinkedList<Class<? extends PageObject>> getSuccessorKey(
            Map<Class<? extends PageObject>, Button> successor) {
        LinkedList<Class<? extends PageObject>> list = new LinkedList<Class<? extends PageObject>>();
        list.addAll(successor.keySet());
        return list;
    }

    public boolean invoke() {

        if (com.equal.pages.base.PageFactory.init(this.getClass()).exist()) {
            return false;
        }
        if (getcurrentPage() == null
                || !com.equal.pages.base.PageFactory.init(getcurrentPage()).exist()) {
            setcurrentPage(getOpenedPage(siteMap));
        }

        Logger.logDebug("StartPage==" + getcurrentPage());
        LinkedList<Class<? extends PageObject>> way = getShortWay(
                getcurrentPage(), this.getClass());
        if (way != null) {
            setcurrentPage(way.getLast());
            passWay(way, null);
        } else {
            if (!getcurrentPage().getSimpleName().equals(
                    getClass().getSimpleName())) {
                throw new NavigationPathNotFoundException(MessageFormat.format(
                        "The following path is not found: from {0} to {1}",
                        getcurrentPage().getSimpleName(), getClass()
                                .getSimpleName()));
            }
        }

        return false;

    }

    private void passWay(LinkedList<Class<? extends PageObject>> way,
                         HashMap<String, String> businessData) {
        do {

            if (com.equal.pages.base.PageFactory.init(way.getFirst()).successor
                    .containsKey(way.get(1))

                    ) {
                com.equal.pages.base.PageFactory.init(way.getFirst()).successor.get(
                        way.get(1)).click();

                com.equal.pages.base.PageFactory.init(way.get(1)).exist();

            } else {
                siteMap = SiteMap.resetSiteMap();
                way = getShortWay(getOpenedPage(siteMap), this.getClass());
                com.equal.pages.base.PageFactory.init(way.getFirst()).successor.get(
                        way.get(1)).click();
                com.equal.pages.base.PageFactory.init(way.getFirst())
                        .setcurrentPage(way.get(1));
                com.equal.pages.base.PageFactory.init(way.get(1)).exist();
            }
            com.equal.pages.base.PageFactory.init(way.get(1));

            way.removeFirst();
        } while (way.size() > 1);
    }

    private void passWayNew(LinkedList<Class<? extends PageObject>> way,
                            HashMap<String, String> businessData) {
        do {
            try {
                way.getFirst().newInstance().successor.get(way.get(1)).click();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            PageFactory.initElements(new EqualFieldDecorator(driver),
                    way.get(1));
            way.removeFirst();
        } while (way.size() > 1);
    }

    private HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>> getSiteMap() {
        if (siteMap == null) {
            Class<? extends PageObject> pageStart = HomePage.class;
            siteMap = new HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>>();
            List<Class<? extends PageObject>> used = new ArrayList<Class<? extends PageObject>>();
            LinkedList<Class<? extends PageObject>> currentlyPages = new LinkedList<Class<? extends PageObject>>();
            PageObject pageStartInst = com.equal.pages.base.PageFactory
                    .init(pageStart); // pageStart.newInstance();
            siteMap.put(pageStart,
                    pageStartInst.getSuccessorKey(pageStartInst.successor));
            used.add(pageStart);
            currentlyPages.add(pageStart);
            while (!currentlyPages.isEmpty()) {
                Class<? extends PageObject> page = currentlyPages.getLast();
                currentlyPages.removeLast();
                PageObject pageInst = com.equal.pages.base.PageFactory.init(page); // page.newInstance();
                if (pageInst.successor != null) {
                    List<Class<? extends PageObject>> listPages = pageInst
                            .getSuccessorKey(pageInst.successor);
                    for (int i = 0; i < listPages.size(); ++i) {
                        Class<? extends PageObject> pageTo = listPages.get(i);
                        if (!used.contains(pageTo)) {
                            used.add(pageTo);
                            currentlyPages.add(pageTo);
                            PageObject pageToInst = com.equal.pages.base.PageFactory
                                    .init(pageTo);// pageTo.newInstance();
                            siteMap.put(pageTo, pageToInst
                                    .getSuccessorKey(pageToInst.successor));
                        }
                    }
                }
            }

            return siteMap;
        } else
            return siteMap;
    }

    private LinkedList<Class<? extends PageObject>> getShortWay(
            Class<? extends PageObject> pageStart,
            Class<? extends PageObject> pageEnd) {
        if (pageStart.equals(pageEnd))
            return null;
        List<Class<? extends PageObject>> way;
        // list for pages which was visited
        List<Class<? extends PageObject>> used = new ArrayList<Class<? extends PageObject>>();
        // list of pages that are currently browsing
        LinkedList<Class<? extends PageObject>> currentlyPages = new LinkedList<Class<? extends PageObject>>();
        // Map for to save a previous page
        HashMap<Class<? extends PageObject>, Class<? extends PageObject>> previous = new HashMap<Class<? extends PageObject>, Class<? extends PageObject>>();
        used.add(pageStart);
        previous.put(pageStart, null);
        currentlyPages.add(pageStart);
        while (!currentlyPages.isEmpty()) {
            Class<? extends PageObject> page = currentlyPages.getFirst();
            currentlyPages.removeFirst();
            List<Class<? extends PageObject>> listPages = siteMap.get(page);
            for (int i = 0; i < listPages.size(); ++i) {
                Class<? extends PageObject> pageTo = listPages.get(i);
                if (!used.contains(pageTo)) {
                    used.add(pageTo);
                    currentlyPages.add(pageTo);
                    previous.put(pageTo, page);
                    if (pageTo.equals(pageEnd)) {
                        // algorithm for constructing the way
                        way = new LinkedList<Class<? extends PageObject>>();
                        way.add(pageTo);
                        do {
                            Class<? extends PageObject> previousPage = previous
                                    .get(pageTo);
                            way.add(previousPage);
                            pageTo = previousPage;
                        } while (previous.get(pageTo) != null);
                        Collections.reverse(way);
                        return (LinkedList<Class<? extends PageObject>>) way;
                    }
                }
            }
        }
        return null;
    }

    private Class<? extends PageObject> getOpenedPage(
            HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>> siteMap) {
        // if (driver.getCurrentUrl().contains(TestBase.baseUrl.substring(8))) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        LinkedList<Class<? extends PageObject>> list = new LinkedList<Class<? extends PageObject>>();
        list.addAll(siteMap.keySet());
        for (Class<? extends PageObject> startPage : list) {
            if (com.equal.pages.base.PageFactory.init(startPage).exist()) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                return startPage;
            }
        }

        if (Driver.getCurrentDriver().findElement(By.xpath("//iframe[@src='//s3.amazonaws.com/heroku_pages/error.html']")).isDisplayed()) {
            Logger.logError("Heroku Error");
            throw new PageNotFoundException("Heroku error");
        }

        throw new PageNotFoundException("Current page is not found");
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public abstract boolean exist();

    public void invokeSuccessor(HashMap<String, String> businessData,
                                Class<? extends PageObject> page) {

    }

    public void invoke(String url) {
        getDriver().get(url);
    }

    public boolean invoke(List<Class<? extends PageObject>> necessaryPages,
                          HashMap<String, String> businessData) {
        if (this.exist())
            return true;
        HashMap<Class<? extends PageObject>, List<Class<? extends PageObject>>> siteMap = getSiteMap();
        Class<? extends PageObject> startPage = getOpenedPage(siteMap);

        LinkedList<Class<? extends PageObject>> way = null;
        if (necessaryPages != null) {
            do {
                if (way == null) {
                    way = getShortWay(
                            startPage,
                            ((LinkedList<Class<? extends PageObject>>) necessaryPages)
                                    .getFirst());
                } else {
                    way.addAll(getShortWay(
                            startPage,
                            ((LinkedList<Class<? extends PageObject>>) necessaryPages)
                                    .getFirst()));
                }
                way.removeLast();
                startPage = ((LinkedList<Class<? extends PageObject>>) necessaryPages)
                        .getFirst();
                ((LinkedList<Class<? extends PageObject>>) necessaryPages)
                        .removeFirst();
            } while (necessaryPages.size() > 0);
            way.addAll(getShortWay(startPage, this.getClass()));
        } else if (necessaryPages == null) {
            way = getShortWay(startPage, this.getClass());
        }
        if (way != null)
            passWay(way, businessData);
        return true;
    }

    private static Class<? extends PageObject> getcurrentPage() {
        if (mapOfPages.get(Thread.currentThread().getId() + "") != null) {
            return mapOfPages.get(Thread.currentThread().getId() + "");
        } else {
            mapOfPages.put(Thread.currentThread().getId() + "",
                    HomePage.class);
            return mapOfPages.get(Thread.currentThread().getId() + "");
        }

    }

    protected static void setcurrentPage(Class<? extends PageObject> page) {
        mapOfPages.put(Thread.currentThread().getId() + "", page);
    }

}

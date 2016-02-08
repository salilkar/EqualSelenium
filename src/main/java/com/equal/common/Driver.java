package com.equal.common;

import java.util.HashMap;
import java.util.Set;

import org.apache.jasper.util.Queue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

    public static WebDriverWait wait;
    public static boolean initialized = false;

    public static void getUrl(String url) {
        driversMap.get(Thread.currentThread().getId() + "").get(url);
    }

    public static WebElement findElementBYId(String id) {
        return driversMap.get(Thread.currentThread().getId() + "").findElement(
                By.id(id));
    }

    public static void closeAllWindows() {
        int size = driverQueue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(driverQueue.size());
            System.out.println(driversMap.size());
            RemoteWebDriver driver = (RemoteWebDriver) driverQueue.pull();

            if (driver != null) {
                // String url = Config.getProperty("host.for.test");
                Set<String> windowHandles = driver.getWindowHandles();
                if (windowHandles != null || !windowHandles.isEmpty()) {
                    if (windowHandles.size() == 1 || driver.getClass().getSimpleName().contains("Remote") || driver.getClass().getSimpleName().contains("Selendroid")) {
                        if (driver.getClass().getSimpleName()
                                .contains("ChromeDriver") || driver.getClass().getSimpleName()
                                .contains("Phone") || driver.getClass().getSimpleName().contains("Selendroid")) {
                            Logger.logDebug("Close Chrome Driver");
                            driver.quit();
                            continue;
                        } else {
                            driver.close();
                            continue;
                        }
                    }
                    for (String windowId : windowHandles) {
                        driver.switchTo().window(windowId);
                        if (driver.getClass().getSimpleName()
                                .contains("ChromeDriver") || driver.getClass().getSimpleName()
                                .contains("Selendroid")) {
                            Logger.logDebug("Close Chrome Driver");
                            driver.quit();
                        } else {
                            driver.close();
                        }
                    }
                }
            }
        }
    }

    private static Queue driverQueue = new Queue();
    ;
    private static HashMap<String, RemoteWebDriver> driversMap = new HashMap<String, RemoteWebDriver>();
    ;

    public static synchronized RemoteWebDriver getDriver() {

        if (!initialized) {
            for (int i = 0; i < new Integer(Config.getProperty("threadCount"))
                    .intValue(); i++) {
                BrowserDriverFactory factory = new BrowserDriverFactory();
                driverQueue.put(factory.chooseBrowser(Config.getProperty(
                        "driver").toUpperCase()));
            }
            initialized = true;
        }
        while (true) {
            if (initialized && driverQueue.size() > 0) {
                RemoteWebDriver output = (RemoteWebDriver) driverQueue.pull();

                driversMap.put(Thread.currentThread().getId() + "", output);
                return output;
            } else {
                Thread.currentThread().yield();

                continue;
            }
        }
    }

    public static RemoteWebDriver getCurrentDriver() {
        try {

            return driversMap.get(Thread.currentThread().getId() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // driversMap.put(Thread.currentThread().getId()+"", output);
        return null;
    }

    public static void refreshDriver() {
        BrowserDriverFactory factory = new BrowserDriverFactory();
//		driversMap.get(Thread.currentThread().getId() + "").executeScript("window.close()", Driver.getCurrentDriver().findElement(By.tagName("body")));
        Set<String> windows = driversMap.get(Thread.currentThread().getId() + "").getWindowHandles();
        for (String string : windows) {
            driversMap.get(Thread.currentThread().getId() + "").switchTo().window(string);
            driversMap.get(Thread.currentThread().getId() + "").close();
        }
        driversMap.get(Thread.currentThread().getId() + "").quit();

        driversMap.put(Thread.currentThread().getId() + "", factory
                .chooseBrowser(Config.getProperty("driver").toUpperCase()));
    }

    public static boolean releaseDriver() {
        try {
            RemoteWebDriver releasedDriver = driversMap.get(Thread
                    .currentThread().getId() + "");
            driverQueue.put(releasedDriver);
            driversMap.remove(Thread.currentThread().getId() + "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

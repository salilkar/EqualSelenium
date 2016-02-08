package com.equal.common;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserDriverFactory {
    //	static SelendroidConfiguration config;
//	static SelendroidLauncher selendroidServer;
//	static{
//    config = new SelendroidConfiguration();
//    config.addSupportedApp("src\\test\\java\\resourses\\EasyOrders-0.1.147.apk");
//    selendroidServer = new SelendroidLauncher(config);
//    selendroidServer.lauchSelendroid();
//	}
    public RemoteWebDriver chooseBrowser(String selectedBrowser) {
        if (selectedBrowser != null) {
            if (selectedBrowser.equalsIgnoreCase("FF")) {
                return getFireFoxDriver();
            }
            if (selectedBrowser.equalsIgnoreCase("CHROME")) {
                return getChromeDriver();
            }
            if (selectedBrowser.equalsIgnoreCase("IE")) {

                return getInternetExplorerDriver();
            }
            if (selectedBrowser.equals("Safari")) {

                return getSafariDriver();
            }
            if (selectedBrowser.equalsIgnoreCase("IP")) {

                return getIPDriver();
            }
            if (selectedBrowser.equals("SAFARI")) {

                return getIPDriver();
            }

            if (selectedBrowser.equalsIgnoreCase("ANDROID")) {

                return getANDROIDDriver();
            }

            if (selectedBrowser.equalsIgnoreCase("AH")) {

                return getANDROIDHybridDriver();
            }


        }
        return getFireFoxDriver();
    }

    private RemoteWebDriver getFireFoxDriver() {
        final FirefoxProfile profile = new FirefoxProfile(/* profilePath */);

        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setAcceptUntrustedCertificates(true);
        profile.setEnableNativeEvents(false);
        profile.setPreference("geo.enabled", false);
        RemoteWebDriver driver = new FirefoxDriver(profile);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        return driver;
    }

    private RemoteWebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver",
                "src\\test\\resources\\chromedriver.exe");
        
        ChromeOptions options = new ChromeOptions();
//				try {
//					options.setExperimentalOptions("prefs", new JSONObject().put("profile.default_content_settings.geolocation", 2));
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				options.
        //(options).setExperimentalOption("prefs", new JSONObject().put("profile.default_content_settings.geolocation", 2))

        return new ChromeDriver(options);
    }

    private RemoteWebDriver getIPDriver() {
        try {
            // return new RemoteWebDriver(new
            // URL("http://10.25.11.137:3001/wd/hub"),
            // DesiredCapabilities.iphone());

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
            capabilities.setCapability(CapabilityType.VERSION, "6.0");
            capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
            capabilities.setCapability("device", "iPhone Simulator");
            //tell Appium where the location of the app is
            capabilities.setCapability("app", "safari");

            //create a RemoteWebDriver, the default port for Appium is 4723
            return new RemoteWebDriver(new URL("http://10.25.10.219:4723/wd/hub/"), capabilities);


//			DesiredCapabilities safari = IOSCapabilities.iphone("Safari");
//			safari.iphone();
//			RemoteWebDriver driver = new RemoteWebDriver(new URL("http://10.25.160.247:4449/wd/hub/"), safari);
//			return driver;
//			return new IPhoneDriver("http://10.25.12.2:4444/wd/hub/");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return getFireFoxDriver();
        }

    }

    private RemoteWebDriver getInternetExplorerDriver() {
        System.setProperty("webdriver.ie.driver",
                "src\\test\\resources\\IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities
                .internetExplorer();
        capabilities
                .setCapability(
                        InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                        true);
        capabilities.setJavascriptEnabled(true);
        capabilities.setVersion("IE11");
        System.out.println(capabilities.getVersion()
                + "+-*/-/**++-*/+/+/+/+/*+/*++++*/-/--*-+/+*/+*+-/-*");
        return new InternetExplorerDriver(capabilities);
    }


    private RemoteWebDriver getANDROIDDriver() {
//		try {
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        caps.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        caps.setCapability(CapabilityType.SUPPORTS_LOCATION_CONTEXT,
                true);
        caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

//			return (new AndroidDriver(new URL(
//					"http://192.168.56.101:8080/wd/hub"), caps));
//			return null;
//		} catch (MalformedURLException e) {
        // TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return null;
    }


    public RemoteWebDriver getANDROIDHybridDriver() {
        try {

            DesiredCapabilities capabilities = DesiredCapabilities
                    .android();
//				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
//						"Android");

//				capabilities.setCapability(
//						MobileCapabilityType.PLATFORM_VERSION, "4.4");
//
//				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
//						"Android Emulator");
//
//				capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
//						"com.easyfinancial.indirect.mobile");
//				capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
//						".MobileApp");

//				URL url = new URL("http://127.0.0.1:4723/wd/hub");
            URL url = new URL("http://10.25.14.74:4723/wd/hub");

//				AppiumDriver<WebElement> driver = null;
            try {
//					driver = new AndroidDriver<WebElement>(url, capabilities);
            } catch (Exception e) {
                e.printStackTrace();
            }

//				System.out.println(driver.getContext());
//				Set<String> contextNames = driver.getContextHandles();
//				for (String contextName : contextNames) {

            try {

//						if (contextName.contains("WEBVIEW")) {
//							driver.context(contextName);
//						}
            } catch (Exception e) {
                e.printStackTrace();
//						driver.context("WEBVIEW_0");
            }
//				}
//				return driver;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private RemoteWebDriver getAppiumDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("device", "Selendroid");
        desiredCapabilities.setCapability("app", "src\\test\\java\\resourses\\EasyOrders-0.1.147.apk");
        URL url;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");

            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, desiredCapabilities);
            return remoteWebDriver;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    private RemoteWebDriver getSafariDriver() {
//				// <<<<<<< .mine
//			try {
//				// ///////////////////////////////////////
//				// ///////////for Safari
//				SeleniumServer ss;
//				RemoteControlConfiguration rcc;
//
//				rcc = new RemoteControlConfiguration();
//				rcc.setInteractive(true);
//				rcc.setSingleWindow(true);
//				rcc.setTimeoutInSeconds(10);
//				ss = new SeleniumServer(rcc);
//
//				ss.start();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// ///////////for Safari
//			// ///////////////////////////////////////
//			Selenium sel = new DefaultSelenium("10.25.11.231", 4444, "*safari",
//					"/Applications");
//			CommandExecutor executor = new SeleneseCommandExecutor(sel);
//			DesiredCapabilities dc = new DesiredCapabilities();
//			return new RemoteWebDriver(executor, dc);
//
//			// c.setBrowserName("safari");
//			// c.safari();
        File safari = new File(
                "C:\\Program Files (x86)\\Safari\\Safari.exe");
        System.setProperty("SafariDefaultPath",
                safari.getAbsolutePath());
        System.setProperty("webdriver.safari.driver", "src\\test\\java\\resources\\selenium-server-standalone-2.31.0.jar");
        return new SafariDriver(DesiredCapabilities.safari());
//			// =======
//			// //c.setBrowserName("safari");
//			// //c.safari();
//			// File safari = new
//			// File("C:\\Program Files (x86)\\Safari\\Safari.exe");

//			// return new SafariDriver();
//			// >>>>>>> .r938
//		
//		return new SafariDriver();
    }
}

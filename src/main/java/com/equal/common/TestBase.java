package com.equal.common;

import com.equal.dao.base.DAORepository;
import com.equal.dao.user.IUserDAO;
import com.equal.dao.user.UserXlsDAO;
import com.equal.listeners.AnnotationTransformerListener;
import com.equal.listeners.TestListener;
import com.equal.listeners.TestResultListener;
import com.equal.logging.CustomReport;
import com.equal.pages.LoginPage;
import com.equal.ui.components.Button;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.*;

import static com.equal.common.Driver.wait;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Listeners({AnnotationTransformerListener.class, TestListener.class, TestResultListener.class})
public class TestBase {

    protected RemoteWebDriver driver;
    public static CustomReport reports;
    public Asserter asserter = new Asserter();

    static {
        TestNG testNG = new TestNG();
        testNG.setAnnotationTransformer(new AnnotationTransformerListener());
        // System.setProperty("spring.profiles.active", "defaultTest");
        BaseDP.daoRepository = new DAORepository();
    }

    public static String baseUrl = Config.getProperty("host.for.test");

    private IUserDAO userDAO = new UserXlsDAO();
    // public List<UserDTO> testData = userDAO.findListById("Test");

    public boolean deleteSession() {

        if (Driver.getCurrentDriver().getClass().getSimpleName().contains("InternetExplorer")) {

            Driver.refreshDriver();
            driver = Driver.getCurrentDriver();
            driver.get(baseUrl);
        } else if (Driver.getCurrentDriver().getClass().getSimpleName().contains("Remote")) {
            Driver.getCurrentDriver().executeScript(
                    "var cookies = document.cookie.split(\";\");for (var i = 0; i < cookies.length; i++) document.cookie = cookies[i].split(\"=\")[0]+\"=;expires=Thu, 01 Jan 1970 00:00:00 GMT\";",
                    (new Button()).getElement());

        } else {
            // System.out.println(Driver.getCurrentDriver().executeScript("return
            // document.cookie.split(\";\");", (new Button()).getElement()));
            Driver.getCurrentDriver().manage().deleteAllCookies();
            if (!driver.getClass().getSimpleName().contains("SelendroidDriver")) {
                Driver.getCurrentDriver().executeScript(
                        "var cookies = document.cookie.split(\";\");for (var i = 0; i < cookies.length; i++) document.cookie = cookies[i].split(\"=\")[0]+\"=;expires=Thu, 01 Jan 1970 00:00:00 GMT\";",
                        (new Button()).getElement());
            }
            // System.out.println(Driver.getCurrentDriver().executeScript("return
            // document.cookie.split(\";\");", (new Button()).getElement()));

        }
        Logger.logDebug("All cookies were deleted");
        //
        return true;
    }

    @BeforeClass(alwaysRun = true)
    public void printClassNameBeforeTest() {
        Logger.logDebug("+++++++++++++++++++++++++++++++++++++++++" + this.getClass().getSimpleName()
                + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        reports = new CustomReport();
        reports.scriptStartTime = System.currentTimeMillis();
        try {
            reports.createTestReport(this.getClass().getSimpleName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Logger.logDebug("New report created");
    }

    @AfterClass(alwaysRun = true)
    public void printClassName() {
        /////////////////////////////////////
        Logger.setTest(false);
        reports.executionHealthReport(getClass().getSimpleName());
        try {
            if (CustomReport.failedTests > 0) {
                CustomReport.failCount = CustomReport.failCount + CustomReport.failedTests;
                CustomReport.passCount = CustomReport.passCount + CustomReport.passedTests;
                reports.summaryReport(getClass().getSimpleName(), getClass().getSimpleName(),
                        CustomReport.getExecutionTime(reports.scriptStartTime, System.currentTimeMillis()),
                        reports.fail);

                reports.closeFile();
            } else {
                CustomReport.failCount = CustomReport.failCount + CustomReport.failedTests;
                CustomReport.passCount = CustomReport.passCount + CustomReport.passedTests;
                reports.summaryReport(getClass().getSimpleName(), getClass().getSimpleName(),
                        CustomReport.getExecutionTime(reports.scriptStartTime, System.currentTimeMillis()),
                        reports.pass);
                reports.closeFile();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CustomReport.failedTests = 0;
        CustomReport.passedTests = 0;

        Logger.logDebug("+++++++++++++++++++++++++++++++++++++++++" + this.getClass().getSimpleName()
                + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }

    @BeforeMethod(alwaysRun = true)
    public synchronized void start(Method method) {
        Logger.logDebug("TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST");
        Logger.logDebug("Start BEFORE Method");
        while (true) {
            driver = Driver.getDriver();
            if (driver != null)
                break;
        }
        System.out.println(driver.getClass().getSimpleName());
        if (!driver.getClass().getSimpleName().contains("Android")
                && !driver.getClass().getSimpleName().contains("Remote")
                && !driver.getClass().getSimpleName().contains("SelendroidDriver")) {
            driver.manage().window().maximize();
        }

        Logger.logDebug("STEP 2");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        if (!driver.getClass().getSimpleName().contains("AndroidDriver")
                && !driver.getClass().getSimpleName().contains("Remote")
                && !driver.getClass().getSimpleName().contains("SelendroidDriver")) {
            driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 8);
        }

        if (!driver.getClass().getSimpleName().contains("SelendroidDriver")) {
            driver.get(baseUrl);
        }
        if (!driver.getClass().getSimpleName().contains("AndroidDriver")
                && !driver.getClass().getSimpleName().contains("Remote")) {
            deleteSession();
        }
        Logger.logDebug("Finish BEFORE Class");
        LoginPage home = new LoginPage();
        home.makeThisPageCurrent();

        //////////////////////////// custom reporter
        reports.writeTestName(method.getName());

    }

    @AfterMethod(alwaysRun = true)
    public void end(ITestResult result) {
        if (Driver.getCurrentDriver() != null
                && !Driver.getCurrentDriver().getClass().getSimpleName().contains("Phone")) {
            try {

                Logger.makeScreenshot(this.getClass().getSimpleName() + System.currentTimeMillis());
            } catch (Exception e) {
            }
            Logger.logDebug("Start AFTER CLASS");
            Logger.logDebug("TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST");

            closeWindows();
        }
        deleteSession();
        Driver.releaseDriver();

        Logger.logDebug("Finish AFTER CLASS");

        ///////////////////////////////
        if (result.isSuccess()) {
            CustomReport.passedTests = CustomReport.passedTests + 1;
        } else {
            CustomReport.failedTests = CustomReport.failedTests + 1;
        }
        CustomReport.testStatus = true;
    }

    @BeforeSuite(alwaysRun = true)
    public void createUsers() {
        Logger.setTest(false);
        Logger.logDebug("Start BeforeSuite create user");
        // :TODO here must be logic for creating of test data
    }

    @AfterSuite(alwaysRun = true)
    public void closeAllWindows() {

        Logger.setTest(false);
        Logger.logDebug("Before closing all windows");

        Driver.closeAllWindows();

        Logger.logDebug("All Windows are Closed");
        Logger.logDebug(killIEServers());
        System.out.println("Generate total report");
        reports.writtingSummaryReport();

    }

    @SuppressWarnings("null")
	public void closeWindows() {
        if (Driver.getCurrentDriver() != null) {
            Set<String> windowHandles = Driver.getCurrentDriver().getWindowHandles();
            if (windowHandles != null || !windowHandles.isEmpty()) {
                if (windowHandles.size() == 1 || Driver.getCurrentDriver().getClass().getSimpleName().contains("Remote")
                        || Driver.getCurrentDriver().getClass().getSimpleName().contains("Selendroid")) {

                    return;
                }
                int count = windowHandles.size();
                for (String windowId : windowHandles) {
                    Driver.getCurrentDriver().switchTo().window(windowId);
                    if (count == 1) {
                        return;
                    }

                    Driver.getCurrentDriver().close();
                    count = count - 1;
                }
            }
        }
    }

    private String killIEServers() {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

}

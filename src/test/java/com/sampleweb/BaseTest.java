package com.sampleweb;

import com.sampleweb.listeners.TestListener;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

@Listeners(TestListener.class)
public abstract class BaseTest {

    private static final String DEFAULT_SELENIUM_ENV_PROPERTIES_FILE_PATH = "src/test/resources/test.properties";

    private static Properties props;
    protected RemoteWebDriver driver = null;
    private DesiredCapabilities dr = null;

    static {
        props = new Properties();
        System.out.println("Current dir using System:" + System.getProperty("user.dir"));
        System.out.println(new File(DEFAULT_SELENIUM_ENV_PROPERTIES_FILE_PATH).getPath());
        System.out.println(new File(DEFAULT_SELENIUM_ENV_PROPERTIES_FILE_PATH).getAbsolutePath());
        try {
            System.out.println(new File(DEFAULT_SELENIUM_ENV_PROPERTIES_FILE_PATH).getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEnvironmentPropertiesFromFile() {
        loadPropertiesFromFile(DEFAULT_SELENIUM_ENV_PROPERTIES_FILE_PATH);
    }

    @BeforeMethod(alwaysRun = true)
    public void setupBaseTest() throws Exception {
        System.out.println(isLocal());
        dr = DesiredCapabilities.firefox();
        if(isLocal().equals("true")){
//            driver = new ChromeDriver();
            driver = new RemoteWebDriver(new URL(gridLocalUrl()), dr);
        } else {
            driver = new RemoteWebDriver(new URL(gridUrl()), dr);
        }
        driver.get(baseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.isSuccess()) {
            if (null != driver) {
                driver.manage().deleteAllCookies();
            }
            driver.close();
        } else {
            driver.quit();
        }
    }

    public String isLocal() {
        return getProp("test.local");
    }

    public String gridUrl() {
        return getProp("test.gridUrl");
    }

    public String gridLocalUrl() {
        return getProp("test.gridLocalUrl");
    }

    public String baseUrl() {
        return getProp("test.mainUrl");
    }

    protected String getProp(String key) {
        loadEnvironmentPropertiesFromFile();
        String s = props.getProperty(key);
        return (s != null) ? s.trim() : null;
    }

    private void loadPropertiesFromFile(String defaultPropertiesFilePath) {
        loadPropertiesFromFile(defaultPropertiesFilePath, false);
    }

    private void loadPropertiesFromFile(String defaultPropertiesFilePath, boolean ignoreNotFound) {
        String path;
        if(System.getProperty("user.dir").contains("target")){
            path = "../" + DEFAULT_SELENIUM_ENV_PROPERTIES_FILE_PATH;
        } else {
            path = DEFAULT_SELENIUM_ENV_PROPERTIES_FILE_PATH;
        }
        try {
            InputStream propsStream;
            propsStream = new FileInputStream(new File(path).getPath());
            props.load(propsStream);
        } catch (Exception ex) {
            if (!ignoreNotFound) {
                ex.printStackTrace();
                throw new RuntimeException(
                        "Problem loading test properties file [" + ex.getMessage() + "]. Is " + defaultPropertiesFilePath + " on the classpath?", ex);

            }
        }
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}

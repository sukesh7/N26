package com.monefy;

import com.monefy.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

public class Base {
    protected static AppiumDriver driver;
    protected static Properties props;
    InputStream inputStream;
    public static AppiumDriverLocalService service;

    public Base() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @BeforeMethod
    public void beforeMethod() {
        startServer();
    }

    public void startServer() {
        if(!checkIfServerIsRunning(4723)){
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
    }

    @AfterMethod
    public void stopServer() {
        service.stop();
    }

    @BeforeTest
    @Parameters({"platformName","platformVersion","deviceName"})
    public void beforeTest(String platformName, String platformVersion, String deviceName) {
            startServer();
        try {
            props = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
            desiredCapabilities.setCapability("appPackage",props.getProperty("androidPackageName"));
            desiredCapabilities.setCapability("appActivity",props.getProperty("androidActivity"));
            URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
            desiredCapabilities.setCapability("app",appUrl);
            desiredCapabilities.setCapability(MobileCapabilityType.UDID,props.getProperty("emulator"));
            desiredCapabilities.setCapability("avd","Pixel_5_API_30");
            desiredCapabilities.setCapability("avdLaunchTimeout",180000);

            URL url = new URL(props.getProperty("appiumURL"));
            driver = new AndroidDriver(url,desiredCapabilities);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    public void waitForVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch(IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}

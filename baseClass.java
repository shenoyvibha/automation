package com.pack.helper;

//import com.sun.tools.internal.ws.processor.model.ModelVisitor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import javafx.scene.input.KeyCode;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class baseClass {
    public baseClass() throws MalformedURLException {
    }

    //public AppiumDriver driver;
    public static AndroidDriver getDriver() throws MalformedURLException {
        File app = new File("/Users/vibha/apks/1528805200967_app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        DesiredCapabilities cap = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformversion", "6.0.1");
        capabilities.setCapability("noReset", true);

        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability("resetKeyboard", "true");
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;

    }

    public AppiumDriver driver = getDriver();
    TouchAction touchAction = new TouchAction(driver);

    public void searchBarClick() {
        MobileElement searchBar = (MobileElement) driver.findElementById("example.com.githubissues:id/et_search");
        searchBar.click();
    }

    public void sendKeysToSearchBar(String searchQuery) {
        MobileElement searchBar = (MobileElement) driver.findElementById("example.com.githubissues:id/et_search");
        searchBar.sendKeys(searchQuery);


    }

    public static void searchError(String message) throws MalformedURLException {
        String text = getScreenShotForToast();
        Assert.assertTrue(text.contains(message));

    }



    public MobileElement getIssueCreatorField() {
        MobileElement issueCreatorField = (MobileElement) driver.findElement(By.id("example.com.githubissues:id/creator_name_holder"));
        return issueCreatorField;
    }

    public MobileElement getIssueIdHolder() {
        MobileElement issueIdHolder = (MobileElement) driver.findElement(By.id("example.com.githubissues:id/id_holder"));
        return issueIdHolder;
    }

    public MobileElement getIssueIdNumber() {
        MobileElement issueIdNumber = (MobileElement) driver.findElement(By.id("example.com.githubissues:id/issue_id"));
        return issueIdNumber;
    }

    public MobileElement getIssueCreatorName() {
        MobileElement issueCreatorName = (MobileElement) driver.findElement(By.id("example.com.githubissues:id/creator_name"));
        return issueCreatorName;
    }

    public MobileElement getIssueTitle() {
        MobileElement issueTitle = (MobileElement) driver.findElement(By.id("example.com.githubissues:id/title"));
        return issueTitle;
    }

    public static String getScreenShotForToast() throws MalformedURLException {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        try {
            FileUtils.copyFile(scrFile, new File("./apks/" + "getText" + ".png"));
        } catch (Exception e) {
            System.out.println("Error in copying screenshot");
        }


        File imageFile = new File("./apks/getText.png");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath("/usr/local/Cellar/tesseract/3.05.01/share/");
        String result;
        try {
            result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            return "Failed";
        }
        return result;

    }


}


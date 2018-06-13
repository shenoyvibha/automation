package com.pack.test;

import com.pack.helper.baseClass;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import javafx.scene.input.KeyCode;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.openqa.selenium.Keys.ENTER;

public class newMethods {
    public newMethods() throws MalformedURLException {
    }

    baseClass baseClass = new baseClass();
    @Test(priority = 1,description = "When User does not enter anything and clicks on search, "+
                                      "Then an error with the message 'Oops! some error occured' should appear")
    public void blankSearch() throws MalformedURLException {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar(" ");
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        baseClass.searchError("Oops! Some error occured.");

    }

    @Test(priority = 2, description = "When User enters a valid repository and clicks on search, "+
                                      "Then the list of errors for that repository should be dispalyed, scroll through that list")
    public void validSearch() throws InterruptedException {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar("GoodieBag/CarouselPicker"); //GoodieBag/CarouselPicker
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        baseClass.driver.findElementByXPath("//android.widget.TextView[@text='a way to change alpha of not \"focused/chosen\" items?']").click();
        ((AndroidDriver) baseClass.driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Color\"));");
    }

    @Test(priority = 3, description = "When User enters a valid repository and clicks on search," +
                                      "Then the list of errors for that repo should be listed and an issue should have Issue title, issue Id, creator of the issue ")
    public void validSearchFields() throws InterruptedException {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar("GoodieBag/CarouselPicker");
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        baseClass.getIssueCreatorField().isDisplayed();
        baseClass.getIssueTitle().isDisplayed();
        baseClass.getIssueIdNumber().isDisplayed();
        baseClass.getIssueIdHolder().isDisplayed();
        baseClass.getIssueCreatorField().isDisplayed();
        baseClass.getIssueCreatorName().isDisplayed();
        Assert.assertEquals(baseClass.getIssueIdHolder().getText(),"id: ");
        Assert.assertEquals(baseClass.getIssueCreatorField().getText(),"creator: ");

    }
    @Test(priority = 4, description = "When User enter an invalid repository name and clicks on search"+
                                      "Then the error with the message 'Oops! some error occured' should appear")
    public void invalidSearch() throws MalformedURLException {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar("good/");
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        baseClass.searchError("Oops! Something went wrong");

    }
    @Test(priority = 5, description = "When User enters a repository name with no issues in it"+
                                      "Then a message saying 'No issues found for the searched repository' should be displayed")
    //No issues found for the searched repository
    public void validWithNoIssues() throws MalformedURLException, InterruptedException {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar("GoodieBag/GBOS");
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        baseClass.searchError("No issues found for the searched repository");
    }






}

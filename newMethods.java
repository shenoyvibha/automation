package com.pack.test;

import com.pack.helper.baseClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

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
                                      "Then the list of errors for that repository should be dispalyed")
    public void validSearch() {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar("square/retrofit");
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);


    }
    @Test(priority = 3, description = "When User enter an invalid repository name and clicks on search"+
                                      "Then the error with the message 'Oops! some error occured' should appear")
    public void invalidSearch() throws MalformedURLException {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar(" ");
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.ENTER);
        baseClass.searchError("Oops! Something went wrong");

    }
    @Test(priority = 4, description = "When User enters a repository name with no issues in it"+
                                      "Then a message saying 'No issues found for the searched repository' should be displayed")
    //No issues found for the searched repository
    public void validWithNoIssues() throws MalformedURLException, InterruptedException {
        baseClass.searchBarClick();
        baseClass.sendKeysToSearchBar("GoodieBag/GBOS");
        ((AndroidDriver)baseClass.driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        baseClass.searchError("No issues found for the searched repository");
    }




}

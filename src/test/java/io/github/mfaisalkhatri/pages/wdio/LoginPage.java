package io.github.mfaisalkhatri.pages.wdio;

import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final AndroidDriver androidDriver;
    private final WebDriverWait wait;

    public LoginPage (final AndroidDriver androidDriver) {

        this.androidDriver = androidDriver;
        this.wait = new WebDriverWait (androidDriver, Duration.ofSeconds (10));
    }

    public String getSuccessMessageText () {
        return this.androidDriver.findElement (AppiumBy.id ("android:id/message"))
            .getText ();
    }

    public String getSuccessMessageTitle () {
        return this.wait.until (ExpectedConditions.visibilityOfElementLocated (AppiumBy.id ("android:id/alertTitle")))
            .getText ();
    }

    public void performBiometricLogin (final int fingerPrintId) {
        fingerPrintBtn ().click ();
        cancelBtnInAuthenticationView ().isDisplayed ();
        this.androidDriver.fingerPrint (fingerPrintId);
    }

    private WebElement cancelBtnInAuthenticationView () {
        return this.wait.until (
            ExpectedConditions.visibilityOfElementLocated (AppiumBy.accessibilityId ("Tap to cancel authentication")));
    }

    private WebElement fingerPrintBtn () {
        return this.androidDriver.findElement (AppiumBy.accessibilityId ("button-biometric"));
    }
}
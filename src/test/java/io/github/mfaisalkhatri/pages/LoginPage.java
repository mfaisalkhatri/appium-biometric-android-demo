package io.github.mfaisalkhatri.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private AndroidDriver androidDriver;
    private WebDriverWait wait;

    public LoginPage(AndroidDriver androidDriver) {

        this.androidDriver = androidDriver;
        wait = new WebDriverWait(androidDriver, Duration.ofSeconds(10));
    }


    private WebElement fingerPrintBtn() {
       return androidDriver.findElement(AppiumBy.accessibilityId("button-biometric"));
    }

    public void performBioMetricLogin(int fingerPrintId) {
        fingerPrintBtn().click();
        androidDriver.fingerPrint(fingerPrintId);
    }

    public void performBioMetricAuthenticationOnRealDevice() {
        fingerPrintBtn().click();
        androidDriver.executeScript("lambda-biometric-injection=pass");
    }


    public String getSuccessMessageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("android:id/alertTitle"))).getText();
    }

    public String getSuccessMessageText () {
        return androidDriver.findElement(AppiumBy.id("android:id/message")).getText();
    }
}

package io.github.mfaisalkhatri.pages.instrumentation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private final AndroidDriver androidDriver;

    public HomePage(final AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    private WebElement biometricBtn() {
        return this.androidDriver.findElement(AppiumBy.id("com.poc.sample:id/biometric"));
    }

    public void performSuccessBioMetricAuthenticationOnRealDevice() {
        biometricBtn().click();
        this.androidDriver.executeScript("lambda-biometric-injection=pass");
    }

    public void performFailedBioMetricAuthenticationOnRealDevice() {
        biometricBtn().click();
        this.androidDriver.executeScript("lambda-biometric-injection=fail");
    }

    public String getMessageText () {
        return this.androidDriver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")).getText();
    }

}
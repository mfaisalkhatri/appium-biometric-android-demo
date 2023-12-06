package io.github.mfaisalkhatri.pages.instrumentation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private AndroidDriver androidDriver;

    public HomePage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    private WebElement biometricBtn() {
        return androidDriver.findElement(AppiumBy.id("com.poc.sample:id/biometric"));
    }

    public void performBioMetricAuthenticationOnRealDevice() {
        biometricBtn().click();
        androidDriver.executeScript("lambda-biometric-injection=pass");
    }

    public String getSuccessMessage () {
        return androidDriver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")).getText();
    }

}

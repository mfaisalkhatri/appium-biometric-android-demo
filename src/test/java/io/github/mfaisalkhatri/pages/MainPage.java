package io.github.mfaisalkhatri.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;



public class MainPage {

    private final AndroidDriver androidDriver;

    public MainPage(final AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void openMenu(final String menuName) {
        this.androidDriver.findElement(AppiumBy.accessibilityId(menuName)).click();
    }

    public LoginPage openLoginPage() {
        openMenu("Login");
        return new LoginPage(this.androidDriver);
    }


}

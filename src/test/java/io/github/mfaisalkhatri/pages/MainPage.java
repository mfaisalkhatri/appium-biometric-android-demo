package io.github.mfaisalkhatri.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;



public class MainPage {

    private AndroidDriver androidDriver;

    public MainPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void openMenu(String menuName) {
        androidDriver.findElement(AppiumBy.accessibilityId(menuName)).click();
    }

    public LoginPage openLoginPage() {
        openMenu("Login");
        return new LoginPage(androidDriver);
    }


}

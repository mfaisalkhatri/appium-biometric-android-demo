package io.github.mfaisalkhatri.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

public class AndroidDriverManager {

    private AndroidDriver androidDriver;
    private static final String APP_PATH = String.valueOf(
            Path.of(System.getProperty("user.dir"), "/src/test/resources/", "Android-NativeDemoWdio_App-0.4.0.apk"));


    public void createAndroidDriver() {
        try {
            this.androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options());
        } catch (final MalformedURLException e) {
            throw new Error("Error while setting up Android Driver", e);
        }
        setupDriverTimeouts();
    }

    public void createAndroidDriverInCloud() {
        System.out.println("setting up driver in cloud");

    }

    public AndroidDriver getAndroidDriver() {
        return this.androidDriver;
    }


    private static UiAutomator2Options uiAutomator2Options() {

        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Pixel_7")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
//                .setUnlockType("fingerprint")
//                .setUnlockKey("1")
                .setApp(APP_PATH)
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity("com.wdiodemoapp.MainActivity")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAutoGrantPermissions(true)
                .setNoReset(false);

        return uiAutomator2Options;
    }

    private void setupDriverTimeouts() {
        getAndroidDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }


    public void quitDriver() {
        getAndroidDriver().quit();
    }
}

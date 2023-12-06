package io.github.mfaisalkhatri.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

import static java.text.MessageFormat.format;

public class AndroidDriverManager {

    private AndroidDriver androidDriver;
    private static final String APP_PATH = String.valueOf(
            Path.of(System.getProperty("user.dir"), "/src/test/resources/", "Android-NativeDemoWdio_App-0.4.0.apk"));
    private static final String LT_USERNAME = System.getProperty("LT_USERNAME");
    private static final String LT_ACCESS_KEY = System.getProperty("LT_ACCESS_KEY");
    private static final String GRID_URL = "@mobile-hub.lambdatest.com/wd/hub";


    public void createAndroidDriver() {
        try {
            this.androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options());
        } catch (final MalformedURLException e) {
            throw new Error("Error while setting up Android Driver", e);
        }
        setupDriverTimeouts();
    }

    public void createAndroidDriverInCloud() {
        try {
            this.androidDriver = new AndroidDriver(new URL(format("https://{0}:{1}{2}", LT_USERNAME, LT_ACCESS_KEY, GRID_URL)), setCapabilities());
        } catch (MalformedURLException e) {
            throw new Error("Error while setting up Android Driver in cloud", e);
        }
        setupDriverTimeouts();
    }

    private HashMap<String, Object> ltOptions() {
        final var ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", LT_USERNAME);
        ltOptions.put("accessKey", LT_ACCESS_KEY);
        ltOptions.put("platformName", "ANDROID");
        ltOptions.put("deviceName", "Galaxy S22 5G");
        ltOptions.put("platformVersion", "13");
        ltOptions.put("app", "lt://APP1016033381701860877646796");
        ltOptions.put("w3c", true);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("autoGrantPermissions", true);
        ltOptions.put("enableBiometricsAuthentication", true);
        ltOptions.put("build", "Appium biometric test suite");
        ltOptions.put("name", "Test fingerprint authentucation on login");
        ltOptions.put("plugin", "java-testNG");
        ltOptions.put("visual", true);
        ltOptions.put("console", true);
        ltOptions.put("devicelog", true);
        return ltOptions;
    }

    private DesiredCapabilities setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("lt:options", ltOptions());
        return capabilities;
    }


    public AndroidDriver getAndroidDriver() {
        return this.androidDriver;
    }


    private static UiAutomator2Options uiAutomator2Options() {

        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Pixel_7")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
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

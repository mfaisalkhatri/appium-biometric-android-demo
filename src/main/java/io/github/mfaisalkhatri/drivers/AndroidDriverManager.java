package io.github.mfaisalkhatri.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

import static java.text.MessageFormat.format;

public class AndroidDriverManager {

    private AndroidDriver androidDriver;
    private static final String APP_PATH = String.valueOf(
            Path.of(System.getProperty("user.dir"), "/src/test/resources/", "android.wdio.native.app.v1.0.8.apk"));


    public void createAndroidDriver() {
        try {
            this.androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2Options());
        } catch (final MalformedURLException e) {
            throw new Error("Error while setting up Android Driver", e);
        }
        setupDriverTimeouts();
    }

    public void createAndroidDriverInCloud() {
        final String ltUserName = System.getenv("LT_USERNAME");
        final String ltAccessKey = System.getenv("LT_ACCESS_KEY");
        final String gridUrl = "@mobile-hub.lambdatest.com/wd/hub";

        try {
            this.androidDriver = new AndroidDriver(new URL(format("https://{0}:{1}{2}", ltUserName, ltAccessKey, gridUrl)), setCapabilities());
        } catch (MalformedURLException e) {
            throw new Error("Error while setting up Android Driver in cloud", e);
        }
        setupDriverTimeouts();
    }

    private HashMap<String, Object> ltOptions() {
        final var ltOptions = new HashMap<String, Object>();
        ltOptions.put("platformName", "ANDROID");
        ltOptions.put("deviceName", "Galaxy S23");
        ltOptions.put("platformVersion", "13");
        ltOptions.put("app", "lt://APP1016043281711707979395880");
        ltOptions.put("w3c", true);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("autoGrantPermissions", true);
        ltOptions.put("enableBiometricsAuthentication", true);
        ltOptions.put("build", "Appium biometric test suite");
        ltOptions.put("name", "Test fingerprint authentication on login");
        ltOptions.put("plugin", "java-testNG");
        ltOptions.put("visual", true);
        ltOptions.put("console", true);
        ltOptions.put("devicelog", true);
        return ltOptions;
    }

    private DesiredCapabilities setCapabilities() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("lt:options", ltOptions());
        return capabilities;
    }


    public AndroidDriver getAndroidDriver() {
        return this.androidDriver;
    }


    private static UiAutomator2Options uiAutomator2Options() {

        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Pixel_XL_API_33")
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

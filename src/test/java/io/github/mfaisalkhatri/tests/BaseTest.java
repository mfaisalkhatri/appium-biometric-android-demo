package io.github.mfaisalkhatri.tests;

import io.github.mfaisalkhatri.drivers.AndroidDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseTest {

    protected AndroidDriverManager androidDriverManager;

    @Parameters({"deviceType"})
    @BeforeClass(alwaysRun = true)
    public void setup(String deviceType) {
        this.androidDriverManager = new AndroidDriverManager();

        if (deviceType.equalsIgnoreCase("LOCAL")) {
            this.androidDriverManager.createAndroidDriver();
        } else if (deviceType.equalsIgnoreCase("CLOUD")) {
            this.androidDriverManager.createAndroidDriverInCloud();
        } else {
            throw new Error("Device Type param is not defined!");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.androidDriverManager.quitDriver();
    }

}

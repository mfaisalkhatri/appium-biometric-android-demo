package io.github.mfaisalkhatri.tests;

import static org.testng.Assert.assertEquals;

import io.github.mfaisalkhatri.pages.instrumentation.HomePage;
import io.github.mfaisalkhatri.pages.wdio.LoginPage;
import io.github.mfaisalkhatri.pages.wdio.MainPage;
import org.testng.annotations.Test;

public class BiometricAuthTests extends BaseTest {

    @Test
    public void testFailedBiometricAuthenticationUsingLambdaTest () {
        final HomePage homePage = new HomePage (this.androidDriverManager.getAndroidDriver ());
        homePage.performFailedBioMetricAuthenticationOnRealDevice ();

        assertEquals (homePage.getMessageText (), "Authentication Failed");
    }

    @Test
    public void testFingerPrintAuthenticationLogin () {
        final MainPage mainPage = new MainPage (this.androidDriverManager.getAndroidDriver ());
        final LoginPage loginPage = mainPage.openLoginPage ();

        loginPage.performBiometricLogin (1);

        assertEquals (loginPage.getSuccessMessageTitle (), "Success");
        assertEquals (loginPage.getSuccessMessageText (), "You are logged in through Fingerprint!");
    }

    @Test
    public void testSuccessfulBiometricAuthenticationUsingLambdaTest () {
        final HomePage homePage = new HomePage (this.androidDriverManager.getAndroidDriver ());
        homePage.performSuccessBioMetricAuthenticationOnRealDevice ();

        assertEquals (homePage.getMessageText (), "Success");
    }
}
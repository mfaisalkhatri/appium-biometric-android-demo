package io.github.mfaisalkhatri.tests;

import io.github.mfaisalkhatri.pages.LoginPage;
import io.github.mfaisalkhatri.pages.MainPage;
import io.github.mfaisalkhatri.pages.instrumentation.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class BiometricAuthTests extends BaseTest {


    @Test
    public void testFingerPrintAuthenticationLogin()  {
        final MainPage mainPage = new MainPage(this.androidDriverManager.getAndroidDriver());
        final LoginPage loginPage = mainPage.openLoginPage();

        loginPage.performBiometricLogin(1);

        assertEquals(loginPage.getSuccessMessageTitle(), "Success");
        assertEquals(loginPage.getSuccessMessageText(), "You are logged in through Fingerprint!");
    }

    @Test
    public void testSuccessfulBiometricAuthenticationUsingLambdaTest() {
        final HomePage homePage = new HomePage(this.androidDriverManager.getAndroidDriver());
        homePage.performSuccessBioMetricAuthenticationOnRealDevice();

        assertEquals(homePage.getMessageText(), "Success");
    }

    @Test
    public void testFailedBiometricAuthenticationUsingLambdaTest() {
        final HomePage homePage = new HomePage(this.androidDriverManager.getAndroidDriver());
        homePage.performFailedBioMetricAuthenticationOnRealDevice();

        assertEquals(homePage.getMessageText(), "Authentication Failed");
    }
}
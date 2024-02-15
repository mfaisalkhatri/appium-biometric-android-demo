package io.github.mfaisalkhatri.tests;

import io.github.mfaisalkhatri.pages.LoginPage;
import io.github.mfaisalkhatri.pages.MainPage;
import io.github.mfaisalkhatri.pages.instrumentation.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class BiometricAuthTests extends BaseTest {


    @Test
    public void testFingerPrintAuthenticationLogin()  {
        MainPage mainPage = new MainPage(androidDriverManager.getAndroidDriver());
        LoginPage loginPage = mainPage.openLoginPage();

        loginPage.performBioMetricLogin(1);

        assertEquals(loginPage.getSuccessMessageTitle(), "Success");
        assertEquals(loginPage.getSuccessMessageText(), "You are logged in through Fingerprint!");
    }

    @Test
    public void testBiometricAuthenticationUsingLambdaTest() {
        HomePage homePage = new HomePage(androidDriverManager.getAndroidDriver());
        homePage.performBioMetricAuthenticationOnRealDevice();

        assertEquals(homePage.getSuccessMessage(), "Success");
    }
}

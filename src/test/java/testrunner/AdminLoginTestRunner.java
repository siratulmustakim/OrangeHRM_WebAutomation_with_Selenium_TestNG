package testrunner;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.Setup;

import java.io.IOException;

public class AdminLoginTestRunner extends Setup {

    LoginPage loginPage;

    @Test(priority = 1, description = "Admin cannot login with wrong creds")
    public void doWrongLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("wrongname", "wrongpass");

        String txtActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String txtExpected = "Invalid credentials";
        Assert.assertTrue(txtActual.equals(txtExpected));
    }


    @Test(priority = 2, description = "Admin can login with valid creds")
    public void doLogin() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.doLogin(0);
//        Thread.sleep(3000);

        String txtActual = driver.findElement(By.className("oxd-text--h6")).getText();
        String txtExpected = "Dashboard";
        System.out.println(txtActual);
        Assert.assertTrue(txtActual.equals(txtExpected));
    }

}

package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class EmployeeLoginTestRunner extends Setup {



    LoginPage loginPage;
//    JSONObject jsonObject = (JSONObject) Utils.readJSON().get(Utils.readJSON().size() - 1);

    // negative case
    @Test(priority = 1, description = "Employee cannot login with wrong creds")
    public void doWrongLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("wrongname", "wrongpass");

        String txtActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String txtExpected = "Invalid credentials";
        Assert.assertTrue(txtActual.equals(txtExpected));
    }


    @Test(priority = 2, description = "Employee should login with proper creds")
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);

        JSONObject jsonObject = (JSONObject) Utils.readJSON().get(Utils.readJSON().size() - 1);
        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();

        loginPage.doLogin(username, password);

        String txtActual = driver.findElement(By.className("oxd-text--h6")).getText();
        System.out.println(txtActual);
        String txtExpected = "Dashboard";
        Assert.assertTrue(txtActual.equals(txtExpected));
    }

    @Test(priority = 3, description = "Full name of logged in employee should be displayed beside profile icon")
        public void displayFullName() throws InterruptedException, IOException, ParseException {
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        JSONObject jsonObject = (JSONObject) Utils.readJSON().get(Utils.readJSON().size() - 1);
        String firstName = jsonObject.get("firstName").toString();
        String lastName = jsonObject.get("lastName").toString();

        String fullNameExpected = firstName+" "+lastName;
        Thread.sleep(5000);

        String fullNameActual = dashBoardPage.txtName.getText();
        System.out.println(fullNameActual);

        Assert.assertTrue(fullNameActual.equals(fullNameExpected));

        }

}

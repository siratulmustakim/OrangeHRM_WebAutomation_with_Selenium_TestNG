package testrunner;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.MyInfoPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UpdateInfoTestRunner extends Setup {

    MyInfoPage myInfoPage;
    DashBoardPage dashBoardPage;
    LoginPage loginPage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);

        loginPage.doLogin(Utils.readJSON().size() - 1);  // login employee
        dashBoardPage = new DashBoardPage(driver);
        dashBoardPage.itemMenu.get(2).click();  // goto pages.MyInfoPage
        Thread.sleep(2000);  // it takes time to load the page

    }

    @Test(priority = 1, description = "Gender as female should be selected")
    public void updateGender() throws InterruptedException, IOException, ParseException {

        myInfoPage = new MyInfoPage(driver);
        myInfoPage.btnGender.get(1).click();  // gender female

    }

    //////////////

    @Test(priority =  2, description = "O+ blood group should be selected")
    public void updateBloodGrp() throws InterruptedException, IOException, ParseException {

        // select from dropdown// blood grp
        Utils.doScroll(driver);
        Thread.sleep(1000);

        // handling dropdown list having no <select> tag or typing option
        WebElement wb = driver.findElements(By.className("oxd-select-text-input")).get(3);

        Actions actions = new Actions(driver);
        actions.moveToElement(wb).click().perform();
        actions.keyDown(Keys.ARROW_DOWN);
        actions.keyDown(Keys.ARROW_DOWN);
        actions.keyDown(Keys.ARROW_DOWN);
        actions.keyDown(Keys.ARROW_DOWN);
        actions.keyDown(Keys.ARROW_DOWN);  // here comes O+
        actions.click().perform();
        Thread.sleep(3000);

        String txtActual = wb.getText();
        System.out.println(txtActual);
        Assert.assertTrue(txtActual.equals("O+"));

    }

    @Test(priority = 3, description = "Updated info should be saved")
    public void saveInfo() throws InterruptedException {

        myInfoPage.btnSubmit.get(1).click();
        Thread.sleep(1500);

        String txtActual = driver.findElement(By.id("oxd-toaster_1")).getText();
        Assert.assertTrue(txtActual.contains("Success"));

        // logout
        loginPage.doLogout();
    }

}

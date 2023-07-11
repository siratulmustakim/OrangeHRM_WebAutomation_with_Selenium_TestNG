package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class CreateEmployeeTestRunner extends Setup {

    LoginPage loginPage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {

        loginPage = new LoginPage(driver);
        loginPage.doLogin(0); // admin
    }


    // negative case
    @Test(priority = 1, description = "New employee cannot be created without giving info properly")
    public void failToCreateEmployee() throws InterruptedException {

        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        PIMPage pimPage = new PIMPage(driver);
        Faker faker = new Faker();

        dashBoardPage.itemMenu.get(1).click();  // goto PIM
        pimPage.btnPIM.get(2).click();         // click add new emp btn

        pimPage.btncreds.get(1).sendKeys(faker.name().firstName());  // random first name
        pimPage.btncreds.get(3).sendKeys(faker.name().lastName());  // random lastname

        driver.findElement(By.className("oxd-switch-input")).click();  // create login detail btn
        Thread.sleep(2000);

        pimPage.btncreds.get(5).sendKeys(faker.name().username());

        // wrong pass in confirm pass
        pimPage.btncreds.get(6).sendKeys("@userPass1");  // password
        pimPage.btncreds.get(7).sendKeys("@userPass12");   // confirm password

        String txtActual = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String txtExpected = "Passwords do not match";
        Assert.assertTrue(txtActual.equals(txtExpected));
    }


    @Test(priority = 2, description = "New employee can be created")
    public void createEmployee() throws InterruptedException, IOException, ParseException {

        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        PIMPage pimPage = new PIMPage(driver);
        Faker faker = new Faker();

        String firstName= faker.name().firstName();
        String lastName= faker.name().lastName();
        String username= faker.name().username();
        String password = Utils.randomPass();

        dashBoardPage.itemMenu.get(1).click();  // goto PIM
        pimPage.btnPIM.get(2).click();         // click add new emp btn

        pimPage.btncreds.get(1).sendKeys(firstName);  // random first name
        pimPage.btncreds.get(3).sendKeys(lastName);  // random lastname

        String empID = pimPage.btncreds.get(4).getAttribute("value");  // employee IDoxd-input
        System.out.println("emp "+empID);

//        driver.findElement(By.className("oxd-switch-input")).click();  // create login detail btn
        driver.findElement(By.className("oxd-switch-input")).click();  // create login detail btn
        Thread.sleep(2000);

        pimPage.btncreds.get(5).sendKeys(username);
        pimPage.btncreds.get(6).sendKeys(password);  // password
        pimPage.btncreds.get(7).sendKeys(password);   // confirm password

        driver.findElement(By.cssSelector("[type='submit']")).click();
        Thread.sleep(7000);  // submit er por next page load hote time lage


        String txtActual = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
        String txtExpected = "Personal Details";

        Utils.saveInJSON(firstName, lastName, username, empID, password);  // saving in json file

        Assert.assertTrue(txtActual.equals(txtExpected));
    }




}

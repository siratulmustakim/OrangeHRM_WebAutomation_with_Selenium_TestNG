package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.DirectoryPage;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class SearchEmployeeTestRunner extends Setup {


        public SearchEmployeeTestRunner() throws IOException, ParseException {
        }

        @BeforeTest
        public void doLogin() throws IOException, ParseException, InterruptedException {

                LoginPage loginPage = new LoginPage(driver);
                loginPage.doLogin(0); // admin
        }

        int arrayIndex = Utils.readJSON().size() - 1;

        @Test(priority = 1, description = "Search by Wrong ID should results No Records Found")
        public void searchByWrongID() throws InterruptedException {
                DashBoardPage dashBoardPage = new DashBoardPage(driver);
                PIMPage pimPage = new PIMPage(driver);

                dashBoardPage.itemMenu.get(1).click(); // goto PIM

                pimPage.btncreds.get(1).sendKeys("wrong12");
                Thread.sleep(1000);
                pimPage.btnSearch.click();
                Thread.sleep(4000);

//                String txtActual = pimPage.txtRecordFound.get(11).getText();
                String txtActual = driver.findElement(By.xpath("//span[@class=\"oxd-text oxd-text--span\"]")).getText();
                System.out.println(txtActual);
                Assert.assertTrue(txtActual.contains("No Records Found"));


                driver.findElement(By.cssSelector("[type=\"reset\"]")).click(); // reset textBox/ clear
                Thread.sleep(1000);
        }

        @Test(priority = 2, description = "Search by Wrong Name should results No Records Found")
        public void searchByWrongName() throws InterruptedException, IOException, ParseException {
                PIMPage pimPage = new PIMPage(driver);
                JSONObject jsonObject = (JSONObject) Utils.readJSON().get(arrayIndex);
                String firstName = jsonObject.get("firstName").toString();
                String lastName = jsonObject.get("lastName").toString();

                pimPage.hintName.get(0).sendKeys(firstName + lastName+"@#$");
                Thread.sleep(1000);
                pimPage.btnSearch.click();  // search
                Thread.sleep(4000);

                String txtActual = driver.findElement(By.xpath("//span[@class=\"oxd-text oxd-text--span\"]")).getText();
                System.out.println(txtActual);
                Assert.assertTrue(txtActual.contains("No Records Found"));

                driver.findElement(By.cssSelector("[type=\"reset\"]")).click(); // reset textBox/ clear
                Thread.sleep(1000);
        }


// Same ID multiple emp er thakte pare. So error comes sometime.
        @Test(priority = 3, description = "Newly created employee should be found by searching his ID")
        public void searchByID() throws IOException, ParseException, InterruptedException {

                DashBoardPage dashBoardPage = new DashBoardPage(driver);
                PIMPage pimPage = new PIMPage(driver);

                dashBoardPage.itemMenu.get(1).click(); // goto PIM

                JSONObject jsonObject = (JSONObject) Utils.readJSON().get(arrayIndex);
                String empID = jsonObject.get("empID").toString();

                pimPage.btncreds.get(1).sendKeys(empID);
                Thread.sleep(1000);
                pimPage.btnSearch.click();
                Thread.sleep(4000);

//
                String txtActual = driver.findElement(By.xpath("//span[@class=\"oxd-text oxd-text--span\"]")).getText();
                System.out.println(txtActual);
                Assert.assertTrue(txtActual.contains("Found"));
//                Assert.assertTrue(txtActual.contains("Record Found"));


                driver.findElement(By.cssSelector("[type=\"reset\"]")).click(); // reset textBox/ clear
                Thread.sleep(1000);

        }




        @Test(priority = 4, description = "Newly created employee should be found by searching his Name")
        public void searchByName() throws IOException, ParseException, InterruptedException {
                PIMPage pimPage = new PIMPage(driver);
                JSONObject jsonObject = (JSONObject) Utils.readJSON().get(arrayIndex);
                String firstName = jsonObject.get("firstName").toString();
                String lastName = jsonObject.get("lastName").toString();

                pimPage.hintName.get(0).sendKeys(firstName+" "+ lastName);
                Thread.sleep(1000);
                pimPage.btnSearch.click();  // search
                Thread.sleep(7000);

                String txtActual = driver.findElement(By.xpath("//span[@class=\"oxd-text oxd-text--span\"]")).getText();
                System.out.println(txtActual);
                Assert.assertTrue(txtActual.contains("Record Found"));
        }


        @Test(priority = 5, description = "User should logout successfully")
        public void doLogout() throws InterruptedException {
                DirectoryPage directoryPage = new DirectoryPage(driver);
                directoryPage.doLogout();

                Thread.sleep(3000);
                String txtActual = driver.findElement(By.className("orangehrm-login-title")).getText();
                Assert.assertTrue(txtActual.equals("Login"));
        }


}

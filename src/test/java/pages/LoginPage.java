package pages;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class LoginPage {

//    @FindBy(className = "oxd-input--active")  // why this is not working??
//    List<WebElement> txtBox;

    @FindBy(name = "username")
    WebElement txtUsername;

    @FindBy(name = "password")
    WebElement txtpassword;

    @FindBy(css = "[type = submit]")
    WebElement btnSubmit;

    @FindBy(className = "oxd-userdropdown-icon")
    WebElement btnOption;

    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> btnLogout;     //3


    // constructor
    public LoginPage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }

    // login using value from json file
    public void doLogin(int index) throws InterruptedException, IOException, ParseException {

        JSONArray jsonArray = Utils.readJSON();
        JSONObject jsonObject = (JSONObject) jsonArray.get(index);
        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();


        txtUsername.sendKeys(username);
        txtpassword.sendKeys(password);
        btnSubmit.click();
    }

    // login for negative cases
    public void doLogin(String username, String password){
        txtUsername.sendKeys(username);
        txtpassword.sendKeys(password);
        btnSubmit.click();
    }

    public void doLogout(){
        btnOption.click();
        btnLogout.get(3).click();
    }

}

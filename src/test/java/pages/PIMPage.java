package pages;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class PIMPage {

    @FindBy(className = "oxd-button--medium")
    public List<WebElement> btnPIM;  // 2 for add

    @FindBy(className = "oxd-input")
    public List<WebElement> btncreds;   // 1 for empID

    @FindBy(css = "[placeholder = \"Type for hints...\"]")
    public List <WebElement> hintName;          // 0 name

//    @FindBy(className = "orangehrm-left-space")
//    WebElement btnSearch;
    @FindBy(css ="[type=\"submit\"]" )
    public WebElement btnSearch;

    @FindBy(className = "oxd-text--span")
    public List<WebElement> txtRecordFound;  // 11

    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


}

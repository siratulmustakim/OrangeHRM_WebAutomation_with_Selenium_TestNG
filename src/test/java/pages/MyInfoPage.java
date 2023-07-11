package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyInfoPage {

     @FindBy(className = "oxd-radio-wrapper")
   public List<WebElement> btnGender;  // 1 female

     @FindBy(className = "oxd-select-text--arrow")
    public List<WebElement> btnBlood;  // 2  // blood option

    @FindBy(css = "[type = 'submit']")
    public List <WebElement> btnSubmit; // goto 1

    public MyInfoPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

}

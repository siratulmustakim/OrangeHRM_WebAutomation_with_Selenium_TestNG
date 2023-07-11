package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DirectoryPage {

    @FindBy(className = "oxd-userdropdown-icon")
    WebElement btnList;

    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> listElem;   // 3 logout


    public DirectoryPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void doLogout(){
        btnList.click();
        listElem.get(3).click();
    }

}

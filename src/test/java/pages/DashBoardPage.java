package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashBoardPage {

    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> itemMenu;  // 1 for PIM, dashBoard 7, 2 myInfo

    @FindBy(className = "oxd-userdropdown-name")
    public WebElement txtName;

    public DashBoardPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

}

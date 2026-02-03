package locators;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HelperClass;

import java.util.List;

public class MyProfileLocators  {

    WebDriver driver;
    public MyProfileLocators(){this.driver = HelperClass.getDriver();}

    @FindBy(linkText = "Edit Profile")
    private WebElement editProfile;





    @FindBy(linkText = "Back to Dashboard")
    private WebElement backToDashboard;

    public WebElement getEditProfile(){return editProfile;}
    public WebElement getBackToDashboard(){return backToDashboard;}



}

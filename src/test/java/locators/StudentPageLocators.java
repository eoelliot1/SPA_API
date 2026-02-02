package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HelperClass;

import java.util.List;


public class StudentPageLocators  {

    WebDriver driver;
    public StudentPageLocators(){this.driver = HelperClass.getDriver();}


    @FindBy(linkText = "My Profile")
    private WebElement myProfileNavButton;

    @FindBy(linkText = "My Course")
    private WebElement myCourseNavButton;


    public WebElement getMyProfileNavButton(){return myProfileNavButton;}
    public WebElement getMyCourseNavButton(){return  myCourseNavButton;}





}

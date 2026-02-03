package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HelperClass;

import java.util.List;


public class UpdateProfileLocators {
    WebDriver driver;
    public UpdateProfileLocators() {
        this.driver = HelperClass.getDriver();
    }

    @FindBy(id = "studentName")
    private WebElement studentNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveChanges;


    public WebElement getStudentNameField(){return  studentNameField;}
    public WebElement getSaveChanges(){return  saveChanges;}


}

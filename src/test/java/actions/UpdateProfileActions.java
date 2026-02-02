package actions;

import locators.UpdateProfileLocators;
import org.openqa.selenium.support.PageFactory;

import utils.HelperClass;


public class UpdateProfileActions {

    UpdateProfileLocators updateProfileLocators = null;

    public UpdateProfileActions(){
        this.updateProfileLocators = new UpdateProfileLocators();

        PageFactory.initElements(HelperClass.getDriver(), updateProfileLocators);

    }

    public void clearStudentName(){updateProfileLocators.getStudentNameField().clear();}
    public void updateStudentName(String name){updateProfileLocators.getStudentNameField().sendKeys(name);}
    public void saveChanges(){updateProfileLocators.getSaveChanges().click();}
}

package actions;
import locators.MyProfileLocators;
import org.openqa.selenium.support.PageFactory;

import utils.HelperClass;


public class MyProfilePageActions {

   MyProfileLocators myProfileLocators = null;

    public MyProfilePageActions(){
        this.myProfileLocators = new MyProfileLocators();

        PageFactory.initElements(HelperClass.getDriver(), myProfileLocators);

    }

    public void clickEditProfile(){myProfileLocators.getEditProfile().click();}
    public void clickBackToDashboard(){myProfileLocators.getBackToDashboard().click();}
}

package actions;
import locators.StudentPageLocators;
import org.openqa.selenium.support.PageFactory;

import utils.HelperClass;


public class StudentPageActions {

    StudentPageLocators studentPageLocators = null;

    public StudentPageActions(){
        this.studentPageLocators = new StudentPageLocators();

        PageFactory.initElements(HelperClass.getDriver(), studentPageLocators);

    }

    public void clickMyProfileButton(){studentPageLocators.getMyProfileNavButton().click();}
    public  void clickMyCoursesButton (){studentPageLocators.getMyCourseNavButton().click();}

}

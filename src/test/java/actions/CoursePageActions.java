package actions;

import locators.CourseLocators;
import locators.LoginPageLocators;
import org.openqa.selenium.support.PageFactory;
import utils.HelperClass;

public class CoursePageActions {
    CourseLocators courseLocators = new CourseLocators();

    public void clickCourseButton() {
        courseLocators.getCourseButton().click();
    }



//    public void getCourseIndexHeader() {
//        courseLocators.getCourseIndexHeader();
//    }
}
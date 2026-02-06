package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@DefaultUrl("http://localhost:8091/students/profile")


public class MyProfile extends PageObject {

    @FindBy (css=".me-2:nth-child(1)")
    private WebElementFacade myProfileButton;


    @FindBy (css=".btn-outline-light:nth-child(2)")
    private WebElementFacade myCoursesButton;

    @FindBy(xpath="//h3[contains(.,'Available Courses')]")
    private WebElementFacade coursesList;


    @FindBy (css=".btn-warning")
    private WebElementFacade editProfileButton;

    @FindBy (css=".btn-secondary")
    private WebElementFacade backToDashBoardButton;


    public void clickMyProfile(){ myProfileButton.click();}
    public void clickMyCourses(){ myCoursesButton.click();}

    public void clickEditProfile(){editProfileButton.click();}
    public void clickBackToDashBoard(){backToDashBoardButton.click();}



}

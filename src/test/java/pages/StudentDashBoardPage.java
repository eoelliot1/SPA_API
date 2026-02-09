package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8091/")

public class StudentDashBoardPage extends PageObject{

    @FindBy (css=".me-2:nth-child(1)")
    private WebElementFacade myProfileButton;


    @FindBy (css=".btn-outline-light:nth-child(2)")
    private WebElementFacade myCoursesButton;

    public void clickMyProfile(){ myProfileButton.click();}
    public void clickMyCourses(){ myCoursesButton.click();}






}

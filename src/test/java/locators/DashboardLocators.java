package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardLocators {

    /*
            Notice:
            - Didn't seem necessary to add a whole new class for DashboardActions
            so everything is done in this class.
     */

    @FindBy(xpath = "/html/body/nav/div/div/a[1]")
    private WebElement coursesNavigationButton;

    public WebElement getCoursesNavigationButton() {return coursesNavigationButton;}

}

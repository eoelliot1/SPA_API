package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@DefaultUrl("http://localhost:8091/students/courses")

public class StudentCoursesPage extends PageObject {

    @FindBy(xpath="//h3[contains(.,'Available Courses')]")
    private WebElementFacade coursesList;

    public boolean isCourseListVisible(){
        return waitForCondition().until(webDriver -> coursesList.isVisible());
    }
}

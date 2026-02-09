package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;


@DefaultUrl("http://localhost:8091/courses/3")
public class CourseDetailPage extends PageObject {

    @FindBy (xpath="//h2[contains(.,'Course Details')]")
    private WebElementFacade courseDetails;

    @FindBy (xpath="//p[contains(.,'Name: Java Development')]")
    private WebElementFacade courseName;

    @FindBy (xpath="//p[contains(.,'ID: 3')]")
    private WebElementFacade courseId;

    public boolean isCourseDetailsVisible(){
        return waitForCondition().until(webDriver -> courseDetails.isVisible());
    }

    public boolean isCourseNameVisible(){
        return waitForCondition().until(webDriver -> courseName.isVisible());
    }

    public boolean isCourseIdVisible(){
        return waitForCondition().until(webDriver -> courseId.isVisible());
    }

}

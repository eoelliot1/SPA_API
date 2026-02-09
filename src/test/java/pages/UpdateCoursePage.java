package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8091/courses/2/edit")
public class UpdateCoursePage extends PageObject {
    @FindBy(xpath = "//h2[normalize-space()='Update Course']")
    private WebElementFacade updateCoursePage;

    @FindBy(name = "courseName")
    private WebElementFacade courseNameInput;

    @FindBy(xpath = "//button[normalize-space()='Update']")
    private WebElementFacade updateButton;

    @FindBy(xpath = "/html/body/div/form/a")
    private WebElementFacade cancelButton;


    public void updateCourseName(String newName) {
        courseNameInput.clear();
        courseNameInput.type(newName);
    }

    public boolean isUpdateCoursePageVisible() {
        return waitForCondition().until(webDriver -> updateCoursePage.isVisible());
    }

    public void openUpdateCoursePage() {
        open();
    }

    public void clickUpdateButton() {
        updateButton.click();

    }
    public void clickCancelButton() {
        cancelButton.click();

    }


}

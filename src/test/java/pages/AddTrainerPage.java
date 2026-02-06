package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddTrainerPage extends PageObject {

    public AddTrainerPage() {
        super();
    }

    public AddTrainerPage(WebDriver driver) {
        setDriver(driver); // allows injection in step def
    }

    @FindBy(name = "trainerName")
    private WebElementFacade trainerNameInput;

    @FindBy(name = "courseId")
    private WebElementFacade courseDropdown;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade saveButton;

    public void enterTrainerName(String name) {
        trainerNameInput.type(name);
    }

    public void selectCourse(String courseName) {
        courseDropdown.selectByVisibleText(courseName);
    }

    public void saveTrainer() {
        saveButton.click();
    }
}
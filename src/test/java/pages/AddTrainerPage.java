package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8091/trainers/new-trainer")
public class AddTrainerPage extends PageObject {




    @FindBy(name = "trainerName")
    private WebElementFacade trainerNameInput;

    @FindBy(name = "courseId")
    private WebElementFacade courseDropdown;

    @FindBy(xpath = "/html/body/div/form/button")
    private WebElementFacade saveButton;

    public void enterTrainerName(String name) {
        trainerNameInput.type(name);
    }

    public void selectCourse(String courseName) {
        courseDropdown.selectByVisibleText(courseName);
    }

    public void saveTrainer() {
        saveButton.waitUntilClickable().click();
    }
}
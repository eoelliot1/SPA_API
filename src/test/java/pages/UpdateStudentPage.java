package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DefaultUrl("http://localhost:8091/students/update-student/1")


public class UpdateStudentPage extends PageObject{

    @FindBy (id="studentName")
    private WebElementFacade studentNameField;

    @FindBy(css=".btn-primary")
    private WebElementFacade saveButton;

    @FindBy (css=".btn-secondary")
    private WebElementFacade cancelButton;


    public void clickStudentNameField(){studentNameField.type("Tor");}
    public void clickSaveButton(){saveButton.click();}
    public void clickCancelButton(){cancelButton.click();}
}

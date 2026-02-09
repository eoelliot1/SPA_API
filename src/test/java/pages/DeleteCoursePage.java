package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8091/courses/")
public class DeleteCoursePage extends PageObject {
    @FindBy(xpath = "//Button[normalize-space()='Delete']")
    private WebElementFacade deleteButton;

    public void clickDelete() {
        deleteButton.click();
    }

    public void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }


    public void dismissAlert() {
        getDriver().switchTo().alert().dismiss();
    }

}

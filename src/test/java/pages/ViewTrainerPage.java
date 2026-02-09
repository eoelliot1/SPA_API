package pages;

import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DefaultUrl("http://localhost:8091/view-trainer")
public class ViewTrainerPage extends PageObject {

    @FindBy(xpath = "//h1[contains(text(),'Trainer Details')]")
    private WebElementFacade pageTitle;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/span")
    private WebElementFacade trainerNameField;

    @FindBy(xpath="/html/body/div[2]/div/div/div/div[3]/a")
    private WebElementFacade backToListButton;

    public void clickBackToList() {
        backToListButton.click();
    }


    public String getTrainerName() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(trainerNameField));
        return trainerNameField.getText().trim();
    }

    public boolean isPageLoaded() {
        return pageTitle.isVisible();
    }
}

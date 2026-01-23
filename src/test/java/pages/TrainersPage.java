package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/trainers")
public class TrainersPage extends PageObject {

    @FindBy(css = ".trainer-item")
    private List<WebElementFacade> trainers;

    @FindBy(css = ".btn-remove")
    private List<WebElementFacade> removeButtons;

    public boolean isTrainerDisplayed(String trainerName) {
        return trainers.stream().anyMatch(t -> t.getText().contains(trainerName));
    }

    public void removeTrainer(String trainerName) {
        for (WebElementFacade trainer : trainers) {
            if (trainer.getText().contains(trainerName)) {
                trainer.findBy(".btn-remove").click();
                waitForAlert();
                break;
            }
        }
    }

    public void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText() {
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }
}

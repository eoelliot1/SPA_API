package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/trainers")
public class TrainersPage extends PageObject {



    private static final String ALL_TRAINERS_TABLE_XPATH = "//h2[contains(text(),'All trainers detail')]/following::table[1]//tbody/tr";
    private static final By TRAINER_ROWS =
            By.xpath("//h2[text()='All trainers detail']/following-sibling::table//tbody/tr");

    @FindBy(xpath = "//h2[text()='My Details']/following-sibling::table//tbody/tr")
    private WebElementFacade myDetailsRow;

    @FindBy(xpath = ALL_TRAINERS_TABLE_XPATH)
    private List<WebElementFacade> allTrainerRows;


    @FindBy(name = "query")
    private WebElementFacade searchInput;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElementFacade searchButton;


    @FindBy(linkText = "Add new Trainer")
    private WebElementFacade addNewTrainerButton;




    public void waitForTrainerRows(int expectedMinimum) {
        waitFor(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.xpath("//h2[text()='All trainers detail']/following-sibling::table//tbody/tr"),
                        expectedMinimum - 1
                )
        );
    }



    public void clickAddNewTrainer() {
        addNewTrainerButton.click();
    }

    public void clickViewTrainer(String trainerName) {
        for (WebElementFacade row : allTrainerRows) {
            if (row.getText().contains(trainerName)) {
                row.findBy(".//a[contains(text(),'View Trainer')]").click();
                break;
            }
        }
    }

    public void waitForTrainerTableToLoad() {
        withTimeoutOf(Duration.ofSeconds(15))
                .waitForPresenceOf(TRAINER_ROWS);
    }

    public boolean isTrainerListed(String trainerName) {
        waitForTrainerTableToLoad();
        return findAll(TRAINER_ROWS)
                .stream()
                .anyMatch(row -> row.getText().contains(trainerName));
    }

    public int getTrainerCount() {
        waitForTrainerTableToLoad();
        return findAll(TRAINER_ROWS).size();
    }

    public void searchForCoursesLongerThan(int days) {
        searchInput.type(String.valueOf(days));
        searchButton.click();
        waitForTrainerTableToLoad();
    }
}
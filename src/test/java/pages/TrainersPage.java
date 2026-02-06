package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/trainers") // keep this
public class TrainersPage extends PageObject {

    public TrainersPage() {
        super();
    }

    public TrainersPage(WebDriver driver) {
        setDriver(driver); // allows us to inject driver in step def
    }

    // === My Details table ===
    @FindBy(xpath = "//h2[text()='My Details']/following-sibling::table//tbody/tr")
    private WebElementFacade myDetailsRow;

    // === All Trainers table ===
    @FindBy(xpath = "//h2[text()='All trainers detail']/following-sibling::table//tbody/tr")
    private List<WebElementFacade> allTrainerRows;

    // === Search ===
    @FindBy(name = "query")
    private WebElementFacade searchInput;

    @FindBy(css = "form button[type='submit']")
    private WebElementFacade searchButton;

    // === Add Trainer ===
    @FindBy(linkText = "Add new Trainer")
    private WebElementFacade addNewTrainerButton;

    public boolean isTrainerListed(String trainerName) {
        List<WebElementFacade> rows = findAll("//h2[text()='All trainers detail']/following-sibling::table//tbody/tr");
        return rows.stream().anyMatch(row -> row.getText().contains(trainerName));
    }

    public int getTrainerCount() {
        List<WebElementFacade> rows = findAll("//h2[text()='All trainers detail']/following-sibling::table//tbody/tr");
        System.out.println(rows);
        return rows.size();
    }

    public void searchForCoursesLongerThan(int days) {
        searchInput.type(String.valueOf(days));
        searchButton.click();
        withTimeoutOf(Duration.ofSeconds(10)).waitFor(allTrainerRows);
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
}
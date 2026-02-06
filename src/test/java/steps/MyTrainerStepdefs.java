package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddTrainerPage;
import pages.TrainersPage;

import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import pages.TrainersPage;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.findAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyTrainerStepdefs {

    @Managed
    WebDriver driver;

    TrainersPage trainersPage;
    AddTrainerPage addTrainerPage;

    @Then("I should see all available trainers listed")
    public void i_should_see_all_available_trainers_listed() {
        trainersPage = new TrainersPage();
        trainersPage.setDriver(driver);
       trainersPage.open();
        assertTrue(trainersPage.getTrainerCount() > 0);
    }




    @Then("I should see trainers assigned to long courses")
    public void i_should_see_trainers_assigned_to_long_courses() {
        trainersPage = new TrainersPage();
        trainersPage.setDriver(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean trainersFound = wait.until(d -> {
            // Re-fetch the rows each time during wait
            int count = trainersPage.getTrainerCount();
            System.out.println("Current trainer count: " + count);
            return count > 0;
        });

        assertTrue(trainersFound, "No trainers found for long courses after waiting!");
    }

    @Then("I should see no trainers listed")
    public void i_should_see_no_trainers_listed() {
        assertTrue(trainersPage.getTrainerCount() == 0);
    }

    // ================= ADD =================
    @When("I click {string}")
    public void i_click(String button) {
        trainersPage = new TrainersPage();
        trainersPage.setDriver(driver);
        if (button.equalsIgnoreCase("Add New Trainer")) {
            trainersPage.clickAddNewTrainer();
        }
    }

    @Then("I should be on the add trainer page")
    public void i_should_be_on_the_add_trainer_page() {
        addTrainerPage = new AddTrainerPage();
        addTrainerPage.setDriver(driver);
        assertTrue(addTrainerPage.getTitle().contains("Trainer"));
    }

    @And("I enter trainer name {string}")
    public void i_enter_trainer_name(String name) {
        addTrainerPage = new AddTrainerPage();
        addTrainerPage.setDriver(driver);
        addTrainerPage.enterTrainerName(name);
    }

    @And("I select course {string}")
    public void i_select_course(String course) {
        addTrainerPage.selectCourse(course);
    }

    @And("I click Save Trainer")
    public void i_click_save_trainer() {
        addTrainerPage.saveTrainer();
    }

    @Then("I should see trainer {string} listed")
    public void i_should_see_trainer_listed(String name) {
        trainersPage = new TrainersPage();
        trainersPage.setDriver(driver);
        driver.get("http://localhost:8091/trainers");
        assertTrue(trainersPage.isTrainerListed(name));
    }

    // ================= EDIT =================
    @Given("there is an existing trainer named {string}")
    public void there_is_an_existing_trainer_named(String name) {
        trainersPage = new TrainersPage();
        trainersPage.setDriver(driver);
        driver.get("http://localhost:8091/trainers");
        assertTrue(trainersPage.isTrainerListed(name));
    }

    @When("I click {string} for trainer {string}")
    public void i_click_for_trainer(String action, String trainerName) {
        trainersPage = new TrainersPage();
        trainersPage.setDriver(driver);
        if (action.equalsIgnoreCase("Edit")) {
            trainersPage.clickViewTrainer(trainerName);
        }
    }

    @And("I update trainer name to {string}")
    public void i_update_trainer_name_to(String newName) {
        addTrainerPage = new AddTrainerPage();
        addTrainerPage.setDriver(driver);
        addTrainerPage.enterTrainerName(newName);
    }

    @And("I change course to {string}")
    public void i_change_course_to(String courseName) {
        addTrainerPage.selectCourse(courseName);
    }

    @And("I click Update Trainer")
    public void i_click_update_trainer() {
        addTrainerPage.saveTrainer(); // same button
    }

    @Then("I should be redirected to the trainers page")
    public void i_should_be_redirected_to_the_trainers_page() {
        assertTrue(driver.getCurrentUrl().contains("/trainers"));
    }

//    @When("I search for courses longer than {int} days")
//    public void i_search_for_courses_longer_than_days(int days) {
//        trainersPage = new TrainersPage();
//        trainersPage.setDriver(driver);
//        driver.get("http://localhost:8091/trainers"); // navigate explicitly
//        trainersPage.searchForCoursesLongerThan(days);
//    }

//    @When("I search for courses longer than {int} days")
//    public void iSearchForCoursesLongerThanDays(int days) {
//       trainersPage.open();
//       trainersPage.searchForCoursesLongerThan(days);
//    }

    @When("I search for courses longer than {string} days")
    public void iSearchForCoursesLongerThanDays(String daysString) {
        trainersPage.open();

        trainersPage.searchForCoursesLongerThan(Integer.parseInt(daysString));

        trainersPage.waitForCondition()
                .withTimeoutOf(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .until(driver -> {
                    List<WebElementFacade> rows = findAll(
                            "//h2[text()='All trainers detail']/following-sibling::table//tbody/tr"
                    );
                    return rows.size() > 0;
                });
    }
}
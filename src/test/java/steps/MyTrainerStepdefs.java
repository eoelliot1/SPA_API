package steps;


import io.cucumber.java.en.*;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddTrainerPage;
import pages.TrainersPage;


import io.cucumber.java.en.When;

import pages.ViewTrainerPage;

import java.time.Duration;



import static junit.framework.TestCase.assertEquals;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyTrainerStepdefs {

    @Managed
    WebDriver driver;

    TrainersPage trainersPage;
    AddTrainerPage addTrainerPage;
    ViewTrainerPage viewTrainerPage;

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
            int count = trainersPage.getTrainerCount();
            return count > 0;
        });

        assertTrue(trainersFound, "No trainers found for long courses after waiting!");
    }

    @Then("I should see no trainers listed")
    public void i_should_see_no_trainers_listed() {

        boolean noCoursesMessageShown =
                driver.getPageSource().contains("No courses found");

        assertTrue(
                noCoursesMessageShown,
                "Expected 'No courses found' message, but it was not displayed"
        );
    }


    @When("I click {string}")
    public void i_click(String button) {
        trainersPage.open();
        trainersPage.clickAddNewTrainer();

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
        assertThat(trainersPage.isTrainerListed(name))
                .withFailMessage("Trainer " + name + " was not found in the list.")
                .isTrue();
    }


    @When("I search for courses longer than {string} days")
    public void iSearchForCoursesLongerThanDays(String daysString) {
        trainersPage.open();

        trainersPage.searchForCoursesLongerThan(Integer.parseInt(daysString));


    }

    @When("I click Add New Trainer")
    public void iClickAddNewTrainer() {
        addTrainerPage = new AddTrainerPage();
        addTrainerPage.open();

    }

    @Given("I am on the trainers page")
    public void iAmOnTheTrainersPage() {
        trainersPage.open();
    }

    @And("there is an existing trainer named {string}")
    public void thereIsAnExistingTrainerNamed(String trainerName) {
        assertTrue(trainersPage.isTrainerListed(trainerName),
                "Trainer " + trainerName + " does not exist on the trainers page.");
    }

    @When("I click {string} for trainer {string}")
    public void iClickForTrainer(String action, String trainerName) {
        if (action.equalsIgnoreCase("View Trainer")) {
            trainersPage.clickViewTrainer(trainerName);
        }
    }

    @Then("I should be redirected to the view trainer page")
    public void iShouldBeRedirectedToTheViewTrainerPage() {
        viewTrainerPage = new ViewTrainerPage();
        viewTrainerPage.setDriver(driver);
        assertTrue(driver.getCurrentUrl().contains("/view-trainer"),
                "Not redirected to view trainer page.");
    }

    @And("I should see trainer name {string}")
    public void iShouldSeeTrainerName(String trainerName) {
        viewTrainerPage = new ViewTrainerPage();
        viewTrainerPage.setDriver(driver);
        assertTrue(viewTrainerPage.getTrainerName().equals(trainerName),
                "Expected trainer name: " + trainerName + " but found: " + viewTrainerPage.getTrainerName());
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String arg0) {
        viewTrainerPage.clickBackToList();


    }

    @Then("I should see the trainers list")
    public void iShouldSeeTheTrainersList() {

        assertEquals("http://localhost:8091/courses/", getDriver().getCurrentUrl());
    }
}

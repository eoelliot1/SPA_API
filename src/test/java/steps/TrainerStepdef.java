package steps;

import actions.LoginPageActions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import actions.TrainerPageActions;
import locators.LoginPageLocators;
import locators.TrainerPageLocators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperClass;

import java.time.Duration;
import java.util.List;



public class TrainerStepdef {
    WebDriver driver = HelperClass.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(HelperClass.TIMEOUT));
    TrainerPageLocators trainerLocators= new TrainerPageLocators();

    LoginPageActions loginPageActions = new LoginPageActions();
    LoginPageLocators loginPageLocators;
    TrainerPageActions trainerPageActions = new TrainerPageActions();

    public TrainerStepdef() {

        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(driver, loginPageLocators);
        PageFactory.initElements(driver, trainerLocators);
    }


    @Then("I should see all available trainers listed")
    public void iShouldSeeAllAvailableTrainersListed() {

        trainerPageActions.navigateToTrainersPage();

        // Simple assertion
        Assert.assertTrue(driver.getCurrentUrl().contains("/trainers"));

//        Assert.assertTrue("Trainers table is not visible", trainerPageActions.isTrainersTableVisible());
//        List<WebElement> rows = trainerPageActions.getAllTrainerRows();
//        Assert.assertTrue("No trainers found", rows.size() > 0);

    }

    @When("I search for courses longer than {string} days")
    public void iSearchForCoursesLongerThanDays(String days) {
        trainerPageActions.navigateToTrainersPage(); // optional if not already on page
        trainerPageActions.searchCoursesLongerThan(days);
    }


    @Then("I should see trainers assigned to long courses")
    public void iShouldSeeTrainersAssignedToLongCourses() {
        var rows = trainerPageActions.getTrainerLocators().getTrainerRows();
        Assert.assertTrue("No trainers found for long courses", rows.size() > 0);
    }

    @And("I enter trainer name {string}")
    public void iEnterTrainerName(String name) {
        trainerPageActions.enterTrainerName(name);
    }

    @When("I click Add New Trainer")
    public void iClickAddNewTrainer() {
        trainerPageActions.goToTrainersPage();
        trainerPageActions.clickAddTrainer();
    }

    @And("I select course {string}")
    public void iSelectCourse(String courseName) {
        trainerPageActions.selectCourse(courseName);
    }

    @And("I click Save Trainer")
    public void iClickSaveTrainer() {
        trainerPageActions.saveTrainer();
    }

//    @Then("I should see trainer {string} listed")
//    public void iShouldSeeTrainerListed(String trainerName) {
//        var rows = trainerPageActions.getTrainerPageLocators().getTrainerRows();
//
//        boolean found = rows.stream()
//                .anyMatch(row -> row.getText().contains(trainerName));
//        Assert.assertTrue("Trainer " + trainerName + " not found in the list", found);
//    }

    @When("I click {string}")
    public void iClick(String buttonName) {
        trainerPageActions.goToTrainersPage();
        trainerPageActions.clickAddNewTrainer();
    }

    @Then("I should be on the add trainer page")
    public void iShouldBeOnTheAddTrainerPage() {
        Assert.assertTrue(
                "Not on Add Trainer page",
                trainerPageActions.isOnAddTrainerPage()
        );
    }

    @Then("I should see trainer {string} listed")
    public void iShouldSeeTrainerListed(String trainerName) {

            Assert.assertTrue(
                    "Trainer not found in list: " + trainerName,
                    trainerPageActions.isTrainerListed(trainerName)
            );
    }

    @Then("I should see no trainers listed")
    public void iShouldSeeNoTrainersListed() {
        Assert.assertTrue(
                "Expected 'No courses found' message",
                trainerPageActions.isNoCoursesMessageDisplayed()
        );
    }

    @Given("there is an existing trainer named {string}")
    public void thereIsAnExistingTrainerNamed(String trainerName) {

            trainerPageActions.goToTrainersPage();
            Assert.assertTrue(
                    "Trainer not found: " + trainerName,
                    trainerPageActions.isTrainerListed(trainerName)
            );
    }

    @When("I click {string} for trainer {string}")
    public void iClickForTrainer(String button, String trainerName) {
        if (button.equalsIgnoreCase("Edit")) {
            // Wait a moment for the table to be fully loaded
            try {
                Thread.sleep(1000); // Small wait to ensure page is stable
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Refresh the page elements before clicking
            PageFactory.initElements(driver, trainerLocators);

            trainerPageActions.clickEditTrainer(trainerName);
        }
    }

    @And("I update trainer name to {string}")
    public void iUpdateTrainerNameTo(String newName) {
        trainerPageActions.updateTrainerName(newName);
    }

    @And("I change course to {string}")
    public void iChangeCourseTo(String courseName) {
        trainerPageActions.changeCourse(courseName);
    }

    @Then("I should be redirected to the trainers page")
    public void iShouldBeRedirectedToTheTrainersPage() {
        Assert.assertTrue(
                HelperClass.getDriver().getCurrentUrl().contains("/trainers")
        );
    }

    @And("I should see course {string} assigned")

    public void iShouldSeeCourseAssigned(String courseName) {
        Assert.assertTrue(
                "Course not found",
                trainerPageActions.isCourseAssigned1("John Senior Trainer", courseName)
        );
    }

    @And("I click Update Trainer")
    public void iClickUpdateTrainer() {
        trainerPageActions.clickUpdateTrainer();

    }

    @And("I should see course {string} assigned for trainer {string}")
    public void iShouldSeeCourseAssignedForTrainer(String expectedCourse, String trainerName) {

        List<WebElement> rows = trainerPageActions.getTrainerRows();

        boolean found = rows.stream().anyMatch(row -> {
            String name = row.findElement(By.xpath("./td[1]")).getText();
            String course = row.findElement(By.xpath("./td[3]")).getText();
            return name.equals(trainerName) && course.equals(expectedCourse);
        });

        Assert.assertTrue("Trainer " + trainerName + " does not have course " + expectedCourse, found);
    }


//    @When("I click {string}")
//    public void iClick(String buttonName) {
//        if(buttonName.equals("Add New Trainer")) {
//            // Click the "Add New Trainer" button using your locator
//            trainerPageLocators.getAddTrainerButton().click();
//        }

    }



//    WebDriver driver = HelperClass.getDriver(); // ✅ Use driver from HelperClass
//    TrainerPageActions trainerActions = new TrainerPageActions(driver);
//    TrainerPageLocators trainerLocators;
//
//
//
//
//
//    @And("I navigate to the trainers page")
//    public void iNavigateToTheTrainersPage() {
//        trainerActions.navigateToTrainersPage();
//
//    }




//
//    @Then("I should see all available trainers listed")
//    public void iShouldSeeAllAvailableTrainersListed() {
//        Assert.assertTrue("Trainers table is not visible", trainerActions.isTrainersTableVisible());
//        List<WebElement> rows = trainerActions.getAllTrainerRows();
//        Assert.assertTrue("No trainers found", rows.size() > 0);
//    }
//
//    @Then("I should see trainers with their course names displayed")
//    public void iShouldSeeTrainersWithTheirCourseNamesDisplayed() {
//        List<WebElement> courseCells = trainerActions.getTrainerCourseCells();
//        for (WebElement cell : courseCells) {
//            String text = cell.getText();
//            Assert.assertTrue("Trainer course name is missing", text != null && !text.isEmpty());
//        }
//    }


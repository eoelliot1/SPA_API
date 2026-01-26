package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import pages.CoursePage;
import pages.TrainersPage;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TrainersStepdefs {

    @Managed
    CoursePage coursePage;

    @Managed
    TrainersPage trainersPage;

    @Given("I am logged in as a Trainer and there are courses in the system")
    public void iAmLoggedInAsATrainerAndThereAreCoursesInTheSystem() {
        trainersPage.navigateToCoursesPage();
    }

    @When("I search for a course named {string} on the {string} page")
    public void iSearchForACourseNamedOnThePage(String courseName, String arg1) {
        trainersPage.searchForCourse(courseName);
    }

    @Then("I should see the course {string} displayed in the search results")
    public void iShouldSeeTheCourseDisplayedInTheSearchResults(String courseName) {
        assertTrue(
                "Expected course to be displayed: " + courseName,
                trainersPage.isCourseDisplayed(courseName)
        );
    }

    @Then("I should see a message indicating that no courses match the search")
    public void iShouldSeeAMessageIndicatingThatNoCoursesMatchTheSearch() {
        String alertText = trainersPage.getAlertText();
        System.out.println("Alert message: " + alertText);
        trainersPage.acceptAlert();
    }

    @Given("I am logged in as a Trainer and I am assigned to the course {string}")
    public void iAmLoggedInAsATrainerAndIAmAssignedToTheCourse(String courseName) {
        trainersPage.navigateToCoursesPage();
        trainersPage.assignToCourse(courseName);
        trainersPage.acceptAlert();
    }

    @When("I choose to remove myself from {string} on the {string} page")
    public void iChooseToRemoveMyselfFromOnThePage(String courseName, String arg1) {
        trainersPage.removeAssignmentFromCourse(courseName);
    }

    @Then("I should see a confirmation that I am no longer assigned to the course")
    public void iShouldSeeAConfirmationThatIAmNoLongerAssignedToTheCourse() {
        String alertText = trainersPage.getAlertText();
        System.out.println("Confirmation message: " + alertText);
        trainersPage.acceptAlert();
    }

    @Given("I am logged in as a Trainer and I am not assigned to the course {string}")
    public void iAmLoggedInAsATrainerAndIAmNotAssignedToTheCourse(String arg0) {
        trainersPage.navigateToCoursesPage();
    }

    @When("I attempt to remove myself from {string} on the {string} page")
    public void iAttemptToRemoveMyselfFromOnThePage(String courseName, String arg1) {
        trainersPage.removeAssignmentFromCourse(courseName);
    }

    @Then("I should see an error message indicating I am not assigned to that course")
    public void iShouldSeeAnErrorMessageIndicatingIAmNotAssignedToThatCourse() {
        String alertText = trainersPage.getAlertText();
        System.out.println("Error message: " + alertText);
        trainersPage.acceptAlert();
    }
}

package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import pages.CoursePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseStepdefs {

    @Managed
    CoursePage coursePage;

    private String currentRole;

    // ------------------------------
    // AUTH / SETUP
    // ------------------------------
    @Given("I am logged in as a {string} and there are courses in the system")
    public void iAmLoggedInAsARoleAndThereAreCourses(String role) {
        currentRole = role;
        coursePage.openCoursesPage();
    }

    @Given("I am logged in as a {string} and I am not enrolled or assigned to the course {string}")
    public void iAmNotEnrolledOrAssigned(String role, String courseName) {
        currentRole = role;
        coursePage.openCoursesPage();
    }

    @Given("I am logged in as a {string} and I am enrolled or assigned to the course {string}")
    public void iAmAlreadyEnrolledOrAssigned(String role, String courseName) {
        currentRole = role;
        coursePage.openCoursesPage();
        coursePage.addUserToCourse(courseName);
        coursePage.acceptAlert();
    }

    // ------------------------------
    // READ
    // ------------------------------
    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String pageName) {
        coursePage.openCoursesPage();
    }

    @When("I search for a course named {string}")
    public void iSearchForACourseNamed(String courseName) {
        coursePage.searchForCourse(courseName);
    }

    @Then("I should see {string} displayed in the search results")
    public void iShouldSeeCourseDisplayed(String courseName) {
        assertTrue(
                coursePage.isCourseDisplayed(courseName),
                "Expected course to be displayed: " + courseName
        );
    }

    @Then("I should see that no courses match the search")
    public void iShouldSeeNoCoursesMatchSearch() {
        System.out.println(coursePage.getAlertText());
        coursePage.acceptAlert();
    }

    // ------------------------------
    // CREATE
    // ------------------------------
    @When("I enrol or assign myself to the course {string}")
    public void iEnrolOrAssignMyself(String courseName) {
        coursePage.addUserToCourse(courseName);
    }

    @Then("I should see a confirmation message")
    public void iShouldSeeConfirmationMessage() {
        System.out.println(coursePage.getAlertText());
        coursePage.acceptAlert();
    }

    // ------------------------------
    // DELETE
    // ------------------------------
    @When("I remove myself from the course {string}")
    public void iRemoveMyselfFromCourse(String courseName) {
        coursePage.removeUserFromCourse(courseName);
    }

    @Then("I should see an error message indicating I am not enrolled or assigned to that course")
    public void iShouldSeeNotEnrolledError() {
        System.out.println(coursePage.getAlertText());
        coursePage.acceptAlert();
    }

    @Then("I should see course {string} listed")
    public void iShouldSeeCourseListed(String courseName) {
        boolean displayed = coursePage.isCourseDisplayed(courseName);
        assertTrue(displayed, "Expected course to be displayed: " + courseName);
    }

    @Then("I should see no courses listed")
    public void iShouldSeeNoCoursesListed() {
        boolean anyCoursesDisplayed = coursePage.isCourseDisplayed("");
        boolean noCourses = coursePage.getCourses().isEmpty();
        assertTrue(noCourses, "Expected no courses to be displayed");
    }
}

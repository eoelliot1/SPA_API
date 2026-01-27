package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import pages.CoursePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseStepdefs {

    @Managed
    LoginPage loginPage;

    @Managed
    CoursePage coursePage;

    private String currentRole;

    // ------------------------------
    // REUSABLE LOGIN LOGIC
    // ------------------------------
    private void performLogin(String role) {
        loginPage.open();
        loginPage.enterEmail(role.toLowerCase() + "@sparta.com");
        loginPage.enterPassword("password123");
        loginPage.clickSignIn();
    }

    // ------------------------------
    // AUTH / SETUP
    // ------------------------------
    @Given("I am logged in as a {string} and there are courses in the system")
    public void iAmLoggedInAsARoleAndThereAreCourses(String role) {
        this.currentRole = role;
        performLogin(role);
    }

    @Given("I am logged in as a {string} and I am not enrolled or assigned to the course {string}")
    public void iAmNotEnrolledOrAssigned(String role, String courseName) {
        this.currentRole = role;
        performLogin(role);
    }

    @Given("I am logged in as a {string} and I am enrolled or assigned to the course {string}")
    public void iAmAlreadyEnrolledOrAssigned(String role, String courseName) {
        this.currentRole = role;
        performLogin(role);
        coursePage.addUserToCourse(courseName);
        coursePage.acceptAlert();
    }

    // ------------------------------
    // NAVIGATION / READ
    // ------------------------------
    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String pageName) {
        coursePage.openCoursesPage();
    }

    @When("I search for a course named {string}")
    public void iSearchForACourseNamed(String courseName) {
        coursePage.searchForCourse(courseName);
    }

    @Then("I should see course {string} listed")
    @Then("I should see {string} displayed in the search results")
    public void iShouldSeeCourseDisplayed(String courseName) {
        assertTrue(coursePage.isCourseDisplayed(courseName),
                "Expected course to be displayed: " + courseName);
    }

    @Then("I should see that no courses match the search")
    @Then("I should see no courses listed")
    public void iShouldSeeNoCoursesMatchSearch() {
        // If your app shows an alert when no courses are found:
        try {
            System.out.println("Alert text: " + coursePage.getAlertText());
            coursePage.acceptAlert();
        } catch (Exception e) {
            // If no alert, check if the list is empty instead
            assertTrue(coursePage.getCourses().isEmpty(), "Expected no courses to be listed");
        }
    }

    // ------------------------------
    // CREATE / ACTIONS
    // ------------------------------
    @When("I enrol or assign myself to the course {string}")
    public void iEnrolOrAssignMyself(String courseName) {
        coursePage.addUserToCourse(courseName);
    }

    @Then("I should see a confirmation message")
    public void iShouldSeeConfirmationMessage() {
        coursePage.acceptAlert();
    }

    // ------------------------------
    // DELETE / REMOVE
    // ------------------------------
    @When("I remove myself from the course {string}")
    public void iRemoveMyselfFromCourse(String courseName) {
        coursePage.removeUserFromCourse(courseName);
    }

    @Then("I should see an error message indicating I am not enrolled or assigned to that course")
    public void iShouldSeeNotEnrolledError() {
        coursePage.acceptAlert();
    }
}
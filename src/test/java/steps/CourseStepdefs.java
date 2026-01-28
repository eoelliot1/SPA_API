//package steps;
//
//import io.cucumber.java.PendingException;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import net.serenitybdd.annotations.Managed;
//import org.junit.Assert;
//import pages.CoursePage;
//import pages.LoginPage;
//
//import java.time.Duration;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class CourseStepdefs {
//
//    @Managed
//    LoginPage loginPage;
//
//    @Managed
//    CoursePage coursePage;
//
//    private String currentRole;
//
//    @Given("I am logged in as a trainee")
//    public void iAmLoggedInAsATrainee() {
//        loginPage.open();
//        loginPage.loginWith("4", "trainerpass");
//        Assert.assertTrue(loginPage.trainerDashboardIsVisible());
//    }
//
//    @And("I am on the courses page")
//    public void iAmOnTheCoursesPage() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//
//    @Given("I am logged in as a {string} and I am not enrolled or assigned to the course {string}")
//    public void iAmNotEnrolledOrAssigned(String role, String courseName) {
//        this.currentRole = role;
//        performLogin(role);
//    }
//
//    @Given("I am logged in as a {string} and I am enrolled or assigned to the course {string}")
//    public void iAmAlreadyEnrolledOrAssigned(String role, String courseName) {
//        this.currentRole = role;
//        performLogin(role);
//        coursePage.addUserToCourse(courseName);
//        coursePage.acceptAlert();
//    }
//
//    // ------------------------------
//    // NAVIGATION / READ
//    // ------------------------------
//    @When("I navigate to the {string} page")
//    public void iNavigateToThePage(String pageName) {
//        coursePage.openCoursesPage();
//    }
//
//    @When("I search for a course named {string}")
//    public void iSearchForACourseNamed(String courseName) {
//        coursePage.searchForCourse(courseName);
//    }
//
//    @Then("I should see course {string} listed")
//    @Then("I should see {string} displayed in the search results")
//    public void iShouldSeeCourseDisplayed(String courseName) {
//        assertTrue(coursePage.isCourseDisplayed(courseName),
//                "Expected course to be displayed: " + courseName);
//    }
//
//    @Then("I should see that no courses match the search")
//    @Then("I should see no courses listed")
//    public void iShouldSeeNoCoursesMatchSearch() {
//        // If your app shows an alert when no courses are found:
//        try {
//            System.out.println("Alert text: " + coursePage.getAlertText());
//            coursePage.acceptAlert();
//        } catch (Exception e) {
//            // If no alert, check if the list is empty instead
//            assertTrue(coursePage.getCourses().isEmpty(), "Expected no courses to be listed");
//        }
//    }
//
//    // ------------------------------
//    // CREATE / ACTIONS
//    // ------------------------------
//    @When("I enrol or assign myself to the course {string}")
//    public void iEnrolOrAssignMyself(String courseName) {
//        coursePage.addUserToCourse(courseName);
//    }
//
//    @Then("I should see a confirmation message")
//    public void iShouldSeeConfirmationMessage() {
//        coursePage.acceptAlert();
//    }
//
//    // ------------------------------
//    // DELETE / REMOVE
//    // ------------------------------
//    @When("I remove myself from the course {string}")
//    public void iRemoveMyselfFromCourse(String courseName) {
//        coursePage.removeUserFromCourse(courseName);
//    }
//
//    @Then("I should see an error message indicating I am not enrolled or assigned to that course")
//    public void iShouldSeeNotEnrolledError() {
//        coursePage.acceptAlert();
//    }
//
//
//}
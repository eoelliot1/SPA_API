package steps;
import actions.LoginPageActions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;

public class StudentSteps {
    @Managed
    LoginPageActions loginPageActions;


    @Given("I am logged in as a Student")
    public void iAmLoggedInAsAStudent() {
        loginPageActions.setEmail("alice");
        loginPageActions.setPassword("alicepass");
        loginPageActions.clickSignInButton();

    }

    @When("I click on my course")
    public void iClickOnMyCourse() {

        throw new PendingException();
    }

    @Then("I should see a the course that i am enrolled in and what other courses available")
    public void iShouldSeeATheCourseThatIAmEnrolledInAndWhatOtherCoursesAvailable() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I click on my profile")
    public void iClickOnMyProfile() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("I should be able to edit to change to the course I want")
    public void iShouldBeAbleToEditToChangeToTheCourseIWant() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("I should be able to edit my name")
    public void iShouldBeAbleToEditMyName() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I unenroll from course")
    public void iUnenrollFromCourse() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("I should see a student error message")
    public void iShouldSeeAStudentErrorMessage() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}

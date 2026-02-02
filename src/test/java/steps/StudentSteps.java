package steps;
import actions.*;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import utils.HelperClass;

public class StudentSteps {
    WebDriver driver;
    LoginPageActions loginPageActions;
    StudentPageActions studentPageActions;
    MyProfilePageActions myProfilePageActions;
    UpdateProfileActions updateProfileActions;


    public StudentSteps() {

        HelperClass.setUpDriver();
        this.driver = HelperClass.getDriver();
        this.loginPageActions = new LoginPageActions();
        this.studentPageActions = new StudentPageActions();
        this.myProfilePageActions = new MyProfilePageActions();
        this.updateProfileActions = new UpdateProfileActions();
    }


    @Given("I am logged in as a Student")
    public void iAmLoggedInAsAStudent() {
        loginPageActions.setEmail("alice");
        loginPageActions.setPassword("alicepass");
        loginPageActions.clickSignInButton();

    }

    @When("I click on my course")
    public void iClickOnMyCourse() {
        studentPageActions.clickMyCoursesButton();
    }

    @Then("I should see a the course that i am enrolled in and what other courses available")
    public void iShouldSeeATheCourseThatIAmEnrolledInAndWhatOtherCoursesAvailable() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/courses"));
    }

    @When("I click on my profile")
    public void iClickOnMyProfile() {
        studentPageActions.clickMyProfileButton();

    }

    @Then("I should be able to edit my name")
    public void iShouldBeAbleToEditMyName() {
        myProfilePageActions.clickEditProfile();
        updateProfileActions.clearStudentName();
        updateProfileActions.updateStudentName("Tor");
        updateProfileActions.saveChanges();
        Assert.assertTrue(driver.getCurrentUrl().contains("/profile"));

    }


}

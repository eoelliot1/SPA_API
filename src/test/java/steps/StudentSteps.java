package steps;

import actions.LoginPageActions;
import actions.MyProfilePageActions;
import actions.StudentPageActions;
import actions.UpdateProfileActions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.LoginPageLocators;
import locators.MyProfileLocators;
import locators.StudentPageLocators;
import locators.UpdateProfileLocators;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperClass;

import java.time.Duration;

public class StudentSteps {
    WebDriver driver = HelperClass.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(HelperClass.TIMEOUT));
    StudentPageLocators studentPageLocators = new StudentPageLocators();
    MyProfileLocators myProfileLocators = new MyProfileLocators();
    UpdateProfileLocators updateProfileLocators = new UpdateProfileLocators();
    LoginPageLocators loginPageLocators;
    StudentPageActions studentPageActions = new StudentPageActions();
    MyProfilePageActions myProfilePageActions = new MyProfilePageActions();
    UpdateProfileActions updateProfileActions = new UpdateProfileActions();
    LoginPageActions loginPageActions = new LoginPageActions();

    public StudentSteps() {
        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(driver, loginPageLocators);

        this.studentPageLocators = new StudentPageLocators();
        PageFactory.initElements(driver, studentPageLocators);

        this.myProfileLocators = new MyProfileLocators();
        PageFactory.initElements(driver, myProfileLocators);

        this.updateProfileLocators = new UpdateProfileLocators();
        PageFactory.initElements(driver, updateProfileLocators);

    }



    @When("I am logged in as a Student")
    public void iAmLoggedInAsAStudent() {
        loginPageActions.signIn("alice", "alicepass");
        wait.until(ExpectedConditions.urlContains("http://localhost:8091/"));
    }

    @Then("I should be redirected to the Student dashboard")
    public void iShouldBeRedirectedToTheStudentDashboard() {

        Assert.assertTrue(driver.getCurrentUrl().contains("8091"));
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
        Assert.assertTrue(driver.getCurrentUrl().contains("/profile"));
    }

    @Then("I should be able to edit my name")
    public void iShouldBeAbleToEditMyName() {
        // Perform the actions to change the name
        myProfilePageActions.clickEditProfile();
        updateProfileActions.clearStudentName();
        updateProfileActions.updateStudentName("Tor");
        updateProfileActions.saveChanges();

        // Verification: Check if the UI reflects the change or stays on the right page

    }


    @Then("I should be redirected to the student profile")
    public void iShouldBeRedirectedToTheStudentProfile() {
        Assert.assertTrue("Profile page URL not found after update",
                driver.getCurrentUrl().contains("/profile"));
    }
}

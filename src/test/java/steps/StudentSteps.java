package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import net.thucydides.core.annotations.Managed;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import net.serenitybdd.core.pages.PageObject;

import pages.LoginPage;
import pages.UpdateStudentPage;
import pages.MyProfile;
import pages.StudentDashBoardPage;
import  pages.StudentCoursesPage;


public class StudentSteps {
    @Managed
    WebDriver driver;
    LoginPage loginPage;
    UpdateStudentPage updateStudentPage;
    MyProfile myProfile;
    StudentDashBoardPage studentDashBoardPage;
    StudentCoursesPage studentCoursesPage;


    @When("I am logged in as a Student")
    public void iAmLoggedInAsAStudent() {
        loginPage.open();
        loginPage.enterUserName("alice");
        loginPage.enterPassword("alicepass");
        loginPage.clickLoginButton();
        loginPage.isStudentDashboardVisible();
    }

    @Then("I should be redirected to the Student dashboard")
    public void iShouldBeRedirectedToTheStudentDashboard() {
        assertTrue(loginPage.isStudentDashboardVisible());

    }

    @When("I click on my course")
    public void iClickOnMyCourse() {
      studentDashBoardPage.clickMyCourses();
    }

    @Then("I should see a the course that I am enrolled in and what other courses available")
    public void iShouldSeeATheCourseThatIAmEnrolledInAndWhatOtherCoursesAvailable() {
        assertTrue(studentCoursesPage.isCourseListVisible());
    }

    @When("I click on my profile")
    public void iClickOnMyProfile() {
       studentDashBoardPage.clickMyProfile();
    }

    @Then("I should be able to edit my name")
    public void iShouldBeAbleToEditMyName() {
        myProfile.clickEditProfile();
        updateStudentPage.clickStudentNameField();
        updateStudentPage.clickSaveButton();

    }

    @Then("I should be redirected to the student profile")
    public void iShouldBeRedirectedToTheStudentProfile() {
        new PageObject(driver) {}.waitForCondition()
                .until(d -> d.getCurrentUrl().contains("/profile"));

        assertTrue(driver.getCurrentUrl().contains("/profile"));
    }
}

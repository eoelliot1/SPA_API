package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.CourseDetailPage;
import pages.CoursePage;
import pages.DeleteCoursePage;
import pages.UpdateCoursePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseSteps {

    @Managed
    WebDriver driver;

    CoursePage coursePage;
    UpdateCoursePage updateCoursePage;
    CourseDetailPage courseDetailPage;
    DeleteCoursePage deleteCoursePage;

    @When("I open the courses page")
    public void iOpenTheCoursesPage() {
        coursePage.openCoursesPage();
    }

    @Then("I should see a list of all available courses")
    public void iShouldSeeAListOfAllAvailableCourses() {
        Assertions.assertTrue(coursePage.getAllCourses().getText().matches("Courses List"));
        assertTrue(driver.getCurrentUrl().contains("/courses/"));
    }

    @When("I search for a course named {string}")
    public void iSearchForACourseNamed(String courseName) {
        coursePage.searchForCourse(courseName);
    }

    @Then("I should see the course listed")
    public void iShouldSeeTheCourseListed() {
        Assertions.assertTrue(coursePage.courseTableHasCourse());
    }

    @Then("I should see no courses listed")
    public void iShouldSeeNoCoursesListed() {
        Assertions.assertTrue(coursePage.isNoCoursesMessageDisplayed());
    }

    @When("I click Edit for course {string}")
    public void iClickEditForCourse(String courseName) {
        coursePage.clickEditButton();
    }

    @And("I am on update course page")
    public void iAmOnUpdateCoursePage() {
        updateCoursePage.open();
        updateCoursePage.isUpdateCoursePageVisible();
    }

    @And("I update course name to {string}")
    public void iUpdateCourseNameTo(String updatedName) {
        updateCoursePage.updateCourseName(updatedName);
        updateCoursePage.clickUpdateButton();
    }

    @Then("I should see {string} in the courses list")
    public void iShouldSeeInTheCoursesList(String updatedName) {
        coursePage.searchForCourse(updatedName);
        Assertions.assertTrue(coursePage.courseTableHasCourse());
    }

    @And("I should not see {string} in the courses list")
    public void iShouldNotSeeInTheCoursesList(String courseName) {
        coursePage.searchForCourse(courseName);

        List<WebElementFacade> rows = coursePage.getCourseTableRows(); // Get fresh elements

        if (rows.isEmpty()) {
            return;
        }

        rows.forEach(row ->
                Assertions.assertFalse(row.getText().contains(courseName))
        );
    }

    @And("I attempt to update course name to {string}")
    public void iAttemptToUpdateCourseNameTo(String updatedCourse) {
        updateCoursePage.updateCourseName(updatedCourse);
    }

    @And("I click Cancel")
    public void iClickCancel() {
        updateCoursePage.clickCancelButton();
    }


    @When("I click Delete for course {string}")
    public void iClickDeleteForCourse(String course) {
        deleteCoursePage.clickDelete();
    }

    @And("I cancel the deletion confirmation")
    public void iCancelTheDeletionConfirmation() {
        deleteCoursePage.dismissAlert();
    }

    @And("I confirm the deletion")
    public void iConfirmTheDeletion() {
        deleteCoursePage.acceptAlert();
    }

    @Given("there is an existing course named Java Development")
    public void thereIsAnExistingCourseNamed() {
        coursePage.clickManageCourse();
       assertTrue(coursePage.isJavaDevelopmentVisible());
    }

    @When("I click view on course Java Development")
    public void iClickOnCourse() {
        coursePage.clickViewJavaDevButton();
    }

    @Then("I should see the course details page")
    public void iShouldSeeTheCourseDetailsPage() {
        assertTrue(courseDetailPage.isCourseDetailsVisible());
    }

    @And("I should see Java Development as the course name")
    public void iShouldSeeAsTheCourseName() {
        assertTrue(courseDetailPage.isCourseNameVisible());
    }

    @And("I should see the course id displayed")
    public void iShouldSeeTheCourseIdDisplayed() {
        assertTrue(courseDetailPage.isCourseIdVisible());
    }
}

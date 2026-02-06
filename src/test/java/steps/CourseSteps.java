package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.CoursePage;
import pages.UpdateCoursePage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseSteps {

    @Managed
    WebDriver driver;

    CoursePage coursePage;
    UpdateCoursePage updateCoursePage;

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
    public boolean iShouldSeeNoCoursesListed() {
        return coursePage.getCourseTableRows().stream()
                .anyMatch(row -> !row.getText().contains("No courses found."));
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
        updateCoursePage.updateCourse(updatedName);
    }

    @Then("I should see {string} in the courses list")
    public void iShouldSeeInTheCoursesList(String updatedName) {
        coursePage.searchForCourse(updatedName);
        Assertions.assertTrue(coursePage.courseTableHasCourse());
    }

    @And("I should not see {string} in the courses list")
    public boolean iShouldNotSeeInTheCoursesList(String courseName) {
        coursePage.searchForCourse(courseName);
        return coursePage.getCourseTableRows().stream()
                .anyMatch(row -> !row.getText().contains("No courses found."));
    }
}

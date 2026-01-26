package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import pages.CoursePage;
import pages.StudentsPage;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class StudentsStepdefs {

    @Managed
    CoursePage coursePage;

    @Managed
    StudentsPage studentsPage;

    @Given("I am logged in as a Student and there are courses in the system")
    public void iAmLoggedInAsAStudentAndThereAreCoursesInTheSystem() {
        studentsPage.navigateToCoursesPage();
    }

    @When("I search for a course named {string} on the {string} page")
    public void iSearchForACourseNamedOnThePage(String courseName, String arg1) {
        studentsPage.searchForCourse(courseName);
    }

    @Then("I should see the course {string} displayed in the search results")
    public void iShouldSeeTheCourseDisplayedInTheSearchResults(String courseName) {
        assertTrue(
                "Expected course to be displayed: " + courseName,
                studentsPage.isCourseDisplayed(courseName)
        );
    }

    @Then("I should see a message indicating that no courses match the search")
    public void iShouldSeeAMessageIndicatingThatNoCoursesMatchTheSearch() {
        String alertText = studentsPage.getAlertText();
        System.out.println("Alert message: " + alertText);
        studentsPage.acceptAlert();
    }

    @Given("I am logged in as a Student and I am enrolled in the course {string}")
    public void iAmLoggedInAsAStudentAndIAmEnrolledInTheCourse(String courseName) {
        studentsPage.navigateToCoursesPage();
        studentsPage.enrolInCourse(courseName);
        studentsPage.acceptAlert();
    }

    @When("I choose to unenrol from {string} on the {string} page")
    public void iChooseToUnenrolFromOnThePage(String courseName, String arg1) {
        studentsPage.unenrolFromCourse(courseName);
    }

    @Then("I should see a confirmation that I am no longer enrolled in the course")
    public void iShouldSeeAConfirmationThatIAmNoLongerEnrolledInTheCourse() {
        String alertText = studentsPage.getAlertText();
        System.out.println("Confirmation message: " + alertText);
        studentsPage.acceptAlert();
    }

    @Given("I am logged in as a Student and I am not yet enrolled in the course {string}")
    public void iAmLoggedInAsAStudentAndIAmNotYetEnrolledInTheCourse(String arg0) {
        studentsPage.navigateToCoursesPage();
    }

    @When("I attempt to unenrol from {string} on the {string} page")
    public void iAttemptToUnenrolFromOnThePage(String courseName, String arg1) {
        studentsPage.unenrolFromCourse(courseName);
    }

    @Then("I should see an error message indicating I am not enrolled in that course")
    public void iShouldSeeAnErrorMessageIndicatingIAmNotEnrolledInThatCourse() {
        String alertText = studentsPage.getAlertText();
        System.out.println("Error message: " + alertText);
        studentsPage.acceptAlert();
    }
}

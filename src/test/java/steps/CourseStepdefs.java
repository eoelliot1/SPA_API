package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import pages.CoursePage;

import java.util.ArrayList;
import java.util.List;

public class CourseStepdefs {

    @Managed
    CoursePage coursePage;

    private List<String> courses = new ArrayList<>();
    private String userRole = "";
    private String currentPage = "";
    private String message = "";

    @Given("I am logged in as a Student and there are courses in the system")
    public void iAmLoggedInAsAStudentAndThereAreCoursesInTheSystem() {
        coursePage.navigateToPage();
        System.out.println("Logged in as Student with courses in the system");
    }

    @When("I search for a course named {string} on the {string} page")
    public void iSearchForACourseNamedOnThePage(String courseName, String arg1) {
        coursePage.navigateToPage();
        coursePage.searchCourse(courseName);
    }

    @Then("I should see the course {string} displayed in the search results")
    public void iShouldSeeTheCourseDisplayedInTheSearchResults(String courseName) {
        boolean displayed = coursePage.isCourseDisplayed(courseName);
        if (!displayed) {
            throw new AssertionError("Course not found in search results: " + courseName);
        }
    }

    @Then("I should see a message indicating that no courses match the search")
    public void iShouldSeeAMessageIndicatingThatNoCoursesMatchTheSearch() {
        String alert = coursePage.getAlertText();
        System.out.println("Message shown: " + alert);
        coursePage.acceptAlert();
    }

    @Given("I am logged in as a Student and I am enrolled in the course {string}")
    public void iAmLoggedInAsAStudentAndIAmEnrolledInTheCourse(String courseName) {
        coursePage.navigateToPage();
        coursePage.enrolInCourse(courseName); // Enrol first
        System.out.println("Logged in as Student and enrolled in course: " + courseName);
    }

    @When("I choose to unenrol from {string} on the {string} page")
    public void iChooseToUnenrolFromOnThePage(String courseName, String arg1) {
        coursePage.navigateToPage();
        coursePage.unenrolFromCourse(courseName);
    }

    @Then("I should see a confirmation that I am no longer enrolled in the course")
    public void iShouldSeeAConfirmationThatIAmNoLongerEnrolledInTheCourse() {
        String alert = coursePage.getAlertText();
        System.out.println("Confirmation message: " + alert);
        coursePage.acceptAlert();
    }

    @Given("I am logged in as a Student and I am not enrolled in the course {string}")
    public void iAmLoggedInAsAStudentAndIAmNotEnrolledInTheCourse(String courseName) {
        coursePage.navigateToPage();
        System.out.println("Logged in as Student and NOT enrolled in course: " + courseName);
    }

    @When("I attempt to unenrol from {string} on the {string} page")
    public void iAttemptToUnenrolFromOnThePage(String courseName, String arg1) {
        coursePage.navigateToPage();
        coursePage.unenrolFromCourse(courseName);
    }

    @Then("I should see an error message indicating I am not enrolled in that course")
    public void iShouldSeeAnErrorMessageIndicatingIAmNotEnrolledInThatCourse() {
        String alert = coursePage.getAlertText();
        System.out.println("Error message: " + alert);
        coursePage.acceptAlert();
    }
}

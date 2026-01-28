package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import pages.CoursePage;
import pages.StudentsPage;

public class StudentsStepdefs {

    @Managed
    CoursePage coursePage;

    @Managed
    StudentsPage studentsPage;

    @Given("I am logged in as a Student")
    public void iAmLoggedInAsAStudent() {
        coursePage.openCoursesPage();
    }

    @Given("I am enrolled in course {string} as a Student")
    public void iAmEnrolledInCourse(String courseName) {
        coursePage.openCoursesPage();
        studentsPage.enrolInCourse(courseName);
        studentsPage.acceptAlert();
    }

    @When("I enrol in course {string}")
    public void iEnrolInCourse(String courseName) {
        studentsPage.enrolInCourse(courseName);
    }

    @When("I unenrol from course {string}")
    public void iUnenrolFromCourse(String courseName) {
        studentsPage.unenrolFromCourse(courseName);
    }

    @Then("I should see a student confirmation message")
    public void iShouldSeeStudentConfirmation() {
        System.out.println(studentsPage.getAlertText());
        studentsPage.acceptAlert();
    }

    @Then("I should see a student error message")
    public void iShouldSeeStudentError() {
        System.out.println(studentsPage.getAlertText());
        studentsPage.acceptAlert();
    }
}

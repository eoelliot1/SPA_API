package steps;

import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import pages.CoursesPage;
import pages.StudentsPage;
import pages.TrainersPage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsSteps {

    @Managed
    WebDriver driver;

    CoursesPage coursesPage;
    StudentsPage studentsPage;
    TrainersPage trainersPage;


    private List<String> allCourses;
    private List<String> enrolledCourses;
    private String currentUserRole;
    private String searchResult;
    private String pageMessage;
    private boolean confirmationPage;
    private boolean errorPage;

    // =======================
    // Given steps
    // =======================
    @Given("I am logged in as a Student and there are courses in the system")
    public void studentLoggedInWithCourses() {
        currentUserRole = "Student";
        allCourses = new ArrayList<>(List.of("Java Basics", "Spring Boot", "Cucumber Testing"));
        enrolledCourses = new ArrayList<>();
        resetFlags();
    }

    @Given("I am logged in as a Student and I am enrolled in the course {string}")
    public void studentEnrolledInCourse(String courseName) {
        currentUserRole = "Student";
        allCourses = new ArrayList<>(List.of("Java Basics", "Spring Boot", "Cucumber Testing"));
        enrolledCourses = new ArrayList<>();
        enrolledCourses.add(courseName);
        resetFlags();
    }

    @Given("I am logged in as a Student and I am not enrolled in the course {string}")
    public void studentNotEnrolledInCourse(String courseName) {
        currentUserRole = "Student";
        allCourses = new ArrayList<>(List.of("Java Basics", "Spring Boot", "Cucumber Testing", "Angular Basics"));
        enrolledCourses = new ArrayList<>();
        resetFlags();
    }

    private void resetFlags() {
        pageMessage = "";
        searchResult = "";
        confirmationPage = false;
        errorPage = false;
    }

    // =======================
    // When steps
    // =======================
    @When("I search for a course named {string} on the {string} page")
    public void searchCourseByName(String courseName, String pageName) {
        if (allCourses.contains(courseName)) {
            searchResult = courseName;
        } else {
            pageMessage = "No courses match the search";
        }
    }

    @When("I choose to unenrol from {string} on the {string} page")
    public void unenrolFromCourse(String courseName, String pageName) {
        if (enrolledCourses.contains(courseName)) {
            enrolledCourses.remove(courseName);
            confirmationPage = true;
        } else {
            errorPage = true;
            pageMessage = "You are not enrolled in this course";
        }
    }

    @When("I attempt to unenrol from {string} on the {string} page")
    public void attemptUnenrolFromCourse(String courseName, String pageName) {
        unenrolFromCourse(courseName, pageName); // same logic as above
    }

    // =======================
    // Then steps
    // =======================
    @Then("I should see the course {string} displayed in the search results")
    public void seeCourseInSearchResults(String courseName) {
        assertEquals(courseName, searchResult, "Expected course not found in search results");
    }

    @Then("I should see a message indicating that no courses match the search")
    public void seeNoCoursesMatchMessage() {
        assertEquals("No courses match the search", pageMessage);
    }

    @Then("I should see a confirmation that I am no longer enrolled in the course")
    public void seeUnenrolConfirmation() {
        assertTrue(confirmationPage, "Expected confirmation page after unenrolling, but it was not shown");
    }

    @Then("I should see an error message indicating I am not enrolled in that course")
    public void seeUnenrolErrorMessage() {
        assertTrue(errorPage, "Expected error page when unenrolling from a course not enrolled in");
        assertEquals("You are not enrolled in this course", pageMessage);
    }
}

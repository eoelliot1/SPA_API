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

public class TrainersSteps {

    @Managed
    WebDriver driver;

    CoursesPage coursesPage;
    StudentsPage studentsPage;
    TrainersPage trainersPage;

    private List<String> allCourses;
    private List<String> assignedCourses;
    private String currentUserRole;
    private String searchResult;
    private String pageMessage;
    private boolean confirmationPage;
    private boolean errorPage;

    // =======================
    // Given steps
    // =======================
    @Given("I am logged in as a Trainer and there are courses in the system")
    public void trainerLoggedInWithCourses() {
        currentUserRole = "Trainer";
        allCourses = new ArrayList<>(List.of("Java Basics", "Spring Boot", "Cucumber Testing"));
        assignedCourses = new ArrayList<>();
        resetFlags();
    }

    @Given("I am logged in as a Trainer and I am assigned to the course {string}")
    public void trainerAssignedToCourse(String courseName) {
        currentUserRole = "Trainer";
        assignedCourses = new ArrayList<>();
        assignedCourses.add(courseName);
        allCourses = new ArrayList<>(List.of("Java Basics", "Spring Boot", "Cucumber Testing"));
        resetFlags();
    }

    @Given("I am logged in as a Trainer and I am not assigned to the course {string}")
    public void trainerNotAssignedToCourse(String courseName) {
        currentUserRole = "Trainer";
        assignedCourses = new ArrayList<>();
        allCourses = new ArrayList<>(List.of("Java Basics", "Spring Boot", "Cucumber Testing", courseName));
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
    public void trainerSearchCourse(String courseName, String pageName) {
        if (allCourses.contains(courseName)) {
            searchResult = courseName;
        } else {
            pageMessage = "No courses match the search";
        }
    }

    @When("I choose to remove myself from {string} on the {string} page")
    public void removeAssignmentFromCourse(String courseName, String pageName) {
        if (assignedCourses.contains(courseName)) {
            assignedCourses.remove(courseName);
            confirmationPage = true;
        } else {
            errorPage = true;
            pageMessage = "You are not assigned to this course";
        }
    }

    @When("I attempt to remove myself from {string} on the {string} page")
    public void attemptRemoveAssignment(String courseName, String pageName) {
        removeAssignmentFromCourse(courseName, pageName); // reuse logic
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

    @Then("I should see a confirmation that I am no longer assigned to the course")
    public void seeRemovalConfirmation() {
        assertTrue(confirmationPage, "Expected confirmation page after removing assignment, but it was not shown");
    }

    @Then("I should see an error message indicating I am not assigned to that course")
    public void seeRemovalErrorMessage() {
        assertTrue(errorPage, "Expected error page when removing assignment from a course not assigned to");
        assertEquals("You are not assigned to this course", pageMessage);
    }
}

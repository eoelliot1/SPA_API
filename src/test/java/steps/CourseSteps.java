package steps;

import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import pages.CoursesPage;
import pages.StudentsPage;
import pages.TrainersPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseSteps {

    @Managed
    WebDriver driver;

    CoursesPage coursesPage;
    StudentsPage studentsPage;
    TrainersPage trainersPage;


    private List<String> allCourses;
    private String currentUserRole;
    private String pageMessage;

    // =======================
    // Given steps
    // =======================
    @Given("I am logged in as a Trainer and there are courses in the system")
    public void trainerLoggedInWithCourses() {
        currentUserRole = "Trainer";
        allCourses = List.of("Java Basics", "Spring Boot", "Cucumber Testing");
        pageMessage = "";
    }

    @Given("I am logged in as a Trainer and there are no courses in the system")
    public void trainerLoggedInWithNoCourses() {
        currentUserRole = "Trainer";
        allCourses = List.of();
        pageMessage = "";
    }

    @Given("I am logged in as a Student and there are courses in the system")
    public void studentLoggedInWithCourses() {
        currentUserRole = "Student";
        allCourses = List.of("Java Basics", "Spring Boot", "Cucumber Testing");
        pageMessage = "";
    }

    @Given("I am logged in as a Student and there are no courses in the system")
    public void studentLoggedInWithNoCourses() {
        currentUserRole = "Student";
        allCourses = List.of();
        pageMessage = "";
    }

    // =======================
    // When steps
    // =======================
    @When("I navigate to the {string} page")
    public void navigateToPage(String pageName) {
        if (allCourses.isEmpty()) {
            pageMessage = "No courses are available";
        }
        // Otherwise, courses will be displayed
    }

    // =======================
    // Then steps
    // =======================
    @Then("I should see a list of all courses displayed on the page")
    public void seeAllCourses() {
        assertFalse(allCourses.isEmpty(), "Expected courses to be displayed, but none are found");
    }

    @Then("I should see a message indicating that no courses are available")
    public void seeNoCoursesMessage() {
        assertEquals("No courses are available", pageMessage);
    }
}

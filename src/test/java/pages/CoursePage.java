package pages;

import lombok.Getter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/courses")
public class CoursePage extends PageObject {

    @Getter
    @FindBy(css = ".course-item")
    private List<WebElementFacade> courses;

    @FindBy(id = "searchBox")
    private WebElementFacade searchBox;

    @FindBy(id = "searchButton")
    private WebElementFacade searchButton;

    // ------------------------------
    // NAVIGATION
    // ------------------------------
    public void openCoursesPage() {
        open();
    }
    public List<WebElementFacade> getCourses() {
        return courses;
    }



    // ------------------------------
    // READ
    // ------------------------------
    public void searchForCourse(String courseName) {
        searchBox.clear();
        searchBox.type(courseName);
        searchButton.click();
    }

    public boolean isCourseDisplayed(String courseName) {
        return courses.stream()
                .anyMatch(course -> course.getText().contains(courseName));
    }

    // ------------------------------
    // CREATE (Enrol / Assign)
    // ------------------------------
    public void addUserToCourse(String courseName) {
        WebElementFacade course = findCourse(courseName);
        course.find(By.cssSelector(".add-btn")).click();
        waitForAlert();
    }

    // ------------------------------
    // DELETE (Unenrol / Unassign)
    // ------------------------------
    public void removeUserFromCourse(String courseName) {
        WebElementFacade course = findCourse(courseName);
        course.find(By.cssSelector(".remove-btn")).click();
        waitForAlert();
    }

    // ------------------------------
    // ALERT HANDLING
    // ------------------------------
    public String getAlertText() {
        waitForAlert();
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }

    protected void waitForAlert() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
    }

    // ------------------------------
    // HELPERS
    // ------------------------------
    private WebElementFacade findCourse(String courseName) {
        return courses.stream()
                .filter(course -> course.getText().contains(courseName))
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("Course not found: " + courseName)
                );
    }


}

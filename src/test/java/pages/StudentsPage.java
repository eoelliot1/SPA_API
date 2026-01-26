package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/courses")
public class StudentsPage extends PageObject {

    // ===============================
    // Web elements
    // ===============================

    // All courses displayed on the page
    @FindBy(css = ".course-item")
    private List<WebElementFacade> courses;

    // Search elements
    @FindBy(id = "searchBox")
    private WebElementFacade searchBox;

    @FindBy(id = "searchButton")
    private WebElementFacade searchButton;

    // Student actions
    @FindBy(css = ".btn-enrol")
    private List<WebElementFacade> enrolButtons;

    @FindBy(css = ".btn-remove")
    private List<WebElementFacade> removeButtons;

    // ===============================
    // Page actions
    // ===============================

    public void navigateToCoursesPage() {
        open();
    }

    // ---- SEARCH ----
    public void searchForCourse(String courseName) {
        searchBox.clear();
        searchBox.type(courseName);
        searchButton.click();
    }

    public boolean isCourseDisplayed(String courseName) {
        return courses.stream()
                .anyMatch(course -> course.getText().contains(courseName));
    }

    // ---- ENROL ----
    public void enrolInCourse(String courseName) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(".btn-enrol").click();
                waitForAlert();
                break;
            }
        }
    }

    // ---- UNENROL ----
    public void unenrolFromCourse(String courseName) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(".btn-remove").click();
                waitForAlert();
                break;
            }
        }
    }

    // ===============================
    // Alert handling
    // ===============================

    public void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText() {
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }
}

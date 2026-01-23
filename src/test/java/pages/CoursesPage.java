package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/courses")
public class CoursesPage extends PageObject {

    // List of courses displayed on the page
    @FindBy(css = ".course-item")
    private List<WebElementFacade> courses;

    // Search box
    @FindBy(id = "searchBox")
    private WebElementFacade searchBox;

    @FindBy(id = "searchButton")
    private WebElementFacade searchButton;

    // Enrol / Assign buttons inside each course item
    @FindBy(css = ".btn-enrol")
    private List<WebElementFacade> enrolButtons;

    @FindBy(css = ".btn-assign")
    private List<WebElementFacade> assignButtons;

    @FindBy(css = ".btn-remove")
    private List<WebElementFacade> removeButtons;

    // ===============================
    // Page actions
    // ===============================

    public void searchCourse(String courseName) {
        searchBox.type(courseName);
        searchButton.click();
    }

    public boolean isCourseDisplayed(String courseName) {
        return courses.stream().anyMatch(c -> c.getText().contains(courseName));
    }

    public void enrolInCourse(String courseName) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(".btn-enrol").click();
                waitForAlert();
                break;
            }
        }
    }

    public void assignToCourse(String courseName) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(".btn-assign").click();
                waitForAlert();
                break;
            }
        }
    }

    public void removeAssignment(String courseName) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(".btn-remove").click();
                waitForAlert();
                break;
            }
        }
    }

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

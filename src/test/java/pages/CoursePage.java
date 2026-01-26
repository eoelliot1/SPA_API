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
public class CoursePage extends PageObject {

    // ------------------------------
    // Web elements
    // ------------------------------

    @FindBy(css = ".course-item")
    private List<WebElementFacade> courses;

    @FindBy(id = "searchBox")
    private WebElementFacade searchBox;

    @FindBy(id = "searchButton")
    private WebElementFacade searchButton;

    @FindBy(css = ".btn-enrol")
    private List<WebElementFacade> enrolButtons;

    @FindBy(css = ".btn-remove")
    private List<WebElementFacade> removeButtons;

    // ------------------------------
    // Page actions
    // ------------------------------

    public void navigateToPage() {
        getDriver().get(getDriver().getCurrentUrl());
        System.out.println("Navigated to Courses page");
    }

    public void searchCourse(String courseName) {
        searchBox.type(courseName);
        searchButton.click();
        System.out.println("Searched for course: " + courseName);
    }

    public boolean isCourseDisplayed(String courseName) {
        boolean found = courses.stream().anyMatch(c -> c.getText().contains(courseName));
        System.out.println(found ? "Course displayed: " + courseName : "Course not found: " + courseName);
        return found;
    }

    public void enrolInCourse(String courseName) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(".btn-enrol").click();
                waitForAlert();
                System.out.println("Enrolled in course: " + courseName);
                break;
            }
        }
    }

    public void unenrolFromCourse(String courseName) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(".btn-remove").click();
                waitForAlert();
                System.out.println("Unenrolled from course: " + courseName);
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

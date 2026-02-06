package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StudentsPage extends PageObject {

    @FindBy(css = ".course-item")
    private List<WebElementFacade> courses;

    public void enrolInCourse(String courseName) {
        clickButton(courseName, ".btn-enrol");
    }

    public void unenrolFromCourse(String courseName) {
        clickButton(courseName, ".btn-remove");
    }

    private void clickButton(String courseName, String cssClass) {
        for (WebElementFacade course : courses) {
            if (course.getText().contains(courseName)) {
                course.findBy(cssClass).click();
                waitForAlert();
                break;
            }
        }
    }

    private void waitForAlert() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText() {
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }

}

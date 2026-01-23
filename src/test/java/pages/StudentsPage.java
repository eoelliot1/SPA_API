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

@DefaultUrl("http://localhost:8091/students")
public class StudentsPage extends PageObject {

    @FindBy(css = ".student-item")
    private List<WebElementFacade> students;

    @FindBy(css = ".btn-remove")
    private List<WebElementFacade> removeButtons;

    public boolean isStudentDisplayed(String studentName) {
        return students.stream().anyMatch(s -> s.getText().contains(studentName));
    }

    public void removeStudent(String studentName) {
        for (WebElementFacade student : students) {
            if (student.getText().contains(studentName)) {
                student.findBy(".btn-remove").click();
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

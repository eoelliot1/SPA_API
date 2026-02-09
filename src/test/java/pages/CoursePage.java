package pages;

import lombok.Getter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/courses/")
public class CoursePage extends PageObject {



    @FindBy(xpath="//a[contains(text(),'Manage Courses')]")
    private WebElementFacade manageCoursesButton;

    @FindBy(xpath = "//h1[normalize-space()='Courses List']")
    private WebElementFacade allCourses;

    @FindBy(name = "keyword")
    private WebElementFacade searchBox;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElementFacade searchButton;

    @FindBy(css = "table.table-striped:nth-of-type(1) tbody tr")
    List<WebElementFacade> courseTableRows;

    @FindBy(xpath = "/html/body/div/table/tbody/tr[1]/td[3]/a[2]")
    private WebElementFacade editButton;

    @FindBy(xpath = "//button[normalize-space()='View']")
    private WebElementFacade viewButton;

    @FindBy (xpath="//td[contains(.,'Java Development')]")
    private WebElementFacade javaDevelopmentCourse;

    @FindBy(xpath="//a[contains(@href, '/courses/3')]")
    private WebElementFacade viewJavaDevButton;



    @FindBy(xpath = "//button[normalize-space()='Delete']")
    private WebElementFacade deleteButton;

    public void openCoursesPage() {
        open();
    }

    public WebElementFacade getAllCourses() {
        return allCourses;
    }

    public List<WebElementFacade> getCourseTableRows() {
        return courseTableRows;
    }

    public void clickEditButton()
    {
        editButton.click();
    }

    public void clickManageCourse(){manageCoursesButton.click();}

    public void searchForCourse(String courseName) {
        searchBox.clear();
        searchBox.type(courseName);
        clickSearchButton();
    }

    public boolean courseTableHasCourse() {
        return courseTableRows.size() > 0;
    }

    public void clickSearchButton()
    {
        searchButton.waitUntilClickable().click();
    }

    public boolean isJavaDevelopmentVisible(){
        return waitForCondition().until(webDriver -> javaDevelopmentCourse.isVisible());
    }
    public void clickViewJavaDevButton (){
        viewJavaDevButton.click();
    }


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






}

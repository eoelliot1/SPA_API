package locators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseLocators {
    // Courses List Page
    @FindBy(xpath = "/html/body/nav/div/div/a[1]")
    private WebElement coursesItem;

    @FindBy(name = "keyword")
    private WebElement searchField;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(linkText = "Add Course")
    private WebElement addCourseButton;

    @FindBy(css = "table.table-striped")
    private WebElement coursesTable;

    @FindBy(css = "td.text-center.text-muted")
    private WebElement noCoursesMessage;

    // Create/Edit Course Page
    @FindBy(css = "input[placeholder='Enter course name']")
    private WebElement courseNameField;

    @FindBy(css = "input[placeholder='Enter start Date']")
    private WebElement startDateField;

    @FindBy(css = "button.btn-success[type='submit']")
    private WebElement createButton;

    @FindBy(css = "button.btn-primary[type='submit']")
    private WebElement updateButton;

    @FindBy(linkText = "Cancel")
    private WebElement cancelButton;

    @FindBy(css = "a.btn-secondary")
    private WebElement backButton;

    // Course Details Page
    @FindBy(xpath = "//p[contains(., 'ID:')]/span")
    private WebElement courseIdDetail;

    @FindBy(xpath = "//p[contains(., 'Name:')]/span")
    private WebElement courseNameDetail;

    @FindBy(xpath = "//p[contains(., 'Start Date:')]/span")
    private WebElement startDateDetail;



    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getAddCourseButton() {
        return addCourseButton;
    }

    public WebElement getCoursesTable() {
        return coursesTable;
    }

    public WebElement getNoCoursesMessage() {
        return noCoursesMessage;
    }

    public WebElement getCourseNameField() {
        return courseNameField;
    }

    public WebElement getStartDateField() {
        return startDateField;
    }

    public WebElement getCreateButton() {
        return createButton;
    }

    public WebElement getUpdateButton() {
        return updateButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getCourseIdDetail() {
        return courseIdDetail;
    }

    public WebElement getCourseNameDetail() {
        return courseNameDetail;
    }

    public WebElement getStartDateDetail() {
        return startDateDetail;
    }
}

package locators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseLocators {
    // Courses List Page
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

    //Neww

    @FindBy(xpath = "//nav//a[normalize-space()='Manage Courses' and @href='/courses/']")
    private WebElement navigateCourseButton;

    @FindBy(xpath = "//h1[contains(@class,'text-center') and contains(@class,'mb-4')]")
    private WebElement courseIndexHeader;

    @FindBy(xpath = "//table//td[normalize-space()='Data']")
    private WebElement courseTable;

    @FindBy(css = "input[placeholder='Search courses by name']")
    private WebElement courseSearchField;

    //Data text field search has been done
    @FindBy(xpath = "//table//tr[td[normalize-space()='Data']]")
    private WebElement dataCell;

    //Data text field search has been done
    @FindBy(xpath = "//table//tr[td[normalize-space()='Software Testing']]")
    private WebElement softare_testingCell;

    @FindBy(xpath = "//table//tr[td[normalize-space()='Swimming']]")
    private WebElement swimmingCell;

    @FindBy(xpath = "//form[@action='/courses/search']//button[@type='submit']")
    private WebElement searchFilterButton;
    // NEW:

    public WebElement getSearchFilterButton() {return searchFilterButton;}
    public WebElement getSwimmingCell() {return swimmingCell;}

    public WebElement getSoftare_testing_Cell() {return softare_testingCell;}

    public WebElement getDataCell() {return dataCell;}

    public WebElement getCourseIndexHeader() {return courseIndexHeader;}

    public WebElement getCourseButton() {return navigateCourseButton;}

    public WebElement getCourseTable() { return courseTable;}

    public WebElement getCourseSearchField() {
        return courseSearchField;
    }

    public WebElement searchButton() {
        return searchButton;
    }

    // OLD:

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

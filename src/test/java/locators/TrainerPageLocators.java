package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HelperClass;

import java.util.List;



public class TrainerPageLocators {
    WebDriver driver;
  public TrainerPageLocators() {
      this.driver = HelperClass.getDriver();
  }


    @FindBy(linkText = "Trainers")
    private WebElement trainersNavButton;

    @FindBy(xpath = "//h2[text()='All trainers detail']/following-sibling::table")
    private WebElement trainersTable;

    @FindBy(xpath = "//h2[text()='All trainers detail']/following-sibling::table//tbody/tr")
    private List<WebElement> trainerRows;

    @FindBy(xpath = "//h2[text()='All trainers detail']/following-sibling::table//tbody/tr/td[3]")
    private List<WebElement> trainerCourseCells;


    @FindBy(name = "query")
    private WebElement searchInput;

    @FindBy(xpath = "//form[@class='mb-4']//button[@type='submit']")
    private WebElement searchButton;


    @FindBy(linkText = "Add new Trainer")
    private WebElement addTrainerButton;

    @FindBy(id = "trainerName") // assuming your input has id="trainerName"
    private WebElement trainerNameInput;

    @FindBy(id = "courseId") // assuming your select has id="courseId"
    private WebElement courseDropdown;


    @FindBy(xpath = "/html/body/div/form/button")
    private WebElement saveTrainerButton;


//    @FindBy(xpath = "//tr[td[text()='John Trainer']]//a[contains(text(),'Edit')]")
//    private WebElement editTrainerButton;

    public WebElement getEditButtonForTrainer(String trainerName) {
        return driver.findElement(By.xpath(
                "//tr[td[text()='" + trainerName + "']]//a[contains(text(),'Edit')]"
        ));
    }



    // Update form fields
    @FindBy(id = "trainerName")
    private WebElement editTrainerNameInput;

    @FindBy(id = "courseId")
    private WebElement editCourseDropdown;

    // Update Trainer button (your exact XPath)
    @FindBy(xpath = "/html/body/div/form/button")
    private WebElement updateTrainerButton;

    //public WebElement getEditTrainerButton() { return editTrainerButton; }
    public WebElement getEditTrainerNameInput() { return editTrainerNameInput; }
    public WebElement getEditCourseDropdown() { return editCourseDropdown; }
    public WebElement getUpdateTrainerButton() { return updateTrainerButton; }


    public WebElement getAddTrainerButton() { return addTrainerButton; }
    public WebElement getTrainerNameInput() { return trainerNameInput; }
    public WebElement getCourseDropdown() { return courseDropdown; }
    public WebElement getSaveTrainerButton() { return saveTrainerButton; }



    public WebElement getTrainersNavButton() { return trainersNavButton; }
    public WebElement getTrainersTable() { return trainersTable; }
    public List<WebElement> getTrainerRows() { return trainerRows; }
    public List<WebElement> getTrainerCourseCells() { return trainerCourseCells; }
    public WebElement getSearchInput() { return searchInput; }
    public WebElement getSearchButton() { return searchButton; }
}

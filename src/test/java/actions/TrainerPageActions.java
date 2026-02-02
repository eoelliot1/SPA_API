package actions;

import locators.TrainerPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperClass;

import java.time.Duration;
import java.util.List;

public class TrainerPageActions {

    TrainerPageLocators trainerPageLocators;
    WebDriver driver;
    WebDriverWait wait;

    public TrainerPageActions() {
        this.driver = HelperClass.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.trainerPageLocators = new TrainerPageLocators();
        PageFactory.initElements(driver, trainerPageLocators);
    }

    // Navigate to Trainers page
    public void navigateToTrainersPage() {
        HelperClass.openPage("http://localhost:8091/trainers");
        wait.until(ExpectedConditions.urlContains("/trainers"));
    }
    public TrainerPageLocators getTrainerLocators() {
        return trainerPageLocators;
    }




    public void searchCoursesLongerThan(String duration) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOf(trainerPageLocators.getSearchInput())
        );
        input.clear();
        input.sendKeys(duration);

        WebElement searchBtn = wait.until(
                ExpectedConditions.elementToBeClickable(trainerPageLocators.getSearchButton())
        );
        searchBtn.click();


        wait.until(ExpectedConditions.visibilityOfAllElements(trainerPageLocators.getTrainerRows()));
    }

    public void goToTrainersPage() {
        HelperClass.openPage("http://localhost:8091/trainers");
        wait.until(ExpectedConditions.visibilityOf(trainerPageLocators.getTrainersTable()));
    }


    public void updateTrainerName(String newName) {
        trainerPageLocators.getEditTrainerNameInput().clear();
        trainerPageLocators.getEditTrainerNameInput().sendKeys(newName);
    }

    public void changeCourse(String courseName) {
        Select select = new Select(trainerPageLocators.getEditCourseDropdown());
        select.selectByVisibleText(courseName);
    }

    public void clickUpdateTrainer() {
        trainerPageLocators.getUpdateTrainerButton().click();
        wait.until(ExpectedConditions.urlContains("/trainers"));
    }

    public boolean isCourseAssigned(String courseName) {
        return trainerPageLocators.getTrainerCourseCells()
                .stream()
                .anyMatch(cell -> cell.getText().equals(courseName));
    }


    public void clickAddNewTrainer() {
        wait.until(ExpectedConditions.elementToBeClickable(trainerPageLocators.getAddTrainerButton()));
        trainerPageLocators.getAddTrainerButton().click();
        wait.until(ExpectedConditions.urlContains("/trainers/new-trainer"));
    }

    // 👉 Verify Add Trainer page
    public boolean isOnAddTrainerPage() {
        return driver.getCurrentUrl().contains("/trainers/new-trainer");
    }




    public void clickAddTrainer() {
        trainerPageLocators.getAddTrainerButton().click();
        wait.until(ExpectedConditions.urlContains("/trainers/new-trainer"));
    }

    public void enterTrainerName(String name) {
        wait.until(ExpectedConditions.visibilityOf(trainerPageLocators.getTrainerNameInput()));
        trainerPageLocators.getTrainerNameInput().clear();
        trainerPageLocators.getTrainerNameInput().sendKeys(name);
    }

    public void selectCourse(String courseName) {
        Select select = new Select(trainerPageLocators.getCourseDropdown());
        select.selectByVisibleText(courseName);
    }

    public void saveTrainer() {
        trainerPageLocators.getSaveTrainerButton().click();
        wait.until(ExpectedConditions.urlContains("/trainers"));
    }
    public boolean isTrainerListed(String trainerName) {
        return trainerPageLocators.getTrainerRows()
                .stream()
                .anyMatch(row -> row.getText().contains(trainerName));
    }

    public boolean isNoCoursesMessageDisplayed() {

        return driver.getPageSource().contains("No courses found.");
    }



    public List<WebElement> getTrainerRows() {
        wait.until(ExpectedConditions.visibilityOf(trainerPageLocators.getTrainersTable()));
        return trainerPageLocators.getTrainerRows();
    }

    public void clickEditTrainer(String trainerName) {
//        WebElement editButton = trainerPageLocators.getEditButtonForTrainer(trainerName);
//        wait.until(ExpectedConditions.elementToBeClickable(editButton));
//        editButton.click();
//        wait.until(ExpectedConditions.visibilityOf(trainerPageLocators.getEditTrainerNameInput()));

        WebElement editButton = driver.findElement(
                By.xpath("//tr[td[text()='" + trainerName + "']]//a[contains(text(),'Edit')]")
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", editButton
        );

        wait.until(ExpectedConditions.visibilityOf(editButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", editButton
        );

        wait.until(ExpectedConditions.urlContains("/update-trainer"));
    }

    public boolean isCourseAssignedToTrainer(String trainerName, String courseName) {
        List<WebElement> rows = trainerPageLocators.getTrainerRows();
        for (WebElement row : rows) {
            if (row.getText().contains(trainerName)) {
                return row.getText().contains(courseName);
            }
        }
        return false;
    }

}

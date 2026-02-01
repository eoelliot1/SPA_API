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



    // ================= SEARCH ACTION =================
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

        // Wait for table rows to update
        wait.until(ExpectedConditions.visibilityOfAllElements(trainerPageLocators.getTrainerRows()));
    }
    public void goToTrainersPage() {
        HelperClass.openPage("http://localhost:8091/trainers");
        wait.until(ExpectedConditions.visibilityOf(trainerPageLocators.getTrainersTable()));
    }

    public void clickEditTrainer(String trainerName) {
        WebElement editButton = trainerPageLocators.getEditButtonForTrainer(trainerName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
        wait.until(ExpectedConditions.urlContains("/update-trainer"));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                editButton
        );

        // JS click (bypasses overlay)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                editButton
        );

        // Wait for edit page
        wait.until(ExpectedConditions.urlContains("/update-trainer"));
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

    // Get all trainer rows after search
//    public List<WebElement> getTrainerRows() {
//        return trainerPageLocators.getTrainerRows();
//    }


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

    public void updateTrainer(String newName, String newCourse) {
        // Update name
        wait.until(ExpectedConditions.visibilityOf(trainerPageLocators.getEditTrainerNameInput()))
                .clear();
        trainerPageLocators.getEditTrainerNameInput().sendKeys(newName);

        // Update course
        Select selectCourse = new Select(trainerPageLocators.getEditCourseDropdown());
        selectCourse.selectByVisibleText(newCourse);

        // Click Update
        wait.until(ExpectedConditions.elementToBeClickable(trainerPageLocators.getUpdateTrainerButton()))
                .click();

        // Wait for redirect back to Trainers page
        wait.until(ExpectedConditions.urlContains("/trainers"));
    }

    public boolean isCourseAssigned1(String trainerName, String expectedCourse) {
        // Wait for the trainers table to be visible
        List<WebElement> rows = trainerPageLocators.getTrainerRows();
        for (WebElement row : rows) {
            String name = row.findElement(By.xpath("./td[1]")).getText();
            String course = row.findElement(By.xpath("./td[3]")).getText();
            if (name.equals(trainerName) && course.equals(expectedCourse)) {
                return true;
            }
        }
        return false;

    }

    public List<WebElement> getTrainerRows() {
        wait.until(ExpectedConditions.visibilityOf(trainerPageLocators.getTrainersTable()));
        return trainerPageLocators.getTrainerRows();
    }

}

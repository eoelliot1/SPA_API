package steps;

import actions.CoursePageActions;
//import actions.LoginPageActions;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.CourseLocators;
import locators.DashboardLocators;
import locators.LoginPageLocators;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperClass;

import java.time.Duration;

import static org.hamcrest.Matchers.is;

public class CourseSteps {

    LoginPageLocators loginPageLocators;
    DashboardLocators dashboardLocators;
    CourseLocators courseLocators;

    WebDriver driver;
    WebDriverWait wait;


    public CourseSteps() {
        this.loginPageLocators = new LoginPageLocators();
        this.dashboardLocators = new DashboardLocators();
        this.courseLocators = new CourseLocators();
    }

    @Before
    public void setup() {
        HelperClass.setUpDriver();
        driver = HelperClass.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(HelperClass.TIMEOUT));

        loginPageLocators = new LoginPageLocators();
        dashboardLocators = new DashboardLocators();
        courseLocators = new CourseLocators();

        PageFactory.initElements(driver, loginPageLocators);
        PageFactory.initElements(driver, dashboardLocators);
        PageFactory.initElements(driver, courseLocators);

        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @Then("I should arrive at the dashboard")
    public void iShouldArriveAtTheDashboard() {
        WebElement trainerDashboard = loginPageLocators.getTrainerDashboard();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + trainerDashboard.getText());

        wait.until(ExpectedConditions.visibilityOf(trainerDashboard));
        Assert.assertTrue(trainerDashboard.getText().contains("Trainer"));
    }


    @When("I click on manage courses")
    public void iClickOnManageCourses() {
        //coursePageActions.clickCourseButton();
        //wait.until(ExpectedConditions.visibilityOf(courselocator.getCourseButton()));
       // courselocator.getCourseButton().click();
        //Assert.assertTrue(courselocator.getCourseIndexHeader().getText().contains("Courses List"));

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        courseLocators.getCourseButton()
                )
        );
        btn.click();
    }


    @Then("I should see all available courses listed")
    public void iShouldSeeAllAvailableCoursesListed() {
        wait.until(ExpectedConditions.visibilityOf(courseLocators.getCourseIndexHeader()));
        Assert.assertTrue(courseLocators.getCourseTable().isDisplayed());
    }

    @When("I search for a course named {string}")
    public void iSearchForACourseNamed(String searchValue) {
        wait.until(ExpectedConditions.elementToBeClickable(
                courseLocators.getCourseSearchField()));
        courseLocators.getCourseSearchField().sendKeys(searchValue);

        Assert.assertTrue(courseLocators.getCourseSearchField().isDisplayed());
        courseLocators.getSearchFilterButton().click();
    }

    @Then("I should see course {string} listed")
    public void iShouldSeeCourseListed(String searchValue) {
        wait.until(ExpectedConditions.visibilityOf(courseLocators.getDataCell()));

        MatcherAssert.assertThat(courseLocators.getDataCell().getText(), is(searchValue));
    }
}
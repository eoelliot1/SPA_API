package steps;

import actions.LoginPageActions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.LoginPageLocators;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperClass;
import java.time.Duration;

public class LoginSteps {
    LoginPageActions loginPageActions = new LoginPageActions();
    LoginPageLocators loginPageLocators;
    WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(HelperClass.TIMEOUT));

    public LoginSteps() {
        // Initialize the locators with PageFactory
        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(HelperClass.getDriver(), loginPageLocators);
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        HelperClass.openPage("http://localhost:8091/login");
    }

    @When("I enter email {string} and password {string}")
    public void iEnterEmailAndPassword(String email, String password) {
        loginPageActions.signIn(email, password);
    }

    @Then("I should be redirected to the trainer dashboard")
    public void iShouldBeRedirectedToTheTrainerDashboard() {
        WebElement trainerDashboard = loginPageLocators.getTrainerDashboard();
        wait.until(ExpectedConditions.visibilityOf(trainerDashboard));

        Assert.assertTrue(trainerDashboard.getText().contains("Trainer"));
    }


    @Then("I should be redirected to the student dashboard")
    public void iShouldBeRedirectedToTheStudentDashboard() {
        WebElement studentDashboard = loginPageLocators.getStudentDashboard();
        wait.until(ExpectedConditions.visibilityOf(studentDashboard));

        Assert.assertTrue(studentDashboard.getText().contains("Student"));

    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {

        String currentUrl = HelperClass.getDriver().getCurrentUrl();
        boolean isOnLoginPage = currentUrl.contains("/login");

        // Also check if error parameter is present
        if (!isOnLoginPage && currentUrl.contains("error")) {
            isOnLoginPage = true;
        }

        Assertions.assertTrue(isOnLoginPage,
                "Expected to remain on the login page, but URL was: " + currentUrl);
    }

    @Given("I am logged in as a Trainer")
    public void iAmLoggedInAsATrainer() {
        String email = "trainer";
        String password = "trainerpass";

        loginPageActions.signIn(email, password);
    }

    @When("I press the logout button")
    public void iPressTheLogoutButton() {
        loginPageActions.clickLogoutButton();
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        String currentUrl = HelperClass.getDriver().getCurrentUrl();
        boolean isOnLoginPage = currentUrl.contains("/login");
        Assertions.assertTrue(isOnLoginPage,
                "Expected to remain on the login page, but URL was: " + currentUrl);
    }

    @And("I am on the trainer dashboard")
    public void iAmOnTheTrainerDashboard() {
        WebElement trainerDashboard = loginPageLocators.getTrainerDashboard();
        wait.until(ExpectedConditions.visibilityOf(trainerDashboard));

        Assert.assertTrue(trainerDashboard.getText().contains("Trainer"));
    }

}

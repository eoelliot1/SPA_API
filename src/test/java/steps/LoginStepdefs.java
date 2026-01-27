package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import pages.LoginPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginStepdefs {

    @Managed
    LoginPage loginPage;

    private final int TIMEOUT = 10; // Timeout for explicit waits

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.open();
        WebDriver driver = loginPage.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @When("I enter email {string} and password {string}")
    public void iEnterEmailAndPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
    }

    @Then("I should be redirected to the trainer dashboard")
    public void iShouldBeRedirectedToTrainerDashboard() {
        WebDriver driver = loginPage.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getTrainerDashboardHeader()));
        Assert.assertTrue("Trainer dashboard should be visible", loginPage.isTrainerDashboardVisible());
    }

    @Then("I should be redirected to the trainee dashboard")
    public void iShouldBeRedirectedToTraineeDashboard() {
        WebDriver driver = loginPage.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getStudentDashboardHeader()));
        Assert.assertTrue("Student dashboard should be visible", loginPage.isStudentDashboardVisible());
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnLoginPage() {
        WebDriver driver = loginPage.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.urlContains("/login"));
    }


}

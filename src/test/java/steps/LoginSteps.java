package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

import pages.LoginPage;

public class LoginSteps {

    @Managed
    WebDriver driver;

    LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.open();
    }

    @When("I enter email {string} and password {string}")
    public void iEnterEmailAndPassword(String username, String password) {
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
    }

    @And("I click the log in button")
    public void iClickTheLogInButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the trainer dashboard")
    public void iShouldBeRedirectedToTheTrainerDashboard() {
        assertTrue(loginPage.isTrainerDashboardVisible());
    }

    @Then("I should be redirected to the student dashboard")
    public void iShouldBeRedirectedToTheStudentDashboard() {
        assertTrue(loginPage.isStudentDashboardVisible());
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
       assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @And("I am logged in as a Trainer")
    public void iAmLoggedInAsATrainer() {
      loginPage.open();
      loginPage.enterUserName("sarah");
      loginPage.enterPassword("sarahpass");
      loginPage.clickLoginButton();
      loginPage.isTrainerDashboardVisible();
    }

    @And("I am on the trainer dashboard")
    public void iAmOnTheTrainerDashboard() {
        assertTrue(loginPage.isTrainerDashboardVisible());
    }

    @When("I press the logout button")
    public void iPressTheLogoutButton() {
       loginPage.clickLogout();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {

        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

}

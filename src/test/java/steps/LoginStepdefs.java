package steps;


import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;
import pages.LoginPage;

public class LoginStepdefs {

    @Steps
    LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.openLoginPage();
    }

    @When("I enter email {string} and password {string}")
    public void i_enter_email_and_password(String email, String password) {
        loginPage.loginWith(email, password);
    }

    @Then("I should be redirected to the trainer dashboard")
    public void i_should_be_redirected_to_the_trainer_dashboard() {
        Assert.assertTrue(loginPage.trainerDashboardIsVisible());
    }

    @Then("I should be redirected to the trainee dashboard")
    public void i_should_be_redirected_to_the_trainee_dashboard() {
        Assert.assertTrue(loginPage.studentDashboardIsVisible());
    }

    @Then("I should remain on the login page")
    public void i_should_remain_on_the_login_page() {
        Assert.assertTrue(loginPage.isStillOnLoginPage());
    }
}

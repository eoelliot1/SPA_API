package actions;

import locators.LoginPageLocators;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import utils.HelperClass;
import java.time.Duration;


public class LoginPageActions {
    LoginPageLocators loginPageLocators = null;
    WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(HelperClass.TIMEOUT));

    public LoginPageActions() {

        this.loginPageLocators = new LoginPageLocators();

        PageFactory.initElements(HelperClass.getDriver(), loginPageLocators);
    }

    // Set email in textbox
    public void setEmail(String email) {
        loginPageLocators.getEmailField().sendKeys(email);
    }

    // Set password in password textbox
    public void setPassword(String password) {
        loginPageLocators.getPasswordField().sendKeys(password);
    }

    // Click on login button
    public void clickSignInButton() {
        loginPageLocators.getSignInButton().click();
    }

    // Click on logout button
    public void clickLogoutButton() {
        WebElement logoutButton = loginPageLocators.getLogoutButton();
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();

    }

    public void signIn(String email, String password) {

        // Fill email
        this.setEmail(email);

        // Fill password
        this.setPassword(password);


        // Click signup button
        this.clickSignInButton();

    }
}

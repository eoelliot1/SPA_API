package actions;

import locators.LoginPageLocators;
import org.openqa.selenium.support.PageFactory;
import utils.HelperClass;

public class LoginPageActions {
    LoginPageLocators loginPageLocators = null;

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

    public void signIn(String email, String password) {

        // Fill email
        this.setEmail(email);

        // Fill password
        this.setPassword(password);


        // Click signup button
        this.clickSignInButton();

    }
}

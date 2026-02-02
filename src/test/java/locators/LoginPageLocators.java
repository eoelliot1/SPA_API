package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {

    @FindBy(id = "password-input")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    @FindBy(css = "button[type='submit']")
    private WebElement logoutButton;

    @FindBy(xpath = "//h2[@id='trainer-dashboard']")
    private WebElement trainerDashboard;

    @FindBy(xpath = "/html/body/div[2]/div[1]/div")
    private WebElement studentDashboard;

    @FindBy(xpath = "/html/body/div/div/div/form/div[1]/input")
    private WebElement emailField;

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getTrainerDashboard() {
        return trainerDashboard;
    }

    public WebElement getStudentDashboard() {
        return studentDashboard;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }




}

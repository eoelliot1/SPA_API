package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8091/login")
public class LoginPage extends PageObject {

    // Login form elements
    @FindBy(name = "username")
    private WebElementFacade emailField;

    public WebElementFacade getEmailField() {
        return emailField;
    }

    public void setEmailField(WebElementFacade emailField) {
        this.emailField = emailField;
    }

    public WebElementFacade getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(WebElementFacade passwordField) {
        this.passwordField = passwordField;
    }

    public WebElementFacade getSignInButton() {
        return signInButton;
    }

    public void setSignInButton(WebElementFacade signInButton) {
        this.signInButton = signInButton;
    }

    public WebElementFacade getTrainerDashboardHeader() {
        return trainerDashboardHeader;
    }

    public void setTrainerDashboardHeader(WebElementFacade trainerDashboardHeader) {
        this.trainerDashboardHeader = trainerDashboardHeader;
    }

    public WebElementFacade getStudentDashboardHeader() {
        return studentDashboardHeader;
    }

    public void setStudentDashboardHeader(WebElementFacade studentDashboardHeader) {
        this.studentDashboardHeader = studentDashboardHeader;
    }

    @FindBy(name = "password")
    private WebElementFacade passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade signInButton;

    // Trainer dashboard element
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/h2")
    private WebElementFacade trainerDashboardHeader;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div")
    private WebElementFacade studentDashboardHeader;

    public void enterEmail(String email) {
        emailField.type(email);
    }

    public void enterPassword(String password) {
        passwordField.type(password);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public boolean isTrainerDashboardVisible() {
        return trainerDashboardHeader.isVisible();
    }

    public boolean isStudentDashboardVisible() {
        return studentDashboardHeader.isVisible();
    }


}

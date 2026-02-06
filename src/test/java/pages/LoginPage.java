package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8091/login")
public class LoginPage extends PageObject {

    @FindBy(id = "email-input")
    private WebElementFacade userNameField;

    @FindBy(id = "password-input")
    private WebElementFacade passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade loginButton;

    @FindBy(id = "trainer-dashboard")
    private WebElementFacade trainerDashboard;

    @FindBy(id = "student-dashboard")
    private WebElementFacade studentDashboard;

    @FindBy(xpath = "//button[text()='Logout']")
    private WebElementFacade logoutButton;

    public void enterUserName(String username) {
        userNameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();

    }

    public boolean isTrainerDashboardVisible() {
        return waitForCondition().until(webDriver -> trainerDashboard.isVisible());
    }

    public boolean isStudentDashboardVisible()
    {
        return waitForCondition().until(webDriver -> studentDashboard.isVisible());
    }

    public void clickLogout()
    {
        logoutButton.waitUntilClickable().click();
    }







}

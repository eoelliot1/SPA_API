package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
//import net.serenitybdd.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@DefaultUrl("http://localhost:8091/login")
public class LoginPage extends PageObject {

    @FindBy(id = "email-input")
    private WebElementFacade userNameField;

    @FindBy(id = "password-input")
    private WebElementFacade passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade loginButton;


    @FindBy(xpath = "/html/body/div[2]/div[1]/div")
    private WebElementFacade trainerDashboard;

    @FindBy(xpath = "/html/body/div[2]/div[1]/div")
    private WebElementFacade studentDashboard;

    @FindBy(xpath = "\"//button[text()='Logout']")
    private WebElementFacade logoutButton;


    public void enterUserName(String username) {
        withTimeoutOf(Duration.ofSeconds(10))
                .waitFor(userNameField)
                .type(username);
    }

    public void enterPassword(String password) {
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();

    }

    public boolean isTrainerDashboardVisible()
    {
        return trainerDashboard.waitUntilVisible().isVisible();
    }

    public boolean isStudentDashboardVisible()
    {
        return studentDashboard.isDisplayed();
    }
    public void clickLogout()
    {
        logoutButton.waitUntilClickable().click();
    }







}

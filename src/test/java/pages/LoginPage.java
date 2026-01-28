package pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8091/login")
public class LoginPage extends PageObject {

    @FindBy(name = "username")
    private WebElementFacade emailField;

    @FindBy(name = "password")
    private WebElementFacade passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElementFacade signInButton;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div")
    private WebElementFacade trainerDashboardHeader;

    @FindBy(xpath = "/html/body/div[1]/div[1]")
    private WebElementFacade studentDashboardHeader;

    public void openLoginPage() {
        open();
    }

    public void loginWith(String email, String password) {
        emailField.type(email);
        passwordField.type(password);
        signInButton.click();
    }

    public boolean trainerDashboardIsVisible() {
        return trainerDashboardHeader.waitUntilVisible().isVisible();
    }

    public boolean studentDashboardIsVisible() {
        return studentDashboardHeader.waitUntilVisible().isVisible();
    }

    public boolean isStillOnLoginPage() {
        return getDriver().getCurrentUrl().contains("/login");
    }
}

package pl.kuba.tau.bddsitetests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import pl.kuba.tau.pages.LoginPage;
import pl.kuba.tau.pages.RegisterPage;

public class CorrectLoginStepTest {

    private static final LoginPage LOGIN_PAGE;
    private static final RegisterPage REGISTER_PAGE;
    private static final WebDriver DRIVER = DriverFactory.getDriver();
    private final static String EMAIL = "takiegomaila@niktniepoda.pl";
    private final static String PASS = "secret";

    static {
        LOGIN_PAGE = new LoginPage(DRIVER);
        REGISTER_PAGE = new RegisterPage(DRIVER);
    }

    @Given("^login page form$")
    public void login_page_form() {
        LOGIN_PAGE.open();
        assertTrue(LOGIN_PAGE.isSingInButtonVisible());
    }

    @When("^we login with valid credentials$")
    public void we_login_with_valid_credentials() throws Throwable {

        LOGIN_PAGE.loginIntoAccount(EMAIL, PASS);
    }

    @Then("^we should see account page$")
    public void we_should_see_account_page() {
        assertTrue(LOGIN_PAGE.isLoginSuccessful());
        REGISTER_PAGE.getLogoutButton().click();
    }

}

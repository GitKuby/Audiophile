package pl.kuba.tau.bddsitetests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import pl.kuba.tau.pages.LoginPage;

public class InvalidLoginStepTest {

    private static final LoginPage LOGIN_PAGE;
    private static final WebDriver DRIVER = DriverFactory.getDriver();
    private final static String EXPECTED_URL = "http://automationpractice.com/index.php?controller=authentication";

    static {
        LOGIN_PAGE = new LoginPage(DRIVER);
    }

    @Given("^login form$")
    public void login_form() {
        LOGIN_PAGE.open();
        assertTrue(LOGIN_PAGE.isSingInButtonVisible());
    }

    @When("^we login with invalid credentials \\(\"([^\"]*)\" and \"([^\"]*)\"\\)$")
    public void we_login_with_invalid_credentials_and(String email, String pass) throws Throwable {
        LOGIN_PAGE.loginIntoAccount(email, pass);
        assertEquals(EXPECTED_URL, DRIVER.getCurrentUrl());

    }

    @Then("^we should see error message$")
    public void we_should_see_error_message() throws Throwable {
        assertTrue(LOGIN_PAGE.hasAuthenticationFailed());
    }

}

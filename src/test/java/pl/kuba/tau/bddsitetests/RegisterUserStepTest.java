package pl.kuba.tau.bddsitetests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import pl.kuba.tau.pages.LoginPage;
import pl.kuba.tau.pages.RegisterPage;
import static junit.framework.Assert.assertTrue;

public class RegisterUserStepTest {

    private static final LoginPage LOGIN_PAGE;
    private static final RegisterPage REGISTER_PAGE;
    private static final WebDriver DRIVER = DriverFactory.getDriver();
    private static String email = null;
    private static final Map<String, String> EMAILS = new HashMap<>();

    static {
        LOGIN_PAGE = new LoginPage(DRIVER);
        REGISTER_PAGE = new RegisterPage(DRIVER);
    }

    public static Map<String, String> getEmails() {
        return EMAILS;
    }

    private void generateEmail() {
        String generatedEmailPrefix = RandomStringUtils.random(10, true, false);
        email = String.format("%s@com.pl", generatedEmailPrefix);
    }

    public static void cleanp() {
        DRIVER.quit();
    }

    @Given("^registration form$")
    public void registration_form() throws Throwable {
        LOGIN_PAGE.open();
        generateEmail();
        LOGIN_PAGE.setEmailInput(email);
        LOGIN_PAGE.clickSubmitCreateButon();
        assertTrue(REGISTER_PAGE.isRegisterButtonVisible());
    }

    @When("^we add an user with \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", (\\d+), (\\d+), (\\d+),"
            + " \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", (\\d+), \"([^\"]*)\" and (\\d+)$")
    public void we_add_an_user_with_password_and(String gender, String firstName, String lastName,
            String pass, int day, int month, int year, String street, String city, String state,
            int zip, String country, int mobile) throws Throwable {

        EMAILS.put(email, pass);

        LOGIN_PAGE.open();
        LOGIN_PAGE.setEmailInput(email);
        LOGIN_PAGE.clickSubmitCreateButon();

        REGISTER_PAGE.setGender(gender);
        REGISTER_PAGE.setFirstName(firstName);
        REGISTER_PAGE.setLastName(lastName);
        REGISTER_PAGE.setPassword(pass);
        REGISTER_PAGE.setDateOfBirth(day, month, Integer.toString(year));
        REGISTER_PAGE.setStreet(street);
        REGISTER_PAGE.setCity(city);
        REGISTER_PAGE.setState(state);
        REGISTER_PAGE.setZipCode(Integer.toString(zip));
        REGISTER_PAGE.setCountry(country);
        REGISTER_PAGE.setMobilePhone(Integer.toString(mobile));
        REGISTER_PAGE.getRegistrationForm().click();

        assertTrue(REGISTER_PAGE.getLogoutButton().isDisplayed());

    }

    @Then("^we should see user's account$")
    public void we_should_see_user_s_account() throws Throwable {
        assertTrue(REGISTER_PAGE.getLogoutButton().isDisplayed());
        REGISTER_PAGE.getLogoutButton().click();
    }

}

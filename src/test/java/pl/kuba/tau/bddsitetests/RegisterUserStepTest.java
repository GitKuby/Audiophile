package pl.kuba.tau.bddsitetests;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.apache.commons.lang.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import pl.kuba.tau.pages.LoginPage;
import pl.kuba.tau.pages.RegisterPage;
import pl.kuba.tau.pages.StartPage;

public class RegisterUserStepTest {

    private static LoginPage loginPage;
    private static RegisterPage registerPage;
    private static WebDriver driver;
    private static String EMAIL = null;
    private final static String PASSWORD = "secret";

    static {

//        String binary = System.getProperty("phantomjs.binary");
//        assertNotNull(binary);
//        assertTrue(new File(binary).exists());
        PhantomJsDriverManager.getInstance().setup();
        driver = new PhantomJSDriver();
//        ChromeDriverManager.getInstance().setup();
//        driver = new ChromeDriver();
        driver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);

    }

    private void generateEmail() {
        String generatedEmailPrefix = RandomStringUtils.random(10, true, false);
        EMAIL = String.format("%s@com.pl", generatedEmailPrefix);
    }

    public static void cleanp() {
        driver.quit();
    }

    @Given("^registration form$")
    public void registration_form() throws Throwable {
        loginPage.open();
        generateEmail();
        loginPage.setEmailInput(EMAIL);
        loginPage.clickSubmitCreateButon();
        assertTrue(registerPage.isRegisterButtonVisible());
    }

    @When("^we add an user with \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", (\\d+), (\\d+), (\\d+),"
            + " \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", (\\d+), \"([^\"]*)\" and (\\d+)$")
    public void we_add_an_user_with_password_and(String gender, String firstName, String lastName,
            String pass, int day, int month, int year, String street, String city, String state,
            int zip, String country, int mobile) throws Throwable {

        loginPage.open();
        loginPage.setEmailInput(EMAIL);
        loginPage.clickSubmitCreateButon();

        registerPage.setGender(gender);
        registerPage.setFirstName(firstName);
        registerPage.setLastName(lastName);
        registerPage.setPassword(pass);
        registerPage.setDateOfBirth(day, month, Integer.toString(year));
        registerPage.setStreet(street);
        registerPage.setCity(city);
        registerPage.setState(state);
        registerPage.setZipCode(Integer.toString(zip));
        registerPage.setCountry(country);
        registerPage.setMobilePhone(Integer.toString(mobile));
        registerPage.getRegistrationForm().click();

        assertTrue(registerPage.getLogoutButton().isDisplayed());

    }

    @Then("^we should see user's account$")
    public void we_should_see_user_s_account() throws Throwable {
//        loginPage.open();
        assertTrue(registerPage.getLogoutButton().isDisplayed());
        registerPage.getLogoutButton().click();
    }
}

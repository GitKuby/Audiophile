package pl.kuba.tau.bddsitetests;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.kuba.tau.pages.LoginPage;
import pl.kuba.tau.pages.RegisterPage;
import pl.kuba.tau.pages.StartPage;

public class InvalidLoginStepTest {

    private StartPage startPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private static WebDriver driver;

    static {

//        String binary = System.getProperty("phantomjs.binary");
//        assertNotNull(binary);
//        assertTrue(new File(binary).exists());
//        PhantomJsDriverManager.getInstance().setup();
//        driver = new PhantomJSDriver();
//        ChromeDriverManager.getInstance().setup();
//        driver = new ChromeDriver();
//        driver.manage()
//                .timeouts()
//                .implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
    }

    @Given("^login form$")
    public void login_form() {
        throw new PendingException();
    }

    @When("^we login with invalid credentials \\(\"([^\"]*)\" and \"([^\"]*)\"\\)$")
    public void we_login_with_invalid_credentials_and(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^we should see error message$")
    public void we_should_see_error_message() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}

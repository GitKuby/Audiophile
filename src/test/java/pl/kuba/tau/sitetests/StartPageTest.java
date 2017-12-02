package pl.kuba.tau.sitetests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.kuba.tau.pages.LoginPage;
import pl.kuba.tau.pages.StartPage;
import pl.kuba.tau.pages.RegisterPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class StartPageTest {

    private final static String EXPECTED_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private static WebDriver driver;
    private StartPage startPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private static String EMAIL = null;
    private final static String PASSWORD = "secret";
    private final static String WIN_PATH = "c:\\tmp\\screenshot.png";
    private final static String LINUX_PATH = "//screenshot.png";

    @BeforeClass
    public static void driverSetup() {
        String generatedEmailPrefix = RandomStringUtils.random(10, true, false);
        EMAIL = String.format("%s@com.pl", generatedEmailPrefix);
//        String binary = System.getProperty("phantomjs.binary");
//        assertNotNull(binary);
//        assertTrue(new File(binary).exists());
//        ChromeDriverManager.getInstance().setup();
//        driver = new ChromeDriver();
        PhantomJsDriverManager.getInstance().setup();
        driver = new PhantomJSDriver();
        driver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void before() {
        startPage = new StartPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @AfterClass
    public static void cleanp() {
        driver.quit();
    }

    @Test
    public void checkPhoneNoVisibility() {
        startPage.open();
        Assert.assertNotNull(startPage.getPhoneNo());
    }

    @Test
    public void checkSliderVisibility() {
        startPage.open();
        Assert.assertNotNull(startPage.getLoremSlider());
    }

    @Test
    public void checkBasketVisibility() {
        startPage.open();
        Assert.assertNotNull(startPage.getBasketLink());
    }

    @Test
    public void checkBestSellers() {
        startPage.open();
        startPage.clickBestSellers();
        List<WebElement> elements = startPage.getProducts();
        assertEquals(7, elements.size());
        assertTrue(startPage.isPrintedChiffonDressVisible(elements));
    }

    @Test
    public void checkDressessButton() throws IOException {
        startPage.open();
        startPage.setMouseOverDressesButton();
        assertTrue(startPage.isDressesSubmenuVisible());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(getFilePath()));

    }

    private String getFilePath() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            return WIN_PATH;
        } else {
            return LINUX_PATH;
        }
    }

    @Test
    public void checkSignIn() throws InterruptedException {
        startPage.open();
        startPage.getSignIn().click();
        Thread.sleep(200);
        assertEquals(EXPECTED_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkInvalidSignInForm() throws InterruptedException {
        loginPage.open();
        loginPage.setEmailInput(EMAIL);
        loginPage.clickSubmitCreateButon();
        assertTrue(loginPage.getErrorMessage().isDisplayed());
    }

    @Test
    public void checkValidSignInForm() throws InterruptedException {
        loginPage.open();
        loginPage.setEmailInput(EMAIL);
        loginPage.clickSubmitCreateButon();

        registerPage.setGender("M");
        registerPage.setFirstName("John");
        registerPage.setLastName("Newman");
        registerPage.setPassword(PASSWORD);
        registerPage.setDateOfBirth(11, 11, "1980");
        registerPage.setStreet("Fifth Avenue");
        registerPage.setCity("New York");
        registerPage.setState("New York");
        registerPage.setZipCode("12345");
        registerPage.setCountry("United States");
        registerPage.setMobilePhone("100200300");
        registerPage.getRegistrationForm().click();

        assertTrue(registerPage.getLogoutButton().isDisplayed());
        registerPage.getLogoutButton().click();

    }

    @Test
    public void checkFailedAuthentication() throws InterruptedException {
        loginPage.open();
        loginPage.loginIntoAccount("badEmail%d@wrong.com", "someWrongPassword");
        assertTrue(loginPage.hasAuthenticationFailed());
    }

    @Test
    public void checkCorrectAuthentication() throws InterruptedException {
        loginPage.open();
        loginPage.loginIntoAccount(EMAIL, PASSWORD);
        assertTrue(loginPage.isLoginSuccessful());
    }

}

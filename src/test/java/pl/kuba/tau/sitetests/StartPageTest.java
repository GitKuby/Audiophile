package pl.kuba.tau.sitetests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.kuba.tau.pages.StartPage;

public class StartPageTest {

    private static WebDriver driver;
    private StartPage startPage;

    @BeforeClass
    public static void driverSetup() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Before
    public void before() {
        startPage = new StartPage(driver);
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
        startPage.clickBestSellers();

    }

}

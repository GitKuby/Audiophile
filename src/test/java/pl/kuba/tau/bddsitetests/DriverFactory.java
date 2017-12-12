package pl.kuba.tau.bddsitetests;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class DriverFactory {

    private static WebDriver driver;

    static {
//        String binary = System.getProperty("phantomjs.binary");
//        assertNotNull(binary);
//        assertTrue(new File(binary).exists());

//        ChromeDriverManager.getInstance().setup();
//        driver = new ChromeDriver();
    }

    private DriverFactory() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            PhantomJsDriverManager.getInstance().setup();
            driver = new PhantomJSDriver();
            driver.manage()
                    .timeouts()
                    .implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }
        return driver;
    }
}

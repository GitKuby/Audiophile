package pl.kuba.tau.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public void setEmailInput(String email) {
        driver.findElement(By.id("email_create")).sendKeys(email);
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector("#center_column > div"));
    }

    public void clickSubmitCreateButon() {
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public void loginIntoAccount(String email, String pass) {
        WebElement credentialsEmail = driver.findElement(By.id("email"));
        credentialsEmail.clear();
        credentialsEmail.sendKeys(email);

        WebElement credentialsPassword = driver.findElement(By.id("passwd"));
        credentialsPassword.clear();
        credentialsPassword.sendKeys(pass);
        clickSingIn();
    }

    public void clickSingIn() {
        driver.findElement(By.id("SubmitLogin")).click();
    }

    public boolean hasAuthenticationFailed() {
        WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOf(driver.findElement(By.cssSelector("#center_column > div.alert.alert-danger"))));
        return element.isDisplayed();
    }

    public boolean isLoginSuccessful() {
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions
                .visibilityOf(driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul"))));
        return element.isDisplayed();
    }
}

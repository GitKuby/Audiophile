package pl.kuba.tau.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setGender(String gender) {
        if ("M".equals(gender)) {
            driver.findElement(By.id("id_gender1")).click();
        } else if ("W".equals(gender)) {
            driver.findElement(By.id("id_gender2")).click();
        }
    }

    public void setFirstName(String firstName) {
        driver.findElement(By.id("customer_firstname")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(firstName);

    }

    public void setLastName(String lastName) {
        driver.findElement(By.id("customer_lastname")).click();
        driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
    }

    public void setPassword(String password) {
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).sendKeys(password);
    }

    public void setDateOfBirth(int day, int month, String year) {
        new Select(driver.findElement(By.id("days"))).selectByIndex(day);
        new Select(driver.findElement(By.id("months"))).selectByIndex(month);
        new Select(driver.findElement(By.id("years"))).selectByValue(year);

    }

    public void setStreet(String street) {
        driver.findElement(By.id("address1")).click();
        driver.findElement(By.id("address1")).sendKeys(street);
    }

    public void setCity(String city) {
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(city);
    }

    public void setState(String state) {
        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText(state);
    }

    public void setZipCode(String zip) {
        driver.findElement(By.id("postcode")).click();
        driver.findElement(By.id("postcode")).sendKeys(zip);
    }

    public void setCountry(String country) {
        new Select(driver.findElement(By.id("id_country"))).selectByVisibleText(country);
    }

    public void setMobilePhone(String mobileNo) {
        driver.findElement(By.id("phone_mobile")).click();
        driver.findElement(By.id("phone_mobile")).sendKeys(mobileNo);
    }

    public WebElement getRegistrationForm() {
        return driver.findElement(By.id("submitAccount"));
    }

    public WebElement getLogoutButton() {
        return driver.findElement(
                By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a"));
    }

    public boolean isRegisterButtonVisible() {
        return getRegistrationForm().isDisplayed();
    }

}

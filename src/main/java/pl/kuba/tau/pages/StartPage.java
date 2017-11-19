package pl.kuba.tau.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage {

    private static final String URL = "http://automationpractice.com";

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/span/strong")
    WebElement phoneNo;

    @FindBy(id = "homeslider")
    WebElement loremSlider;

    @FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div")
    WebElement basketLink;

    @FindBy(css = "#home-page-tabs > li.active > a")
    WebElement bestSellerButton;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPhoneNo() {
        return phoneNo;
    }

    public WebElement getLoremSlider() {
        return loremSlider;
    }

    public WebElement getBasketLink() {
        return basketLink;
    }

    public void open() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public void clickBestSellers() {
        bestSellerButton.click();
    }

    public boolean isBestSellersActive() {
//            driver.findElement.

        return false;

    }
}

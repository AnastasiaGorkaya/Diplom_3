package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPage extends BasePage {
    private By loginButton = By.xpath("//*[text()='Войти в аккаунт']");
    private By createOrderButton = By.xpath("//button[text()='Оформить заказ']");
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        clickOnElement(loginButton);
    }

    public void assertThatMainPageOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }
}
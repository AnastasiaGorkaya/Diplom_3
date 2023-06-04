package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginPage extends BasePage {
    private By loginField = By.xpath("//input[@type='text']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[text()='Войти']");

    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setLogin(String login) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(loginField));
        driver.findElement(loginField).sendKeys(login);
    }

    public void setPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        clickOnElement(loginButton);
    }

    @Step("Проверка открытия страницы авторизации")
    public void assertThatLoginPageOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Step("Авторизация")
    public void login(String login, String password) {
        setLogin(login);
        setPassword(password);
        clickLoginButton();
    }
}
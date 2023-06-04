package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class UserCabinetPage extends BasePage {
    private By nameField = By.xpath("//input[@name='name' and @type='text']");
    private By emailField = By.xpath("//input[@name='Name' and @type='text']");
    private By passwordField = By.xpath("//input[@name='name' and @type='password']");
    private By exitButton = By.xpath("//button[text()='Выход']");

    public static final String USER_CABINET_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    public UserCabinetPage(WebDriver driver) {
        super(driver);
    }

    public String getAuthorizedUserName() {
        return driver.findElement(nameField).getAttribute("value");
    }

    public String getAuthorizedUserEmail() {
        return driver.findElement(emailField).getAttribute("value");
    }

    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }

    public void waitForEmailField() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    public void assertThatCabinetPageOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(USER_CABINET_PAGE_URL));
        assertEquals(USER_CABINET_PAGE_URL, driver.getCurrentUrl());
    }
}
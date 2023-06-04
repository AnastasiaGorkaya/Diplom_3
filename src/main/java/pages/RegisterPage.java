package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegisterPage extends BasePage {
    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private By loginButton = By.xpath("//a[text()='Войти']");
    private By incorrectPasswordMessage = By.xpath("//*[text()='Некорректный пароль']");
    private By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By nameField = By.xpath("//input[1]");
    private By emailField = By.xpath("//input[2]");
    private By passwordField = By.xpath("//input[3]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        clickOnElement(loginButton);
    }

    public void clickRegisterButton() {
        clickOnElement(registerButton);
    }

    public void assertThatRegisterPageOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(REGISTER_PAGE_URL));
        assertEquals(REGISTER_PAGE_URL, driver.getCurrentUrl());
    }

    public void setName(String name) {
        List<WebElement> registerFormItems = driver.findElements(By.xpath("//input"));
        sendKeysToField(registerFormItems.get(0), name);
    }

    public void setEmail(String email) {
        List<WebElement> registerFormItems = driver.findElements(By.xpath("//input"));
        sendKeysToField(registerFormItems.get(1), email);
    }

    public void setPassword(String password) {
        List<WebElement> registerFormItems = driver.findElements(By.xpath("//input"));
        sendKeysToField(registerFormItems.get(2), password);
    }

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка отображения ошибки 'Некорректный пароль'")
    public void assertThatIncorrectPasswordMessageShows() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordMessage));
    }
}
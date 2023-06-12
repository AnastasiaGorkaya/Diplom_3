import generators.UserGenerator;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import rest.UserClient;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private Header header;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private User user;
    private final UserClient api = new UserClient();
    private ValidatableResponse createdUserResponse;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");
        driver = new ChromeDriver(options);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        registerPage = new RegisterPage(driver);
        header = new Header(driver);

        user = UserGenerator.getRandomUser();
        createdUserResponse = api.createUser(user);
    }

    @After
    public void tearDown() {
        String createdUserAuth = createdUserResponse.extract().path("accessToken");
        if( createdUserAuth != null ) {
            api.delete(createdUserAuth);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешный вход по кнопке «Войти в аккаунт» на главной")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFromMainPageLoginButton() {
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.assertThatMainPageOpened();
    }

    @Test
    @DisplayName("Успешный вход через кнопку «Личный кабинет»")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFromMainPageCabinetButton() {
        driver.get(MainPage.MAIN_PAGE_URL);
        header.clickCabinetButton();
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.assertThatMainPageOpened();
    }

    @Test
    @DisplayName("Успешный вход через кнопку в форме регистрации")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFromRegisterPageLoginButton() {
        driver.get(RegisterPage.REGISTER_PAGE_URL);
        registerPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.assertThatMainPageOpened();
    }

    @Test
    @DisplayName("Успешный вход через кнопку в форме восстановления пароля")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFromForgotPasswordPageLoginButton() {
        driver.get(ForgotPasswordPage.FORGOT_PASSWORD_PAGE_URL);
        forgotPasswordPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.assertThatMainPageOpened();
    }
}
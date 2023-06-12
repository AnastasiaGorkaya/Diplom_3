import generators.UserGenerator;
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

public class UserCabinetPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private Header header;
    private UserCabinetPage userCabinetPage;
    private User user;
    private UserClient api = new UserClient();
    private ValidatableResponse createdUserResponse;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");
        driver = new  ChromeDriver(options);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        header = new Header(driver);
        userCabinetPage = new UserCabinetPage(driver);

        user = UserGenerator.getRandomUser();
        createdUserResponse = api.createUser(user);
    }

    @After
    public void tearDown() {
        String createdUserAuth = createdUserResponse.extract().path("accessToken");
        if(createdUserAuth != null) {
            api.delete(createdUserAuth);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешный переход в ЛК по клику на «Личный кабинет».")
    public void jumpToUserCabinetPageFromMainPageWhenUserLoggedSuccess() {
        driver.get(LoginPage.LOGIN_PAGE_URL);
        loginPage.login(user.getEmail(), user.getPassword());
        header.clickCabinetButton();

        userCabinetPage.assertThatCabinetPageOpened();
    }

    @Test
    @DisplayName("Успешный переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void jumpToConstructorPageFromUserCabinetPageSuccess() {
        driver.get(LoginPage.LOGIN_PAGE_URL);
        loginPage.login(user.getEmail(), user.getPassword());
        header.clickCabinetButton();

        userCabinetPage.assertThatCabinetPageOpened();
        header.clickConstructorButton();

        mainPage.assertThatMainPageOpened();
    }

    @Test
    @DisplayName("Успешный выход по кнопке «Выйти» в личном кабинете.")
    public void logOutViaExitButtonFromCabinetPageWhenUserLoggedSuccess() {
        driver.get(LoginPage.LOGIN_PAGE_URL);
        loginPage.login(user.getEmail(), user.getPassword());
        header.clickCabinetButton();

        userCabinetPage.clickExitButton();
        loginPage.assertThatLoginPageOpened();
    }
}
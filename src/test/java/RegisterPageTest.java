import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import rest.UserClient;

public class RegisterPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User userToRegister;
    private final Faker faker = new Faker();
    private final UserClient api = new UserClient();

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");
        driver = new ChromeDriver(options);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        String authToken = api.getAuthToken(userToRegister);
        if(authToken != null) {
            api.delete(authToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registerUserSuccess() {
        driver.get(RegisterPage.REGISTER_PAGE_URL);
        userToRegister = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(6, 10));
        registerPage.register(userToRegister.getName(), userToRegister.getEmail(), userToRegister.getPassword());

        loginPage.assertThatLoginPageOpened();
    }

    @Test
    @DisplayName("Вывод ошибки при регистрации с некорректным паролем. Минимальный пароль — шесть символов.")
    public void registerUserWhenPasswordNotValidFails() {
        driver.get(RegisterPage.REGISTER_PAGE_URL);
        userToRegister = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(1, 5));
        registerPage.register(userToRegister.getName(), userToRegister.getEmail(), userToRegister.getPassword());

        registerPage.assertThatIncorrectPasswordMessageShows();
    }
}
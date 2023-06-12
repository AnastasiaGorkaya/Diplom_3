import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import models.Ingredient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver driver;
    private Constructor constructor;
    private final String expected;
    private final Ingredient ingredient;

    public ConstructorTest(String expected, Ingredient ingredient) {
        this.expected = expected;
        this.ingredient = ingredient;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Соусы", Ingredient.SAUCE},
                {"Булки", Ingredient.BUN},
                {"Начинки", Ingredient.FILLING}
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");
        driver = new ChromeDriver(options);
        constructor = new Constructor(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Работают переходы к разделам конструктора")
    @Severity(SeverityLevel.NORMAL)
    public void jumpToConstructorTabWorksCorrect() {
        driver.get(MainPage.MAIN_PAGE_URL);

        constructor.clickConstructorTab(ingredient);
        assertEquals(expected, constructor.getActiveIngredientTab());
    }
}
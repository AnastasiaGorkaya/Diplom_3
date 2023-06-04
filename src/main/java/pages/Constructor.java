package pages;

import io.qameta.allure.Step;
import models.Ingredient;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class Constructor extends BasePage {
    private By bunTab = By.xpath("//span[text()='Булки']");
    private By sauceTab = By.xpath("//span[text()='Соусы']");
    private By fillingTab = By.xpath("//span[text()='Начинки']");
    private By activeTab = By.xpath("//div[contains(@class, 'tab_type_current')]/span");

    public Constructor(WebDriver driver) {
        super(driver);
    }

    @Step("Получить текст активной вкладки")
    public String getActiveIngredientTab() {
        return driver.findElement(activeTab).getText();
    }

    @Step("Открыть вкладку конструктора")
    public void clickConstructorTab(Ingredient ingredient) {
        switch(ingredient){
            case BUN:
                try {
                    clickOnElement(bunTab);
                } catch (ElementClickInterceptedException e) {
                    System.out.println("Элемент уже выбран");
                }
                break;
            case SAUCE:
                clickOnElement(sauceTab);
                break;
            case FILLING:
                clickOnElement(fillingTab);
                break;
            default:
                System.out.println("Unknown type of ingredient");
                break;
        }
    }
}
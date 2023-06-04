package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class Header extends BasePage {
    private By cabinetButton = By.xpath("//*[text()='Личный Кабинет']");
    private By constructorButton = By.xpath("//*[text()='Конструктор']");

    public Header(WebDriver driver) {
        super(driver);
    }

    public void clickCabinetButton() {
        clickOnElement(cabinetButton);
    }

    public void clickConstructorButton() {
        clickOnElement(constructorButton);
    }
}
package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы
    private final By profileText = By.xpath("//a[@class='Account_nav__LgAli text text_type_main-medium text_color_inactive'][1]");
    private final By exitButton = By.xpath("//button[text()='Выход']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    // Проверка: отображается ли текст "Профиль"
    public boolean isProfilePageDisplayed() {
        return driver.findElement(profileText).isDisplayed();
    }

    // Клик по кнопке "Выход"
    public void clickLogout() {
        driver.findElement(exitButton).click();
    }

    // Клик по кнопке "Конструктор"
    public void clickConstructor() {
        driver.findElement(constructorButton).click();
    }

    // Клик по логотипу Stellar Burgers
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}

package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private WebDriver driver;

    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");
    private final By profileButton = By.xpath(".//a[text() = 'Профиль']");
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private final By logoButton = By.xpath(".//header/nav/div");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Ожидание открытия страницы профиля")
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(profileButton));
    }

    @Step("Видимость кнопки выхода")
    public boolean isLogoutLinkVisible() {
        try {
            return driver.findElement(logoutButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Нажатие на кнопку Конструктор")
    public void clickConstructButton() {
        waitForLoadPage();
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на логотип")
    public void clickLogo() {
        waitForLoadPage();
        driver.findElement(logoButton).click();
    }

    @Step("Нажатие на кнопку Выход")
    public void clickLogoutButton() {
        waitForLoadPage();
        driver.findElement(logoutButton).click();
    }
}

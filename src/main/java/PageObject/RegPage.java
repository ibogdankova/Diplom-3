package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegPage {
    private final WebDriver driver;

    // Локаторы полей регистрации
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.linkText("Войти");

    public RegPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void submitRegistration() {
        driver.findElement(registerButton).click();
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}

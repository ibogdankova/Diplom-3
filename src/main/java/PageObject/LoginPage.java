package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    // Локаторы элементов формы логина
    private final By emailField = By.xpath(".//input[@name='name' or @name='email']");
    private final By passwordField = By.xpath(".//input[@name='Пароль' or @name='password']");
    private final By loginButton = By.xpath("//div/form/button");
    private final By registerLink = By.xpath(".//a[@href='/register']");
    private final By restorePasswordLink = By.xpath(".//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void goToRegistration() {
        driver.findElement(registerLink).click();
    }

    public void goToPasswordRecovery() {
        driver.findElement(restorePasswordLink).click();
    }

    public boolean isLoginFormVisible() {
        return driver.findElement(loginButton).isDisplayed();
    }
}

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import PageObject.*;
import service.WebDriverContainer;
import user.User;
import user.UserAPI;
import user.UserGenerator;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private User testUser;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegPage regPage;
    private RecPassPage recPassPage;

    @Before
    @DisplayName("Подготовка: создание пользователя через API и инициализация страниц")
    public void setUp() {
        driver = WebDriverContainer.init();
        testUser = UserGenerator.generateValidUser();
        UserAPI.deleteIfExists(testUser);
        UserAPI.register(testUser);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        regPage = new RegPage(driver);
        recPassPage = new RecPassPage(driver);
    }

    @After
    @DisplayName("Очистка: удаление пользователя и закрытие браузера")
    public void tearDown() {
        UserAPI.deleteIfExists(testUser);
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной странице")
    public void loginFromMainPageLoginButton() {
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void loginFromPersonalCabinetButton() {
        mainPage.openMainPage();
        mainPage.clickPersonalCabinetButton();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через форму регистрации")
    public void loginFromRegistrationPage() {
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        loginPage.goToRegistration();
        regPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через форму восстановления пароля")
    public void loginFromPasswordRecoveryPage() {
        mainPage.openMainPage();
        mainPage.clickLoginButton();
        loginPage.goToPasswordRecovery();
        recPassPage.clickLoginLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }
}

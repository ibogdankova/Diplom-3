import PageObject.LoginPage;
import PageObject.RegPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import service.WebDriverContainer;
import user.User;
import user.UserAPI;
import user.UserGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private WebDriver driver;
    private User user;

    @Before
    @Step("Инициализация WebDriver и подготовка пользователя")
    public void setUp() {
        driver = WebDriverContainer.init();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации при вводе корректных данных: имя, email и пароль (6+ символов).")
    public void testSuccessfulRegistration() {
        user = UserGenerator.generateValidUser();
        UserAPI.deleteIfExists(user);

        RegPage regPage = new RegPage(driver);
        regPage.open();
        regPage.enterName(user.getName());
        regPage.enterEmail(user.getEmail());
        regPage.enterPassword(user.getPassword());
        regPage.submitRegistration();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Ожидалась форма логина после успешной регистрации", loginPage.isLoginFormVisible());
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    @Description("Проверка, что при вводе пароля менее 6 символов пользователь остаётся на странице регистрации.")
    public void testRegistrationWithInvalidPassword() {
        user = UserGenerator.generateUserWithInvalidPassword();

        RegPage regPage = new RegPage(driver);
        regPage.open();
        regPage.enterName(user.getName());
        regPage.enterEmail(user.getEmail());
        regPage.enterPassword(user.getPassword());
        regPage.submitRegistration();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("При ошибке регистрации должно оставаться на странице регистрации",
                "https://stellarburgers.nomoreparties.site/register", currentUrl);
    }

    @After
    @Step("Завершение работы драйвера и удаление пользователя")
    public void tearDown() {
        if (user != null) {
            UserAPI.deleteIfExists(user);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}

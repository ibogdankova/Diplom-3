import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import PageObject.MainPage;
import PageObject.LoginPage;
import PageObject.ProfilePage;
import user.User;
import user.UserAPI;
import user.UserGenerator;
import service.WebDriverContainer;

import static org.junit.Assert.assertTrue;

public class ProfileTest {

    private WebDriver driver;
    private User user;

    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    @DisplayName("Создание пользователя и авторизация")
    public void setUp() {
        driver = WebDriverContainer.init();
        user = UserGenerator.generateValidUser();
        UserAPI.deleteIfExists(user);
        UserAPI.register(user);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        mainPage.openMainPage();
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @After
    @DisplayName("Удаление пользователя и закрытие драйвера")
    public void tearDown() {
        UserAPI.deleteIfExists(user);
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void checkGoToProfilePage() {
        mainPage.clickPersonalCabinetButton();
        profilePage.waitForLoadPage(); // Явное ожидание загрузки
        assertTrue("Кнопка выхода не найдена — возможно, страница профиля не открылась",
                profilePage.isLogoutLinkVisible());
    }

    @Test
    @DisplayName("Переход в конструктор по кнопке 'Конструктор'")
    public void checkConstructorViaButton() {
        mainPage.clickPersonalCabinetButton();
        profilePage.clickConstructButton();
        assertTrue("Контейнер ингредиентов не отображается", mainPage.isIngredientsContainerVisible());
    }

    @Test
    @DisplayName("Переход в конструктор по клику на логотип")
    public void checkConstructorViaLogo() {
        mainPage.clickPersonalCabinetButton();
        profilePage.clickLogo();
        assertTrue("Контейнер ингредиентов не отображается", mainPage.isIngredientsContainerVisible());
    }

}

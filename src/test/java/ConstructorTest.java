import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import PageObject.MainPage;
import service.WebDriverContainer;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    @DisplayName("Открытие главной страницы перед каждым тестом")
    public void setUp() {
        driver = WebDriverContainer.init();
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitForLoadHomePage();
    }

    @After
    @DisplayName("Закрытие браузера после каждого теста")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Булки'")
    public void testOpenBunsSection() {
        mainPage.clickSectionSauce();

        // Добавим паузу, чтобы избежать спама кликами по вкладкам
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                d -> mainPage.getCurrentSectionType().equals("Соусы")
        );

        mainPage.clickSectionBuns();

        // Явное ожидание, что в активном разделе появится нужный текст
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                d -> mainPage.getCurrentSectionType().equals("Булки")
        );

        assertEquals("Булки", mainPage.getCurrentSectionType());
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    public void testOpenSaucesSection() {
        mainPage.clickSectionSauce();

        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                d -> mainPage.getCurrentSectionType().equals("Соусы")
        );

        assertEquals("Соусы", mainPage.getCurrentSectionType());
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    public void testOpenFillingsSection() {
        mainPage.clickSectionFilling();

        new WebDriverWait(driver, Duration.ofSeconds(2)).until(
                d -> mainPage.getCurrentSectionType().equals("Начинки")
        );

        assertEquals("Начинки", mainPage.getCurrentSectionType());
    }
}

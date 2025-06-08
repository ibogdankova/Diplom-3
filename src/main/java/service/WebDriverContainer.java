package service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverContainer {

    public static WebDriver init() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        if (browser.equals("yandex")) {
            // Путь до  chromedriver нужной версии
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");
            //Путь до Яндекс.Браузера
            options.setBinary("C:\\Users\\ibogdankova\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else {
            // Chrome (управляется WebDriverManager)
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        }

        return new ChromeDriver(options);
    }
}

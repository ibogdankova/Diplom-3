package service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverContainer {

    public static WebDriver init() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);

            case "yandex":
                WebDriverManager.chromedriver().setup();
                options.setBinary("C:\\Users\\ibogdankova\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                return new ChromeDriver(options);

            default:
                throw new RuntimeException("Неизвестный браузер: " + browser);
        }
    }
}

package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static final Logger log = LogManager.getLogger(DriverManager.class);
    private static WebDriver driver;
    private static Properties properties;

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            loadConfig();
            String browserName = properties.getProperty("browser");

            switch (browserName.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    log.error("Unsupported browser: " + browserName);
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitWait"))));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static String getBaseUrl() {
        loadConfig();
        return properties.getProperty("baseUrl");
    }
    public static String getPhoneNumber() {
        loadConfig();
        return properties.getProperty("phoneNumber");
    }

    public static String getPaymentAmount() {
        loadConfig();
        return properties.getProperty("paymentAmount");
    }


    private static void loadConfig(){
        if(properties == null){
            properties = new Properties();
            try (InputStream input = DriverManager.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    log.error("Sorry, unable to find config.properties");
                    throw new IOException("Sorry, unable to find config.properties");
                }
                properties.load(input);
            } catch (IOException e) {
                log.error("Error loading config: " + e.getMessage());
                throw new RuntimeException("Error loading config", e);
            }
        }

    }
}

package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Logger log = LogManager.getLogger(ConfigManager.class);
    private static Properties properties;

    private ConfigManager() {}

    public static String getBaseUrl() {
        loadConfig();
        return properties.getProperty("baseUrl");
    }
    private static void loadConfig() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
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
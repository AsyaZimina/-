package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    private static final Logger log = LogManager.getLogger(JsonUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode readJsonFile(String fileName) {
        try (InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(fileName)) {
             if(inputStream == null){
                  log.error("Unable to find file: " + fileName);
                throw new IOException("Unable to find file: " + fileName);
            }
            return mapper.readTree(inputStream);
        } catch (IOException e) {
            log.error("Error reading file: " + e.getMessage());
           throw new RuntimeException("Error reading file: " + e);
        }
    }
}
package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConfigManager;

import java.util.Map;

public class RequestMethods {
    private static final Logger log = LogManager.getLogger(RequestMethods.class);
    private final String baseUrl;

    public RequestMethods() {
        this.baseUrl = ConfigManager.getBaseUrl();
        RestAssured.baseURI = this.baseUrl;
    }

    public Response get(String path, Map<String, ?> queryParams) {
        log.info("Sending GET request to: " + baseUrl + path + " with query params: " + queryParams);
        RequestSpecification request = RestAssured.given();
        if (queryParams != null) {
            request.queryParams(queryParams);
        }
        Response response = request.get(path);
        log.info("Response Status code:" + response.statusCode() + " Response body: " + response.getBody().prettyPrint());
        return response;
    }
    public Response get(String path) {
        log.info("Sending GET request to: " + baseUrl + path);
        RequestSpecification request = RestAssured.given();
        Response response = request.get(path);
        log.info("Response Status code:" + response.statusCode() + " Response body: " + response.getBody().prettyPrint());
        return response;
    }


    public Response post(String path, Object body) {
        log.info("Sending POST request to: " + baseUrl + path + " with body: " + body);
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(path);
         log.info("Response Status code:" + response.statusCode() + " Response body: " + response.getBody().prettyPrint());
         return response;
    }

    public Response put(String path, Object body) {
        log.info("Sending PUT request to: " + baseUrl + path + " with body: " + body);
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .put(path);
        log.info("Response Status code:" + response.statusCode() + " Response body: " + response.getBody().prettyPrint());
        return response;
    }

    public Response delete(String path) {
        log.info("Sending DELETE request to: " + baseUrl + path);
        Response response = RestAssured.given().delete(path);
        log.info("Response Status code:" + response.statusCode() + " Response body: " + response.getBody().prettyPrint());
        return response;
    }
}
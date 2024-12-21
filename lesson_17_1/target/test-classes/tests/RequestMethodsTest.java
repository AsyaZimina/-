package tests;

import api.RequestMethods;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestMethodsTest {
    private static final Logger log = LogManager.getLogger(RequestMethodsTest.class);
    private RequestMethods requestMethods;
    private JsonNode testData;

    @BeforeClass
    public void setup() {
        requestMethods = new RequestMethods();
        testData = JsonUtils.readJsonFile("testData.json");
        log.info("Setup completed");
    }

    @Test(dataProvider = "getPaths")
    public void testGetRequest(String path, Map<String, ?> queryParams) {
        Response response = requestMethods.get(path, queryParams);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200 for GET: " + path);
        if(queryParams != null){
            queryParams.forEach((key, value) -> {
                 String bodyValue = response.getBody().jsonPath().getString("args."+key);
                 Assert.assertEquals(bodyValue, value, "Body value doesn't match for key: " + key + " in GET: " + path);
            });
        }
    }

     @Test(dataProvider = "getPathsWithoutParams")
    public void testGetRequestWithoutParams(String path) {
        Response response = requestMethods.get(path);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200 for GET: " + path);
    }

    @Test
    public void testPostRequest() {
        Object requestBody = testData.get("testPostData");
        Response response = requestMethods.post("/post", requestBody);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200 for POST");
        String actualBody = response.getBody().asString();
        try {
            JSONAssert.assertEquals(requestBody.toString(), response.getBody().jsonPath().getString("json").toString(), JSONCompareMode.STRICT);
        } catch (Exception e) {
             log.error("Error while comparing json: " + e.getMessage());
             Assert.fail("JSON mismatch in POST response");
         }

    }

    @Test
    public void testPutRequest() {
        Object requestBody = testData.get("testPostData");
        Response response = requestMethods.put("/put", requestBody);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200 for PUT");
        String actualBody = response.getBody().asString();
        try {
            JSONAssert.assertEquals(requestBody.toString(), response.getBody().jsonPath().getString("json").toString(), JSONCompareMode.STRICT);
        } catch (Exception e) {
            log.error("Error while comparing json: " + e.getMessage());
            Assert.fail("JSON mismatch in PUT response");
        }
    }

    @Test
    public void testDeleteRequest() {
         Response response = requestMethods.delete("/delete");
         Assert.assertEquals(response.statusCode(), 200, "Status code is not 200 for DELETE");
     }

    @DataProvider(name = "getPaths")
    public Object[][] getPaths() {
       Map<String, String> queryParams = new HashMap<>();
       queryParams.put("param1", "value1");
       queryParams.put("param2", "value2");
        return new Object[][]{
                {"/get", queryParams},
                {"/get?key=value", null},
                {"/get/123", null}
        };
    }
    @DataProvider(name = "getPathsWithoutParams")
    public Object[][] getPathsWithoutParams() {
         return new Object[][]{
                 {"/ip"},
                 {"/time"},
                 {"/user-agent"},
                 {"/uuid"},
        };
    }
}
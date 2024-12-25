package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;
import pages.PaymentDetailsPage;
import utils.DriverManager;
import api.RequestMethods;
import com.fasterxml.jackson.databind.JsonNode;
import io.qameta.allure.*;
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


public class MtsPaymentTest {
    private static final Logger log = LogManager.getLogger(MtsPaymentTest.class);
    private MainPage mainPage;
    private PaymentDetailsPage paymentDetailsPage;
    private RequestMethods requestMethods;
    private JsonNode testData;
    
    @BeforeClass
    public void setup() {
        requestMethods = new RequestMethods();
        testData = JsonUtils.readJsonFile("testData.json");
        log.info("Setup completed");
    }
    @BeforeMethod
    public void setup(){
        DriverManager.getDriver().get(DriverManager.getBaseUrl());
        mainPage = new MainPage(DriverManager.getDriver());
        paymentDetailsPage = new PaymentDetailsPage(DriverManager.getDriver());
        log.info("Setup completed for a test");
    }
    
    @Test(dataProvider = "getPaths")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GET request with query params")
    @Story("GET")
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
     @Severity(SeverityLevel.NORMAL)
     @Description("Verify GET request without query params")
     @Story("GET")
    public void testGetRequestWithoutParams(String path) {
        Response response = requestMethods.get(path);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200 for GET: " + path);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify POST request")
    @Story("POST")
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
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify PUT request")
    @Story("PUT")
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
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify DELETE request")
    @Story("DELETE")
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
    
    @Test
    public void testPaymentBlockTitle() {
        log.info("Starting testPaymentBlockTitle");
        String actualTitle = mainPage.getPaymentBlockTitle();
        Assert.assertEquals(actualTitle, "Онлайн пополнение без комиссии");
        log.info("testPaymentBlockTitle PASSED");
    }

    @Test
    public void testPaymentLogos() {
        log.info("Starting testPaymentLogos");
        int actualLogosCount = mainPage.getPaymentLogosCount();
        Assert.assertTrue(actualLogosCount > 0,"No payment logos found");
        log.info("testPaymentLogos PASSED");
    }

    @Test
    public void testMoreDetailsLink() {
        log.info("Starting testMoreDetailsLink");
        mainPage.clickMoreDetailsLink();
        String actualTitle = mainPage.getMoreDetailsPageTitle();
        Assert.assertTrue(actualTitle.contains("Пополнение счета"));
        log.info("testMoreDetailsLink PASSED");
    }

    @Test
    public void testContinueButtonFunctionality(){
        log.info("Starting testContinueButtonFunctionality");
        mainPage.clickMobileServiceTab();
        mainPage.fillPhoneNumber(DriverManager.getPhoneNumber());
        mainPage.clickContinueButton();
        Assert.assertTrue(paymentDetailsPage.getPaymentDetailsTitle().contains("Оплата услуг"));
        log.info("testContinueButtonFunctionality PASSED");
    }

    @Test
    public void testPlaceholderTexts(){
        log.info("Starting testPlaceholderTexts");
        Assert.assertEquals(mainPage.getPlaceholderText("Услуги связи", "Номер телефона"), "Номер телефона");
        Assert.assertEquals(mainPage.getPlaceholderText("Услуги связи", "Сумма"), "Сумма");
        mainPage.clickHomeInternetTab();
        Assert.assertEquals(mainPage.getPlaceholderText("Домашний интернет", "Номер телефона"), "Номер телефона");
        Assert.assertEquals(mainPage.getPlaceholderText("Домашний интернет", "Номер договора"), "Номер договора");
        mainPage.clickInstallmentTab();
        Assert.assertEquals(mainPage.getPlaceholderText("Рассрочка", "Номер телефона"), "Номер телефона");
        Assert.assertEquals(mainPage.getPlaceholderText("Рассрочка", "Номер договора"), "Номер договора");
        mainPage.clickDebtTab();
        Assert.assertEquals(mainPage.getPlaceholderText("Задолженность", "Номер телефона"), "Номер телефона");
        Assert.assertEquals(mainPage.getPlaceholderText("Задолженность", "Номер договора"), "Номер договора");
        log.info("testPlaceholderTexts PASSED");
    }

    @Test
    public void testPaymentDetailsPage(){
        log.info("Starting testPaymentDetailsPage");
        mainPage.clickMobileServiceTab();
        mainPage.fillPhoneNumber(DriverManager.getPhoneNumber());
        mainPage.fillPaymentAmount(DriverManager.getPaymentAmount());
        mainPage.clickContinueButton();
        Assert.assertTrue(paymentDetailsPage.getPaymentDetailsTitle().contains("Оплата услуг"));
        Assert.assertEquals(paymentDetailsPage.getPaymentAmount(), "Сумма к оплате:");
        Assert.assertEquals(paymentDetailsPage.getPaymentAmountValue(), DriverManager.getPaymentAmount() + ",00 BYN");
        Assert.assertEquals(paymentDetailsPage.getPhoneNumberValue(), DriverManager.getPhoneNumber());
        Assert.assertTrue(paymentDetailsPage.getSubmitButtonValue().contains("Оплатить " + DriverManager.getPaymentAmount() + " BYN"));
        Assert.assertTrue(paymentDetailsPage.getPaymentMethodsLogosCount() > 0, "No Payment System Logos Found");
        Assert.assertEquals(paymentDetailsPage.getCardPlaceholderText("Номер карты"), "Номер карты");
        Assert.assertEquals(paymentDetailsPage.getCardPlaceholderText("ММ/ГГ"), "ММ/ГГ");
        Assert.assertEquals(paymentDetailsPage.getCardPlaceholderText("CVC"), "CVC");
        log.info("testPaymentDetailsPage PASSED");
    }


    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();
        log.info("Tear down completed");
    }
}

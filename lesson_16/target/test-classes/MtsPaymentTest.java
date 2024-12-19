package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;
import pages.PaymentDetailsPage;
import utils.DriverManager;


public class MtsPaymentTest {
    private static final Logger log = LogManager.getLogger(MtsPaymentTest.class);
    private MainPage mainPage;
    private PaymentDetailsPage paymentDetailsPage;

    @BeforeMethod
    public void setup(){
        DriverManager.getDriver().get(DriverManager.getBaseUrl());
        mainPage = new MainPage(DriverManager.getDriver());
        paymentDetailsPage = new PaymentDetailsPage(DriverManager.getDriver());
        log.info("Setup completed for a test");

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

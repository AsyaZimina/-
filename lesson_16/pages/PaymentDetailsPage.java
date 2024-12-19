package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class PaymentDetailsPage {
    private static final Logger log = LogManager.getLogger(PaymentDetailsPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(xpath = "//div[@class='b-payment-card__form-title']/h3")
    private WebElement paymentDetailsTitle;
    @FindBy(xpath = "//div[@class='b-payment-card__form-amount']/span")
    private  WebElement paymentAmount;
    @FindBy(xpath = "//div[@class='b-payment-card__form-amount']/span[@class='b-payment-card__form-amount-value']")
    private WebElement paymentAmountValue;
    @FindBy(xpath = "//button[@class='b-payment-card__form-submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//div[@class='b-payment-card__form-info']/span[contains(text(),'Номер телефона')]/following-sibling::span")
    private  WebElement phoneNumberValue;

    @FindBy(xpath = "//input[@name='card_number']")
    private WebElement cardNumberField;
    @FindBy(xpath = "//input[@name='card_date']")
    private WebElement cardDateField;
    @FindBy(xpath = "//input[@name='card_cvc']")
    private WebElement cardCVCField;

    @FindBy(xpath = "//div[@class='b-payment-card__payment-methods']/img")
    private List<WebElement> paymentMethodsLogos;


    public PaymentDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public String getPaymentDetailsTitle(){
        wait.until(ExpectedConditions.visibilityOf(paymentDetailsTitle));
        return paymentDetailsTitle.getText();
    }

    public String getPaymentAmount(){
        wait.until(ExpectedConditions.visibilityOf(paymentAmount));
        return paymentAmount.getText();
    }

    public String getPaymentAmountValue(){
        wait.until(ExpectedConditions.visibilityOf(paymentAmountValue));
        return paymentAmountValue.getText();
    }
    public String getPhoneNumberValue(){
        wait.until(ExpectedConditions.visibilityOf(phoneNumberValue));
        return phoneNumberValue.getText();
    }
    public String getSubmitButtonValue(){
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        return submitButton.getText();
    }
    public int getPaymentMethodsLogosCount(){
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentMethodsLogos));
        return paymentMethodsLogos.size();
    }
    public String getCardPlaceholderText(String fieldName){
        By locator = null;
        if(fieldName.equals("Номер карты")){
            locator = By.xpath("//input[@name='card_number']");
        } else if(fieldName.equals("ММ/ГГ")){
            locator = By.xpath("//input[@name='card_date']");
        }else if(fieldName.equals("CVC")){
            locator = By.xpath("//input[@name='card_cvc']");
        }
        WebElement field = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(field));
        return field.getAttribute("placeholder");
    }


}
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

public class MainPage {
    private static final Logger log = LogManager.getLogger(MainPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//div[@class='b-payment__title']/h2")
    private WebElement paymentBlockTitle;

    @FindBy(xpath = "//div[@class='b-payment__logos']/img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//a[@class='b-payment__more-link']")
    private WebElement moreDetailsLink;

    @FindBy(xpath = "//button[@data-tab='mobile']")
    private WebElement mobileServiceTab;

    @FindBy(xpath = "//button[@data-tab='home']")
    private WebElement homeInternetTab;

    @FindBy(xpath = "//button[@data-tab='installment']")
    private WebElement installmentTab;

    @FindBy(xpath = "//button[@data-tab='debt']")
    private WebElement debtTab;

    @FindBy(xpath = "//input[@name='pay_phone']")
    private WebElement phoneNumberField;
    @FindBy(xpath = "//input[@name='pay_amount']")
    private WebElement amountField;

    @FindBy(xpath = "//button[@id='b-payment__submit']")
    private WebElement continueButton;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getPaymentBlockTitle() {
        wait.until(ExpectedConditions.visibilityOf(paymentBlockTitle));
        return paymentBlockTitle.getText();
    }

    public int getPaymentLogosCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentLogos));
        return paymentLogos.size();
    }
    public void clickMoreDetailsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(moreDetailsLink));
        moreDetailsLink.click();
    }
    public String getMoreDetailsPageTitle() {
        wait.until(ExpectedConditions.titleContains("Пополнение счета"));
        return driver.getTitle();
    }

    public void clickMobileServiceTab(){
        wait.until(ExpectedConditions.elementToBeClickable(mobileServiceTab));
        mobileServiceTab.click();
    }

    public void clickHomeInternetTab(){
        wait.until(ExpectedConditions.elementToBeClickable(homeInternetTab));
        homeInternetTab.click();
    }

    public void clickInstallmentTab(){
        wait.until(ExpectedConditions.elementToBeClickable(installmentTab));
        installmentTab.click();
    }

    public void clickDebtTab(){
        wait.until(ExpectedConditions.elementToBeClickable(debtTab));
        debtTab.click();
    }

    public void fillPhoneNumber(String number) {
        wait.until(ExpectedConditions.visibilityOf(phoneNumberField));
        phoneNumberField.sendKeys(number);
    }
    public void fillPaymentAmount(String amount){
        wait.until(ExpectedConditions.visibilityOf(amountField));
        amountField.sendKeys(amount);
    }


    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    public String getPlaceholderText(String serviceType, String fieldName) {
        By locator;
        switch (serviceType) {
            case "Услуги связи":
                if(fieldName.equals("Номер телефона")){
                    locator = By.xpath("//div[@data-tab='mobile']//input[@name='pay_phone']");
                }else{
                    locator = By.xpath("//div[@data-tab='mobile']//input[@name='pay_amount']");
                }
                break;
            case "Домашний интернет":
                if(fieldName.equals("Номер телефона")){
                    locator = By.xpath("//div[@data-tab='home']//input[@name='pay_phone']");
                }else {
                    locator = By.xpath("//div[@data-tab='home']//input[@name='pay_contract']");
                }
                break;
            case "Рассрочка":
                if(fieldName.equals("Номер телефона")){
                    locator = By.xpath("//div[@data-tab='installment']//input[@name='pay_phone']");
                }else {
                    locator = By.xpath("//div[@data-tab='installment']//input[@name='pay_contract']");
                }
                break;
            case "Задолженность":
                if(fieldName.equals("Номер телефона")){
                    locator = By.xpath("//div[@data-tab='debt']//input[@name='pay_phone']");
                }else {
                    locator = By.xpath("//div[@data-tab='debt']//input[@name='pay_contract']");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid service type: " + serviceType);
        }

        WebElement field = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(field));
        return field.getAttribute("placeholder");
    }

}
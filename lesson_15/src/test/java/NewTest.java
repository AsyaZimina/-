import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {

	    private WebDriver driver;

	    @FindBy(xpath = "//h2[contains(text(),'Онлайн пополнение без комиссии')]")
	    private WebElement blockTitle;

	    @FindBy(xpath = "//img[contains(@src,'logo')]")
	    private List<WebElement> logos;

	    @FindBy(xpath = "//a[contains(text(),'подробнее о сервисе')]")
	    private WebElement moreInfoLink;

	    @FindBy(id = "serviceType")
	    private WebElement serviceTypeDropdown;

	    @FindBy(id = "phoneNumber")
	    private WebElement phoneNumberInput;

	    @FindBy(xpath = "//button[contains(text(),'Продолжить')]")
	    private WebElement continueButton;

	    @BeforeClass
	    public void setUp() {
	        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
	        driver = new ChromeDriver();
	        driver.get("https://www.mts.by");
	        PageFactory.initElements(driver, this);
	    }

	    @Test
	    public void testOnlineRechargeBlock() {
	        Assert.assertTrue(blockTitle.isDisplayed(), "Block title is not displayed");

	        for (WebElement logo : logos) {
	            Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
	        }

	        moreInfoLink.click();

	        serviceTypeDropdown.sendKeys("Услуги связи");
	        phoneNumberInput.sendKeys("297777777");
	        continueButton.click();

	       
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
}

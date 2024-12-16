package lesson_15;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class lesson_15 {

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

			public WebDriver getDriver() {
				return driver;
			}

			public void setDriver(WebDriver driver) {
				this.driver = driver;
			}
}

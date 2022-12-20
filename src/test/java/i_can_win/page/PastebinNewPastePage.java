package i_can_win.page;

import i_can_win.test.WebDriverPastebinTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PastebinNewPastePage {

    private final WebDriver driver;

    @FindBy(xpath = "//button[@class='sc-ifAKCX ljEJIv']")
    private WebElement acceptCookiesBtn;

    @FindBy(xpath = "//div/textarea[@id='postform-text']")
    private WebElement textInputField;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement expirationContainer;

    @FindBy(xpath = "//li[contains(@id, '10M')]")
    private WebElement expirationElement10M;

    @FindBy(id = "postform-name")
    private WebElement formNameInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public PastebinNewPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinNewPastePage openPage() {
        driver.get("https://pastebin.com");
        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn))
                .click();
        return this;
    }

    public PastebinResultingPage composeNewPaste(String pasteText) {
        textInputField.sendKeys(pasteText);
        expirationContainer.click();
        expirationElement10M.click();
        formNameInputField.sendKeys(WebDriverPastebinTest.expectedPageName);
        submitButton.submit();
        return new PastebinResultingPage(driver);
    }

}

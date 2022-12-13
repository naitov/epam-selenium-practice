package bring_it_on.page;

import bring_it_on.test.WebDrivePastebinCodeTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PastebinCodeNewPaste {

    public WebDriver driver;

    @FindBy(xpath = "//button[@class='sc-ifAKCX ljEJIv']")
    private WebElement acceptCookiesBtn;

    @FindBy(xpath = "//div/textarea[@id='postform-text']")
    private WebElement textInputField;

    @FindBy(xpath = "//*[@data-select2-id='postform-format']/parent::div")
    private WebElement syntaxHighlightingContainer;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    private WebElement syntaxHighlightingElementBash;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement expirationContainer;

    @FindBy(xpath = "//*[@id='select2-postform-expiration-results']/li[3]")
    private WebElement expirationElement10M;

    @FindBy(id = "postform-name")
    private WebElement formNameInputField;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement submitButton;

    public PastebinCodeNewPaste(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinCodeNewPaste openPage() {
        driver.get(WebDrivePastebinCodeTest.PAGE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        acceptCookiesBtn.click();
        return this;
    }

    public PastebinCodeNewPaste addPasteText(String[] pasteTextArray) {
        for (String line : pasteTextArray) {
            textInputField.sendKeys(line);
            textInputField.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public PastebinCodeNewPaste setSyntaxHighlightingToBash() {
        syntaxHighlightingContainer.click();
        syntaxHighlightingElementBash.sendKeys("bash");
        syntaxHighlightingElementBash.sendKeys(Keys.ENTER);
        return this;
    }

    public PastebinCodeNewPaste setExpirationPeriod10Minutes() {
        driver.manage().timeouts().implicitlyWait(Duration.of(3, ChronoUnit.SECONDS));
        expirationContainer.click();
        expirationElement10M.click();
        return this;
    }

    public PastebinCodeNewPaste addPasteName(String pasteName) {
        formNameInputField.sendKeys(pasteName);
        return this;
    }

    public PastebinCodeResultPage submitNewPaste() {
        submitButton.submit();
        return new PastebinCodeResultPage(driver);
    }
}

package bring_it_on.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PastebinCodeNewPastePage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='qc-cmp2-ui']//button[text()='AGREE']")
    private WebElement acceptCookiesBtn;

    @FindBy(xpath = "//div/textarea[@id='postform-text']")
    private WebElement textInputField;

    @FindBy(xpath = "//*[@data-select2-id='postform-format']/parent::div")
    private WebElement syntaxHighlightingContainer;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    private WebElement syntaxHighlightingElementBash;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement expirationContainer;

    @FindBy(xpath = "//li[contains(@id, '10M')]")
    private WebElement expirationElement10M;

    @FindBy(id = "postform-name")
    private WebElement formNameInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public PastebinCodeNewPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinCodeNewPastePage openPage() {
        driver.get("https://pastebin.com");
        acceptCookiesBtn.click();
        return this;
    }

    public PastebinCodeNewPastePage addPasteText(String pasteText) {
        String[] pasteTextArray = pasteText.split("\\n");
        for (String line : pasteTextArray) {
            textInputField.sendKeys(line);
            textInputField.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public PastebinCodeNewPastePage setSyntaxHighlightingToBash() {
        syntaxHighlightingContainer.click();
        syntaxHighlightingElementBash.sendKeys("bash");
        syntaxHighlightingElementBash.sendKeys(Keys.ENTER);
        return this;
    }

    public PastebinCodeNewPastePage setExpirationPeriod10Minutes() {
        expirationContainer.click();
        new WebDriverWait(driver, Duration.of(1, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(expirationElement10M))
                .click();
        return this;
    }

    public PastebinCodeNewPastePage addPasteName(String pasteName) {
        formNameInputField.sendKeys(pasteName);
        return this;
    }

    public PastebinCodeResultPage submitNewPaste() {
        submitButton.submit();
        return new PastebinCodeResultPage(driver);
    }
}

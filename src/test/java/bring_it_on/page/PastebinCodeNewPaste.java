package bring_it_on.page;

import bring_it_on.test.WebDrivePastebinCodeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinCodeNewPaste {

    public WebDriver driver;

    @FindBy(xpath = "//div/textarea[@id='postform-text']")
    private WebElement textInputField;

    @FindBy(xpath = "//span[@id='select2-postform-format-container']")
    private WebElement syntaxHighlightingContainer;

    @FindBy(xpath = "//*[text()='Bash']")
    private WebElement syntaxHighlightingElementBash;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement expirationContainer;

    @FindBy(xpath = "//*[text()='10 Minutes']")
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
        return this;
    }

    public PastebinCodeNewPaste addPasteText(String[] pasteTextArray) {
        for (String line : pasteTextArray) {
            textInputField.sendKeys(line);
        }
        return this;
    }

    public PastebinCodeNewPaste setSyntaxHighlightingToBash() {
        syntaxHighlightingContainer.submit();
        syntaxHighlightingElementBash.submit();
        return this;
    }

    public PastebinCodeNewPaste setExpirationPeriod10Minutes() {
        expirationContainer.submit();
        expirationElement10M.submit();
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

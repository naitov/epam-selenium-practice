package i_can_win.page;

import i_can_win.test.WebDriverPastebinTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinNewPaste {

    public WebDriver driver;

    @FindBy(xpath = "//div/textarea[@id='postform-text']")
    private WebElement textInputField;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement expirationContainer;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement expirationElement10M;

    @FindBy(id = "postform-name")
    private WebElement formNameInputField;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement submitButton;

    public PastebinNewPaste(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(WebDriverPastebinTest.PAGE_URL);
    }

    public PastebinResultPage composeNewPaste(String pasteValue, String nameValue) {
        textInputField.sendKeys(pasteValue);
        expirationContainer.click();
        expirationElement10M.click();
        formNameInputField.sendKeys(nameValue);
        submitButton.submit();
        return new PastebinResultPage(driver, nameValue);
    }
}

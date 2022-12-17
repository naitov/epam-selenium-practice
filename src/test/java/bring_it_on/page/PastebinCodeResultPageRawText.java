package bring_it_on.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinCodeResultPageRawText extends PastebinCodeResultPage {

    String rawTextUrl;

    public PastebinCodeResultPageRawText(WebDriver driver, String rawTextUrl) {
        super(driver);
        this.rawTextUrl = rawTextUrl;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//pre[@style='word-wrap: break-word; white-space: pre-wrap;']")
    WebElement rawText;

    public boolean expectThatResultingTextEquals(String pasteText) {
        driver.get(rawTextUrl);
        return pasteText.equals(rawText.getText());
    }
}

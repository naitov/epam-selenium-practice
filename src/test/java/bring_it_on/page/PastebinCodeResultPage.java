package bring_it_on.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PastebinCodeResultPage {

    public WebDriver driver;

    @FindBy(xpath = "//a[contains(@href, '/raw/')]")
    WebElement rawTextLink;

    public PastebinCodeResultPage(WebDriver driver) {
        this.driver = driver;

    }

    public boolean expectThatResultingPageTitleEquals(String pasteName) {
        String compositeXpath = "//*[text()='" + pasteName + " - Pastebin.com']";
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                .until(ExpectedConditions.textToBePresentInElementLocated(By
                        .xpath(compositeXpath), pasteName + " - Pastebin.com"));
    }

    public boolean expectThatSyntaxHighlightingIs(String syntaxToHighlight) {
        String compositeXpath = "//*[contains(@href,'light/" + syntaxToHighlight + ".css')]";
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                       .until(ExpectedConditions.presenceOfElementLocated(By.xpath(compositeXpath))) != null;
    }

    public boolean expectThatResultingTextEquals(String pasteText) {
        rawTextLink.click();
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//pre[@style='word-wrap: break-word; white-space: pre-wrap;']")))
                .getText().equals(pasteText);
    }
}
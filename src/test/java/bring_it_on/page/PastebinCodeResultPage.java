package bring_it_on.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PastebinCodeResultPage {

    public WebDriver driver;

    public PastebinCodeResultPage(WebDriver driver) {
        this.driver = driver;

    }

    public boolean expectThatResultingPageTitleContains(String pasteName) {
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                .until(ExpectedConditions.titleContains(pasteName));
    }

    public boolean expectThatSyntaxHighlightingIs(String syntaxToHighlight) {
        String compositeXpath = "//div[@class='highlighted-code']/div/div/a[text()='" + syntaxToHighlight + "']";
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                       .until(ExpectedConditions.presenceOfElementLocated(By.xpath(compositeXpath))) != null;
    }

    public PastebinCodeResultPageRawText getRawTextPageUrl() {
        String urlToMatch = driver.getCurrentUrl();
        Pattern pattern = Pattern.compile(".com/");
        Matcher matcher = pattern.matcher(urlToMatch);
        StringBuilder resultUrl = new StringBuilder();
        if (matcher.find()) {
            matcher.appendReplacement(resultUrl, ".com/raw/");
        }
        matcher.appendTail(resultUrl);
        return new PastebinCodeResultPageRawText(driver, resultUrl.toString());
    }
}
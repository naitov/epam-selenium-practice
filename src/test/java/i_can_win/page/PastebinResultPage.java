package i_can_win.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PastebinResultPage {

    public WebDriver driver;
    private final String nameValue;

    public PastebinResultPage(WebDriver driver, String nameValue) {
        this.driver = driver;
        this.nameValue = nameValue;
    }
    public boolean expectThatResultingPageContainsPasteText() {
        String xPathNameValue = "//div/h1[text()='" + nameValue + "']";
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathNameValue))) != null;
    }
}
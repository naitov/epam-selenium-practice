package i_can_win.page;

import i_can_win.test.WebDriverPastebinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PastebinResultingPage {

    private final WebDriver driver;

    public PastebinResultingPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getResultingPageName() {
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        String.format("//div/h1[text()='%s']", WebDriverPastebinTest.expectedPageName
                        )))).getText();
    }
}
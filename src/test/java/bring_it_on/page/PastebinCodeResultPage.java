package bring_it_on.page;

import bring_it_on.test.WebDrivePastebinCodeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PastebinCodeResultPage {
    private final WebDriver driver;

    @FindBy(xpath = "//textarea[@class='textarea -raw js-paste-raw']")
    WebElement rawTextArea;

    public PastebinCodeResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//div/h1[text()='%s']", WebDrivePastebinCodeTest.pasteName))))
                .getText();
    }

    public String getSyntaxHighlighting() {
        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='highlighted-code']//a")))
                .getText();
    }

    public String getRawText() {
        return rawTextArea.getText();
    }
}
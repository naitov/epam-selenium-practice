package hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HardcorePricingCalculatorPageResult extends HardcorePage {
    Double parsedSum;

    public HardcorePricingCalculatorPageResult(WebDriver driver) {
        super(driver);
    }

    public HardcorePricingCalculatorPageResult getEstimateSum() throws ParseException {

        String estimateSummaryString = new WebDriverWait(driver, Duration.of(3, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='compute']/descendant::b[contains(text(), 'Estimated Component Cost')]"))).getText();
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(estimateSummaryString);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSum = numberFormat.parse(matcher.group()).doubleValue();
        }
        return this;
    }

    public YopmailHomePage requestEmail() {
        String emailName = "biporewrveofjwp@yopmail.com";
        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='email']/parent::button"))).click();
        WebElement emailTextField = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
        emailTextField.sendKeys(emailName);
        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Send Email']"))).click();
        return new YopmailHomePage(driver, emailName, parsedSum);
    }
}
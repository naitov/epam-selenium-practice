package hardcore.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YopmailHomePage extends HardcorePage {

    String emailName;
    Double parsedSumFromGoogleCalculator;

    @FindBy(xpath = "//input[@class='ycptinput']")
    WebElement emailInputField;

    @FindBy(xpath = "//button[@id='refresh']")
    WebElement refreshButton;

    @FindBy(xpath = "//div[@id='nbmail']")
    WebElement mailCounterLabel;

    @FindBy(xpath = "//*[contains(text(), 'Estimated Monthly Cost:')]")
    WebElement estimatedMonthlyCostField;

    public YopmailHomePage(WebDriver driver, String emailName, Double parsedSum) {
        super(driver);
        this.emailName = emailName;
        parsedSumFromGoogleCalculator = parsedSum;
    }

    public YopmailHomePage openEmailPageInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://yopmail.com/ru/");
        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='accept']"))).click();
        return this;
    }

    public YopmailHomePage createNewMailBox() {
        emailInputField.sendKeys(emailName);
        emailInputField.sendKeys(Keys.ENTER);
        return this;
    }

    public YopmailHomePage waitForMail() throws InterruptedException {
        while (mailCounterLabel.getText().equals("0 mail")) {
            refreshButton.click();
            Thread.sleep(2000);
        }
        return this;
    }

    public boolean expectThatSumInEstimateAndEmailAreEqual() throws ParseException {
        WebElement mailFrame = new WebDriverWait(driver, Duration.of(500, ChronoUnit.MILLIS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='ifmail']")));
        driver.switchTo().frame(mailFrame);
        String emailCost = estimatedMonthlyCostField.getText();
        Double parsedSumFromEmail = 0d;
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(emailCost);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSumFromEmail = numberFormat.parse(matcher.group()).doubleValue();
        }
        return parsedSumFromEmail.equals(parsedSumFromGoogleCalculator);
    }
}

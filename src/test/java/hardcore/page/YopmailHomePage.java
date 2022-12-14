package hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
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

public class YopmailHomePage extends AbstractHardcorePage {
    private String randomEmailName;
    private final String estimateWindowHandle;
    private String yopmailWindowHandle;

    @FindBy(xpath = "//button[@id='refresh']")
    WebElement refreshButton;

    @FindBy(xpath = "//div[@id='nbmail']")
    WebElement mailCounterLabel;

    @FindBy(xpath = "//*[contains(text(), 'Estimated Monthly Cost:')]")
    WebElement estimatedMonthlyCostField;


    public YopmailHomePage(WebDriver driver, String estimateWindowHandle) {
        super(driver);
        this.estimateWindowHandle = estimateWindowHandle;

    }

    public YopmailHomePage openEmailPageInNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://yopmail.com/ru/");
        yopmailWindowHandle = driver.getWindowHandle();
        new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='accept']"))).click();
        return this;
    }

    public YopmailHomePage createNewMailBoxWithRandomName() {
        createWaitWithClickableCondition(WaitTimeouts.TEN_SEC, "//a[@href='email-generator']").click();
        randomEmailName = String.format("%s@yopmail.com", createWaitWithPresenceCondition(WaitTimeouts.TEN_SEC, "//span[@class='genytxt']").getText());
        createWaitWithClickableCondition(WaitTimeouts.TEN_SEC, "//button[@onclick='egengo();']").click();
        return this;
    }

    public void switchToEstimatePage() {
        driver.switchTo().window(estimateWindowHandle);
    }

    public YopmailHomePage waitForMail() throws InterruptedException {
        while (mailCounterLabel.getText().equals("0 mail")) {
            refreshButton.click();
            Thread.sleep(2000);
        }
        return this;
    }

    public double getActualSum() throws ParseException {
        WebElement mailFrame = new WebDriverWait(driver, Duration.of(500, ChronoUnit.MILLIS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='ifmail']")));
        driver.switchTo().frame(mailFrame);
        String emailCost = estimatedMonthlyCostField.getText();
        double parsedSumFromEmail = 0d;
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(emailCost);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSumFromEmail = numberFormat.parse(matcher.group()).doubleValue();
        }
        return parsedSumFromEmail;
    }

    public String getRandomEmailName() {
        return randomEmailName;
    }

    public String getYopmailWindowHandle() {
        return yopmailWindowHandle;
    }
}

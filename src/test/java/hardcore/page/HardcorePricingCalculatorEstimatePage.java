package hardcore.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HardcorePricingCalculatorEstimatePage extends AbstractHardcorePage {
    private double actualSum = -1.0;
    private YopmailHomePage yopmailPage;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    WebElement iFrameElement;

    public HardcorePricingCalculatorEstimatePage(WebDriver driver) {
        super(driver);
    }

    public double getEstimateSum() throws ParseException {
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
        String estimateSummaryString = createNewPresenceElement(3, "//*[@id='compute']/descendant::b[contains(text(), 'Estimated Component Cost')]")
                .getText();
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(estimateSummaryString);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            actualSum = numberFormat.parse(matcher.group()).doubleValue();
        }
        return actualSum;
    }

    public YopmailHomePage createYopmailPage() {
        String currentWindowHandle = driver.getWindowHandle();
        yopmailPage = new YopmailHomePage(driver, currentWindowHandle);
        return yopmailPage;
    }

    public HardcorePricingCalculatorEstimatePage sendEmailFromPage() {
        String emailName = yopmailPage.getRandomEmailName();
//        driver.switchTo().frame(0);
//        driver.switchTo().frame(iFrameElement);
        createNewClickableElement(10, "//span[text()='email']/parent::button").click();
        createNewPresenceElement(10, "//input[@type='email']").sendKeys(emailName);
        createNewPresenceElement(10, "//button[@aria-label='Send Email']").click();
        return this;
    }

    public void switchToYopmail() {
        driver.switchTo().window(yopmailPage.getYopmailWindowHandle());
    }
}
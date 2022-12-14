package hurt_me_plenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GooglePricingCalculatorPageResult extends Page {

    public GooglePricingCalculatorPageResult(WebDriver driver) {
        super(driver);
    }


    public boolean expectThatResultingFieldsEqualTo(Map<String, Integer> fields) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='compute']/md-list/md-list-item/div"));
        for (WebElement element : elements) {
            if (fields.containsKey(element.getText())) {
                fields.replace(element.getText().trim(), 0);
            }
        }
        System.out.println(fields.toString());
        return fields.values().stream().mapToInt(Integer::intValue).sum() == 0;
    }

    public boolean expectThatSumInEstimateBlockEqualsTo(Double doubleValue) throws ParseException {
        Double parsedSum = 0d;
        String estimateSummaryString = new WebDriverWait(driver, Duration.of(3, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='compute']/descendant::b[contains(text(), 'Estimated Component Cost')]"))).getText();
        Pattern pattern = Pattern.compile("([0-9,.]{2,20})");
        Matcher matcher = pattern.matcher(estimateSummaryString);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSum = numberFormat.parse(matcher.group()).doubleValue();
        }
        return parsedSum.equals(doubleValue);
    }
}
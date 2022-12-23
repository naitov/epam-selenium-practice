package hurt_me_plenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GooglePricingCalculatorEstimatePage extends AbstractPage {

    public GooglePricingCalculatorEstimatePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getActualTextFromField() {
        List<String> tempElements = driver.findElements(By.xpath("//md-card-content[@id='resultBlock']//div[contains(@class,'list-item')]"))
                .stream().map(WebElement::getText).toList();
        List<String> webElementTextList = new ArrayList<>();
        for (String row : tempElements) {
            Matcher matcher = Pattern.compile("\\n").matcher(row);
            if (matcher.find()) {
                webElementTextList.add(row.substring(0, matcher.start()));
                webElementTextList.add(row.substring(matcher.end()));
            } else {
                webElementTextList.add(row);
            }
        }
        return webElementTextList;
    }

    public double getActualSumFromField() throws ParseException {
        double parsedSum = 0d;
        String elementText = createNewPresenceElement(3, "//*[@id='compute']/descendant::b[contains(text(), 'Estimated Component Cost')]")
                .getText();
        Matcher matcher = Pattern.compile("([0-9,.]{2,20})").matcher(elementText);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSum = numberFormat.parse(matcher.group()).doubleValue();
        }
        return parsedSum;
    }
}
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
            String[] temp = row.split("\\n");
            webElementTextList.addAll(List.of(temp));
        }
        return webElementTextList;
    }

    public double getActualSumFromField() throws ParseException {
        double parsedSum = 0d;
        String elementText = getElementWithPresenceWait(WaitTimeouts.THREE_SEC, "//*[@id='compute']/descendant::b[contains(text(), 'Estimated Component Cost')]")
                .getText();
        Matcher matcher = Pattern.compile("([0-9,.]{2,20})").matcher(elementText);
        if (matcher.find()) {
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            parsedSum = numberFormat.parse(matcher.group()).doubleValue();
        }
        return parsedSum;
    }
}
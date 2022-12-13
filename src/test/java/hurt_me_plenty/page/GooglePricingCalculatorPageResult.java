package hurt_me_plenty.page;

import org.openqa.selenium.WebDriver;

public class GooglePricingCalculatorPageResult extends Page{

    public GooglePricingCalculatorPageResult(WebDriver driver) {
        super(driver);
    }


//    public boolean expectThatResultingPageTitleEquals(String pasteName) {
//        String compositeXpath = "//*[text()='" + pasteName + " - Pastebin.com']";
//        return new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS))
//                .until(ExpectedConditions.textToBePresentInElementLocated(By
//                        .xpath(compositeXpath), pasteName + " - Pastebin.com"));
//    }


    public boolean expectThatEstimateBlockVMClassEqualsNewEstimateVMClass() {
        return false;
    }
}
package hardcore.page;

import hurt_me_plenty.exceptions.NoSuchResultException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HardcoreCloudHomePage extends HardcorePage {


    @FindBy(name = "q")
    private WebElement searchField;

    public HardcoreCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public HardcoreCloudHomePage openPage(String url) {
        driver.get(url);
        return this;
    }

    public HardcorePricingCalculatorPageForm searchForTerm(String searchTerm) throws NoSuchResultException {
        searchField.sendKeys(searchTerm);
        searchField.submit();
        WebElement searchResultLink = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//b[text()='Google Cloud Pricing Calculator']/parent::a")));
        WebElement SearchResultText = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[text()='Google Cloud Pricing Calculator']/parent::a/child::b")));

        if (SearchResultText.getText().equals(searchTerm)) {
            return new HardcorePricingCalculatorPageForm(driver, searchResultLink.getAttribute("href"));
        } else {
            throw new NoSuchResultException("Search term is missing in search results");
        }
    }
}

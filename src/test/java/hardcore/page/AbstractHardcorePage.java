package hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class AbstractHardcorePage {
    protected final WebDriver driver;

    public AbstractHardcorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebElement createNewPresenceElement(int durationSec, String xpath) {
        return new WebDriverWait(driver, Duration.of(durationSec, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    WebElement createNewClickableElement(int durationSec, String xpath) {
        return new WebDriverWait(driver, Duration.of(durationSec, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }
}

package hardcore.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class HardcorePage {
    protected WebDriver driver;

    public HardcorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HardcorePage openPage(String url) {
        driver.get(url);
        return this;
    }

    public HardcorePage openPage() {
        return this;
    }
}

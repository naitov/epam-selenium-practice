package hurt_me_plenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Page openPage(String url) {
        driver.get(url);
        return this;
    }

    public Page openPage() {
        return this;
    }
}

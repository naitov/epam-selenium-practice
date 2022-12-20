package i_can_win.test;

import i_can_win.page.PastebinNewPastePage;
import i_can_win.page.PastebinResultingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class WebDriverPastebinTest {
    public static final String expectedPageName = "helloweb";
    public static final String pasteText = "Hello from WebDriver";
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Resulting page name should be equal expected page name")
    public void ActualAndResultingNamesShouldBeEqual() {
        PastebinResultingPage resultingPage = new PastebinNewPastePage(driver)
                .openPage()
                .composeNewPaste(pasteText);
        String actualPageName = resultingPage.getResultingPageName();

        assertThat("Resulting page name should be equal expected page name", actualPageName, containsString(expectedPageName));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}

package i_can_win.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import i_can_win.page.PastebinNewPaste;

public class WebDriverPastebinTest {
    public WebDriver driver;
    public static String PAGE_URL = "https://pastebin.com";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Need to add some text here")
    public void assertResultingPageExistence() {
        PastebinNewPaste newPaste = new PastebinNewPaste(driver);
        newPaste.openPage();
        Assert.assertTrue(newPaste.composeNewPaste("Hello from WebDriver", "helloweb")
                .expectThatResultingPageContainsPasteText(), "Failed to create new paste");
    }

    @AfterMethod(alwaysRun = true)
    public void browsertearDown() {
        driver.quit();
        driver = null;
    }
}

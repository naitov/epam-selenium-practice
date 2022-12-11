package bring_it_on.test;

import bring_it_on.page.PastebinCodeNewPaste;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WebDrivePastebinCodeTest {
    public WebDriver driver;
    public static final String PAGE_URL = "https://pastebin.com";
    private final String pasteText = "git config --global user.name  \"New Sheriff in Town\"\n" +
                                     "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                                     "git push origin master --force";
    private final String pasteName = "how to gain dominance among developers";


    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
    }

    @Test(description = "Asserting equality new paste name to resulting page title")
    public void TestResultingPageTitleEqualsPasteName() {
        Assert.assertTrue(new PastebinCodeNewPaste(driver)
                .openPage()
                .addPasteText(splitPaste(pasteText))
                .setSyntaxHighlightingToBash()
                .setExpirationPeriod10Minutes()
                .addPasteName(pasteName)
                .submitNewPaste()
                .expectThatResultingPageTitleEquals(pasteName));
    }

    @Test(description = "Asserting syntax highlighting to Bash")
    public void TestResultingPageSyntaxHighlightingIsBash() {
        String syntaxToHighlight = "Bash";
        Assert.assertTrue(new PastebinCodeNewPaste(driver)
                .openPage()
                .addPasteText(splitPaste(pasteText))
                .setSyntaxHighlightingToBash()
                .setExpirationPeriod10Minutes()
                .addPasteName(pasteName)
                .submitNewPaste()
                .expectThatSyntaxHighlightingIs(syntaxToHighlight));
    }

    @Test(description = "Asserting equality new paste text to resulting page paste text")
    public void TestResultingPageTextEqualsPasteText() {
        Assert.assertTrue(new PastebinCodeNewPaste(driver)
                .openPage()
                .addPasteText(splitPaste(pasteText))
                .setSyntaxHighlightingToBash()
                .setExpirationPeriod10Minutes()
                .addPasteName(pasteName)
                .submitNewPaste()
                .expectThatResultingTextEquals(pasteText));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

    public String[] splitPaste(String paste) {
        return paste.split("\\n");
    }
}

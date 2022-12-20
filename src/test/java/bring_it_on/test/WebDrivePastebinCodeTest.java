package bring_it_on.test;

import bring_it_on.page.PastebinCodeNewPastePage;
import bring_it_on.page.PastebinCodeResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class WebDrivePastebinCodeTest {
    public static final String pasteName = "how to gain dominance among developers";
    private static final String pasteText = """
            git config --global user.name  "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force""";
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Resulting page title should contain Paste Name")
    public void ResultingPageTitleShouldContainPasteName() {
        PastebinCodeNewPastePage newPaste = new PastebinCodeNewPastePage(driver)
                .openPage()
                .addPasteText(pasteText)
                .setSyntaxHighlightingToBash()
                .setExpirationPeriod10Minutes()
                .addPasteName(pasteName);
        PastebinCodeResultPage resultPage = newPaste.submitNewPaste();
        String resultingPageTitle = resultPage.getTitle();
        assertThat("Resulting page title should contain Paste Name", resultingPageTitle, containsString(pasteName));
    }

    @Test(description = "Syntax highlighting in the resulting page should be Bash")
    public void ResultingPageSyntaxHighlightingShouldBeBash() {
        PastebinCodeNewPastePage newPaste = new PastebinCodeNewPastePage(driver)
                .openPage()
                .addPasteText(pasteText)
                .setSyntaxHighlightingToBash()
                .setExpirationPeriod10Minutes()
                .addPasteName(pasteName);
        PastebinCodeResultPage resultPage = newPaste.submitNewPaste();
        String resultingSyntax = resultPage.getSyntaxHighlighting();
        assertThat("Syntax highlighting in the resulting page should be Bash", resultingSyntax, containsString("Bash"));
    }

    @Test(description = "Resulting and expected paste texts should be equal")
    public void ResultingTextShouldBeEqualExpectedText() {
        PastebinCodeNewPastePage newPaste = new PastebinCodeNewPastePage(driver)
                .openPage()
                .addPasteText(pasteText)
                .setSyntaxHighlightingToBash()
                .setExpirationPeriod10Minutes()
                .addPasteName(pasteName);
        PastebinCodeResultPage resultPage = newPaste.submitNewPaste();
        String resultingText = resultPage.getRawText();
        assertThat("Resulting and expected paste texts should be equal", resultingText.equals(pasteText));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}

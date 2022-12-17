package hurt_me_plenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class GooglePricingCalculatorPageForm extends Page {

    String searchResultUrl;

    @FindBy(xpath = "//button[@class='devsite-snackbar-action']")
    private WebElement cookieOkButton;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    WebElement iFrameElement;

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement numberOfInstancesInputField;

    @FindBy(xpath = "//*[text()='Operating System / Software']/parent::*")
    private WebElement operationSystemList;

    @FindBy(xpath = "//div[@class='md-text' and contains(text(), 'Free')]/parent::md-option")
    private WebElement operationSystemElement;

    @FindBy(xpath = "//*[text()='Provisioning model']/parent::*")
    private WebElement provisioningModelList;

    @FindBy(xpath = "//div[@class='md-text'][contains(text(), 'Regular')]/parent::md-option")
    private WebElement provisioningModelElement;

    @FindBy(xpath = "//*[text()='Series']/parent::*")
    private WebElement seriesList;

    @FindBy(xpath = "//*[text()='Machine type']/parent::*")
    private WebElement machineTypeList;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']/descendant::md-input-container/md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(xpath = "//*[text()='GPU type']/parent::*")
    private WebElement gpuTypeList;

    @FindBy(xpath = "//*[text()='Number of GPUs']/parent::*")
    private WebElement numberOfGpusList;

    @FindBy(xpath = "//*[text()='Local SSD']/parent::*")
    private WebElement localSsdList;

    @FindBy(xpath = "//*[text()='Datacenter location']/parent::*")
    private WebElement datacenterLocationList;

    @FindBy(xpath = "//*[text()='Committed usage']/parent::*")
    private WebElement committedUsageList;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    public GooglePricingCalculatorPageForm(WebDriver driver, String searchResultUrl) {
        super(driver);
        this.searchResultUrl = searchResultUrl;
    }

    @Override
    public GooglePricingCalculatorPageForm openPage() {
        driver.get(searchResultUrl);
        cookieOkButton.click();
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
        return this;
    }

    public GooglePricingCalculatorPageForm fillAllFieldsAccordingToTerms() {
        this.setNumberOfInstances()
                .selectOperationgSystem()
                .selectProvisioningModel()
                .selectSeries()
                .selectMachineType()
                .activateCheckboxAddGPU()
                .selectGPUType()
                .selectNumberOfGPUs()
                .selectLocalSsd()
                .selectDataCenterLocation()
                .selectCommittedUsage();
        return this;
    }

    public GooglePricingCalculatorPageForm setNumberOfInstances() {
        numberOfInstancesInputField.sendKeys("4");
        return this;
    }

    public GooglePricingCalculatorPageForm selectOperationgSystem() {
        operationSystemList.click();
        operationSystemElement.click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectProvisioningModel() {
        provisioningModelList.click();
        provisioningModelElement.click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectSeries() {
        seriesList.click();
        new WebDriverWait(driver, Duration.of(2, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='md-text ng-binding'][contains(text(), 'N1')]/parent::md-option"))).click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectMachineType() {
        machineTypeList.click();
        new WebDriverWait(driver, Duration.of(3, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='md-text ng-binding'][contains(text(), 'n1-standard-8 (vCPUs: 8, RAM: 30GB)')]/parent::md-option"))).click();
        return this;
    }

    public GooglePricingCalculatorPageForm activateCheckboxAddGPU() {
        addGpuCheckbox.click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectGPUType() {
        gpuTypeList.click();
        new WebDriverWait(driver, Duration.of(1, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='md-text ng-binding'][contains(text(), 'Tesla P4')]/parent::md-option"))).click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectNumberOfGPUs() {
        numberOfGpusList.click();
        new WebDriverWait(driver, Duration.of(1, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), '1')]/parent::*[@id='select_option_477']"))).click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectLocalSsd() {
        localSsdList.click();
        new WebDriverWait(driver, Duration.of(1, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='md-text ng-binding'][contains(text(), '2x375 GB')]/parent::md-option"))).click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectDataCenterLocation() {
        datacenterLocationList.click();
        new WebDriverWait(driver, Duration.of(1, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='md-text ng-binding'][contains(text(), 'Frankfurt')]/parent::*[@id='select_option_228']"))).click();
        return this;
    }

    public GooglePricingCalculatorPageForm selectCommittedUsage() {
        committedUsageList.click();
        new WebDriverWait(driver, Duration.of(1, ChronoUnit.SECONDS))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='md-text'][contains(text(), '1 Year')]/parent::*[@id='select_option_128']"))).click();
        return this;
    }

    public GooglePricingCalculatorPageResult addToEstimate() {
        addToEstimateButton.submit();
        return new GooglePricingCalculatorPageResult(driver);
    }


}

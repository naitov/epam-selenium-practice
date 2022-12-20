package hurt_me_plenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GooglePricingCalculatorFormPage extends AbstractPage {
    String searchResultUrl;
    @FindBy(xpath = "//iframe[@id='myFrame']")
    WebElement iFrameElement;
    @FindBy(xpath = "//button[@class='devsite-snackbar-action']")
    private WebElement cookieOkButton;
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

    public GooglePricingCalculatorFormPage(WebDriver driver, String searchResultUrl) {
        super(driver);
        this.searchResultUrl = searchResultUrl;
    }

    public GooglePricingCalculatorFormPage openFormPage() {
        driver.get(searchResultUrl);
        cookieOkButton.click();
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
        return this;
    }

    public GooglePricingCalculatorFormPage fillAllNecessaryFields() {
        this.setNumberOfInstances()
                .selectOperationSystem()
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

    private GooglePricingCalculatorFormPage setNumberOfInstances() {
        numberOfInstancesInputField.sendKeys("4");
        return this;
    }

    private GooglePricingCalculatorFormPage selectOperationSystem() {
        operationSystemList.click();
        operationSystemElement.click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectProvisioningModel() {
        provisioningModelList.click();
        provisioningModelElement.click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectSeries() {
        seriesList.click();
        createNewClickableElement(1, "//div[@class='md-text ng-binding'][contains(text(), 'N1')]/parent::md-option")
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectMachineType() {
        machineTypeList.click();
        createNewClickableElement(3, "//div[@class='md-text ng-binding'][contains(text(), 'n1-standard-8 (vCPUs: 8, RAM: 30GB)')]/parent::md-option")
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage activateCheckboxAddGPU() {
        addGpuCheckbox.click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectGPUType() {
        gpuTypeList.click();
        createNewClickableElement(2, "//div[@class='md-text ng-binding'][contains(text(), 'Tesla P4')]/parent::md-option")
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectNumberOfGPUs() {
        numberOfGpusList.click();
        createNewClickableElement(1, "//*[contains(text(), '1')]/parent::*[@id='select_option_477']")
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectLocalSsd() {
        localSsdList.click();
        createNewClickableElement(1, "//div[@class='md-text ng-binding'][contains(text(), '2x375 GB')]/parent::md-option")
                .click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectDataCenterLocation() {
        datacenterLocationList.click();
        createNewClickableElement(1, "//div[@class='md-text ng-binding'][contains(text(), 'Frankfurt')]/parent::*[@id='select_option_228']")
                .click();
        return this;
    }

    private void selectCommittedUsage() {
        committedUsageList.click();
        createNewClickableElement(1, "//div[@class='md-text'][contains(text(), '1 Year')]/parent::*[@id='select_option_128']")
                .click();
    }

    public GooglePricingCalculatorEstimatePage addToEstimate() {
        addToEstimateButton.submit();
        return new GooglePricingCalculatorEstimatePage(driver);
    }

}

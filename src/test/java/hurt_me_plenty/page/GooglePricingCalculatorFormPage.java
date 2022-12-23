package hurt_me_plenty.page;

import hurt_me_plenty.form.GoogleCalculatorForm;
import hurt_me_plenty.service.GoogleFormCreator;
import hurt_me_plenty.test.GooglePricingCalculatorTest.FormPresets;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePricingCalculatorFormPage extends AbstractPage {
    private GoogleCalculatorForm form;
    private FormPresets currentPreset;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement iFrameElement;

    @FindBy(xpath = "//button[@class='devsite-snackbar-action']")
    private WebElement cookieOkButton;

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement numberOfInstancesInputField;

    @FindBy(xpath = "//label[@for='select_103']/..")
    private WebElement operationSystemList;

    @FindBy(xpath = "//label[@for='select_107']/..")
    private WebElement provisioningModelList;

    @FindBy(xpath = "//label[@for='select_115']/..")
    private WebElement seriesList;

    @FindBy(xpath = "//label[@for='select_117']/..")
    private WebElement machineTypeList;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(xpath = "//label[@for='select_466']/..")
    private WebElement gpuTypeList;

    @FindBy(xpath = "//label[@for='select_468']/..")
    private WebElement numberOfGpusList;

    @FindBy(xpath = "//label[@for='select_423']/..")
    private WebElement localSsdList;

    @FindBy(xpath = "//label[@for='select_123']/..")
    private WebElement datacenterLocationList;

    @FindBy(xpath = "//label[@for='select_130']/..")
    private WebElement committedUsageList;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    public GooglePricingCalculatorFormPage(WebDriver driver) {
        super(driver);
    }

    public GooglePricingCalculatorFormPage setupFormPage() {
        cookieOkButton.click();
        driver.switchTo().frame(0);
        driver.switchTo().frame(iFrameElement);
        return this;
    }

    public GooglePricingCalculatorFormPage initializeCalculatorForm(FormPresets preset) {
        currentPreset = preset;
        switch (preset) {
            case PRESET_LIGHT -> form = GoogleFormCreator.withOnlyNumberOfInstances();
            case PRESET_FULL -> form = GoogleFormCreator.withAllElements();
            case PRESET_WITHOUT_GPU -> form = GoogleFormCreator.withAllelementsExcludeGpu();
        }
        return this;
    }

    public GooglePricingCalculatorFormPage fillAllNecessaryFields() {
        switch (currentPreset) {
            case PRESET_LIGHT -> {
                this.setNumberOfInstances();
                return this;
            }
            case PRESET_FULL -> {
                this.setNumberOfInstances()
                        .selectOperatingSystem()
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
            case PRESET_WITHOUT_GPU -> {
                this.setNumberOfInstances()
                        .selectOperatingSystem()
                        .selectProvisioningModel()
                        .selectSeries()
                        .selectMachineType()
                        .selectLocalSsd()
                        .selectDataCenterLocation()
                        .selectCommittedUsage();
                return this;
            }
            default -> {
                return this;
            }
        }
    }

    private GooglePricingCalculatorFormPage setNumberOfInstances() {
        numberOfInstancesInputField.sendKeys(String.valueOf(form.getNumberOfInstances()));
        return this;
    }

    private GooglePricingCalculatorFormPage selectOperatingSystem() {
        operationSystemList.click();
        driver.findElement(By.xpath(form.getOperatingSystemXpath())).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectProvisioningModel() {
        provisioningModelList.click();
        driver.findElement(By.xpath(form.getProvisioningModelXpath())).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectSeries() {
        seriesList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, form.getSeriesXpath()).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectMachineType() {
        machineTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC, form.getMachineTypeXpath()).click();
        return this;
    }

    private GooglePricingCalculatorFormPage activateCheckboxAddGPU() {
        addGpuCheckbox.click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectGPUType() {
        gpuTypeList.click();
        getElementWithClickableWait(WaitTimeouts.THREE_SEC, form.getGpuTypeXpath()).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectNumberOfGPUs() {
        numberOfGpusList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, form.getNumberOfGpuXpath()).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectLocalSsd() {
        localSsdList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, form.getLocalSsdXpath()).click();
        return this;
    }

    private GooglePricingCalculatorFormPage selectDataCenterLocation() {
        datacenterLocationList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, form.getDatacenterLocationXpath()).click();
        return this;
    }

    private void selectCommittedUsage() {
        committedUsageList.click();
        getElementWithClickableWait(WaitTimeouts.ONE_SEC, form.getCommittedUsageXpath()).click();
    }

    public GooglePricingCalculatorEstimatePage addToEstimate() {
        addToEstimateButton.submit();
        return new GooglePricingCalculatorEstimatePage(driver);
    }
}

package hurt_me_plenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePricingCalculatorPageForm extends Page {

    String url;

    @FindBy(id = "input_90")
    private WebElement numberOfInstancesInputField;

    @FindBy(id = "select_103")
    private WebElement operationSystemList;

    @FindBy(id = "select_value_label_82")
    private  WebElement operationSystemElement;

    @FindBy
    private WebElement provisioningModelList;

    @FindBy
    private WebElement provisioningModelElement;

    private WebElement seriesList;

    private WebElement seriesElement;

    private WebElement machineTypeList;

    private WebElement machineTypeElement;

    private WebElement addGpuCheckbox;

    private WebElement gpuTypeList;

    private WebElement gpuTypeElement;

    private WebElement numberOfGpusList;

    private WebElement numberOfGpusElement;

    private WebElement localSsdList;

    private WebElement localSsdElement;

    private WebElement dataCenterLocationList;

    private WebElement dataCenterLocationElement;

    private WebElement committedUsageList;

    private WebElement committedUsageElement;

    public GooglePricingCalculatorPageForm(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    @Override
    public GooglePricingCalculatorPageForm openPage() {
        driver.get(url);
        return this;
    }

    public GooglePricingCalculatorPageForm fillFormAccordingToTerms() {
        this    .setNumberOfInstances()
                .selectOperationgSystem()
                .selectProvisioningModel()
                .selectSeries()
                .selectMachineType()
                .activateCheckboxAddGPU()
                .selectGPUType()
                .selectNumberOfGPUs()
                .selectLocalSSD()
                .selectDataCenterLocation()
                .selectCommitedUsage();
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
        seriesElement.click();
        return null;
    }

    public GooglePricingCalculatorPageForm selectMachineType() {
        return null;
    }

    public GooglePricingCalculatorPageForm activateCheckboxAddGPU() {
        return null;
    }

    public GooglePricingCalculatorPageForm selectGPUType() {
        return null;
    }

    public GooglePricingCalculatorPageForm selectNumberOfGPUs() {
        return null;
    }

    public GooglePricingCalculatorPageForm selectLocalSSD() {
        return null;
    }

    public GooglePricingCalculatorPageForm selectDataCenterLocation() {
        return null;
    }

    public GooglePricingCalculatorPageForm selectCommitedUsage() {
        return null;
    }

    public GooglePricingCalculatorPageResult addToEstimate() {

        return null;
    }


}

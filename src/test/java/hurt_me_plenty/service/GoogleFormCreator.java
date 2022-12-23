package hurt_me_plenty.service;

import hurt_me_plenty.form.GoogleCalculatorForm;

import static hurt_me_plenty.service.FormXpathStorage.CommittedUsages.ONE_YEAR;
import static hurt_me_plenty.service.FormXpathStorage.CommittedUsages.THREE_YEARS;
import static hurt_me_plenty.service.FormXpathStorage.DatacenterLocations.FRANKFURT;
import static hurt_me_plenty.service.FormXpathStorage.DatacenterLocations.WARSAW;
import static hurt_me_plenty.service.FormXpathStorage.GpuNumbers.ONE_GPU;
import static hurt_me_plenty.service.FormXpathStorage.GpuTypes.NVIDIA_P4;
import static hurt_me_plenty.service.FormXpathStorage.Instances.FOUR;
import static hurt_me_plenty.service.FormXpathStorage.Instances.ONE;
import static hurt_me_plenty.service.FormXpathStorage.MachineTypes.E2_STANDART_2;
import static hurt_me_plenty.service.FormXpathStorage.MachineTypes.N1_STANDART_8;
import static hurt_me_plenty.service.FormXpathStorage.OperatingSystems.FREE;
import static hurt_me_plenty.service.FormXpathStorage.ProvisioningModels.REGULAR;
import static hurt_me_plenty.service.FormXpathStorage.Series.E2;
import static hurt_me_plenty.service.FormXpathStorage.Series.N1;
import static hurt_me_plenty.service.FormXpathStorage.SsdNumbers.ONE_SSD;
import static hurt_me_plenty.service.FormXpathStorage.SsdNumbers.TWO_SSD;

public class GoogleFormCreator {
    public static GoogleCalculatorForm withOnlyNumberOfInstances() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(ONE.value);
        return form;
    }

    public static GoogleCalculatorForm withAllElements() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(FOUR.value);
        form.setOperatingSystemXpath(FREE.value);
        form.setProvisioningModelXpath(REGULAR.value);
        form.setSeriesXpath(N1.value);
        form.setMachineTypeXpath(N1_STANDART_8.value);
        form.setGpuTypeXpath(NVIDIA_P4.value);
        form.setNumberOfGpuXpath(ONE_GPU.value);
        form.setLocalSsdXpath(TWO_SSD.value);
        form.setDatacenterLocationXpath(FRANKFURT.value);
        form.setCommittedUsageXpath(ONE_YEAR.value);
        return form;
    }

    public static GoogleCalculatorForm withAllelementsExcludeGpu() {
        GoogleCalculatorForm form = new GoogleCalculatorForm();
        form.setNumberOfInstances(FOUR.value);
        form.setOperatingSystemXpath(FREE.value);
        form.setProvisioningModelXpath(REGULAR.value);
        form.setSeriesXpath(E2.value);
        form.setMachineTypeXpath(E2_STANDART_2.value);
        form.setLocalSsdXpath(ONE_SSD.value);
        form.setDatacenterLocationXpath(WARSAW.value);
        form.setCommittedUsageXpath(THREE_YEARS.value);
        return form;
    }
}

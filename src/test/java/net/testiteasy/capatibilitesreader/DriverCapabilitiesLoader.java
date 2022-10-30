package net.testiteasy.capatibilitesreader;

import net.testiteasy.annotations.Step;
import net.testiteasy.utils.properties.PropertiesController;
import net.testiteasy.utils.variables.DeviceName;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverCapabilitiesLoader {

    private final PropertiesController propertiesController = new PropertiesController();

    @Step("Read DesiredCapabilities for the device {deviceName}")
    public DesiredCapabilities getCapabilities(@NotNull DeviceName deviceName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        propertiesController.loadProperties(deviceName.getCapabilities());
        propertiesController.getAllProperty().forEach(capabilities::setCapability);

        return capabilities;
    }
}

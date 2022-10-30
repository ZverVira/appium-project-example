package net.testiteasy.utils.parameters;

import net.testiteasy.utils.properties.PropertiesController;
import net.testiteasy.utils.variables.DeviceName;
import net.testiteasy.utils.variables.OSType;
import net.testiteasy.utils.variables.RunningPlatform;
import net.testiteasy.utils.variables.EnvironmentType;

import static org.apache.commons.lang3.StringUtils.upperCase;

@SuppressWarnings("unused")
public class TestDataParams {

    private static volatile TestDataParams instance;

    private final OSType osType;
    private final DeviceName deviceName;
    private final RunningPlatform runningPlatform;
    private final EnvironmentType envType;

    private TestDataParams() {
        PropertiesController testData = PropertiesController.getPropertiesController();
        osType = OSType.valueOf(upperCase(testData.getProperty("device.platform")));
        deviceName = DeviceName.valueOf(upperCase(testData.getProperty("device.name")));
        runningPlatform = RunningPlatform.valueOf(upperCase(testData.getProperty("running.platform")));
        envType = EnvironmentType.valueOf(upperCase(testData.getProperty("env.type")));
    }

    public static TestDataParams testConfig() {
        TestDataParams result = instance;
        if (result != null) {
            return result;
        }
        synchronized (TestDataParams.class) {
            if (instance == null) {
                instance = new TestDataParams();
            }
            return instance;
        }
    }

    public OSType getOSType() {
        return osType;
    }

    public DeviceName getDeviceName() {
        return deviceName;
    }

    public RunningPlatform getRunningPlatform() {
        return runningPlatform;
    }

    public EnvironmentType getEnvType() {
        return envType;
    }
}

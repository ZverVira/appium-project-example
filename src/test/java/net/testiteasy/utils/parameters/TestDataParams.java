package net.testiteasy.utils.parameters;

import net.testiteasy.utils.properties.MobitruCloudConfig;
import net.testiteasy.utils.properties.TestFrameworkConfig;
import net.testiteasy.utils.variables.OSType;
import net.testiteasy.utils.variables.RunningPlatform;
import net.testiteasy.utils.variables.EnvironmentType;
import org.aeonbits.owner.ConfigFactory;

import static org.apache.commons.lang3.StringUtils.upperCase;

@SuppressWarnings("unused")
public class TestDataParams {

    private static volatile TestDataParams instance;

    private final String appiumBaseUrl;

    private final String iOSAppPath;
    private final String androidAppPath;

    private final OSType osType;
    private final String platformVersion;
    private final String deviceName;
    private final RunningPlatform runningPlatform;
    private final EnvironmentType envType;

    private final String appPackage;
    private final String appActivity;

    private final String mobitruProjectName;
    private final String mobitruAuthorizationKey;

    private TestDataParams() {
        TestFrameworkConfig testConfig = ConfigFactory.create(TestFrameworkConfig.class);
        MobitruCloudConfig cloudConfig = ConfigFactory.create(MobitruCloudConfig.class);

        appiumBaseUrl = testConfig.appiumServiceUrl();

        iOSAppPath = testConfig.iOSAppPath();
        androidAppPath = testConfig.androidAppPath();

        osType = OSType.valueOf(upperCase(testConfig.devicePlatform()));
        platformVersion = testConfig.platformVersion();
        deviceName = testConfig.deviceName();
        runningPlatform = RunningPlatform.valueOf(upperCase(testConfig.runningPlatform()));
        envType = EnvironmentType.valueOf(upperCase(testConfig.envType()));

        appPackage = testConfig.appPackage();
        appActivity = testConfig.appActivity();

        mobitruProjectName = cloudConfig.mobitruProjectName();
        mobitruAuthorizationKey = cloudConfig.mobitruAuthKey();
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

    public String getAppiumBaseUrl() {
        return appiumBaseUrl;
    }

    public String getiOSAppPath() {
        return iOSAppPath;
    }

    public String getAndroidAppPath() {
        return androidAppPath;
    }

    public OSType getOSType() {
        return osType;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public RunningPlatform getRunningPlatform() {
        return runningPlatform;
    }

    public EnvironmentType getEnvType() {
        return envType;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public String getMobitruProjectName() {
        return mobitruProjectName;
    }

    public String getMobitruAuthorizationKey() {
        return mobitruAuthorizationKey;
    }
}

package net.testiteasy.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import net.testiteasy.annotations.Step;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static net.testiteasy.utils.parameters.TestDataParams.testConfig;

public class AppiumLocalDriver {


    @Step("Create local Appium driver based on OS")
    public AppiumDriver createDriver() {

        AppiumDriver driver = null;

        try {
            switch (testConfig().getOSType()) {
                case ANDROID -> {
                    driver = new AndroidDriver(
                            new URL(testConfig().getAppiumBaseUrl()),
                            new LocalCapabilities().getAndroidCapabilities());
                    return driver;
                }

                case IOS -> {
                    driver = new IOSDriver(
                            new URL(testConfig().getAppiumBaseUrl()),
                            new LocalCapabilities().getIOSCapabilities());
                    return driver;
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return driver;
    }

    static class LocalCapabilities {

        @Step("Create UiAutomator 2 capabilities for Local Android driver")
        public UiAutomator2Options getAndroidCapabilities() {

            UiAutomator2Options options = new UiAutomator2Options();

            options.setAppPackage(testConfig().getAppPackage());
            options.setAppActivity(testConfig().getAppActivity());

            options.setPlatformVersion(testConfig().getPlatformVersion());
            options.setApp(getFile(testConfig().getAndroidAppPath()).getAbsolutePath());
            options.setNewCommandTimeout(Duration.ofSeconds(11));
            options.setFullReset(false);

            return options;
        }

        @Step("Create XCUITest capabilities for Local iOS driver")
        public XCUITestOptions getIOSCapabilities() {

            XCUITestOptions options = new XCUITestOptions();

            options.setDeviceName(testConfig().getDeviceName());

            options.setApp(getFile(testConfig().getiOSAppPath()).getAbsolutePath());
            options.setPlatformVersion(testConfig().getPlatformVersion());
            options.setNewCommandTimeout(Duration.ofSeconds(11));
            options.setFullReset(false);

            return options;
        }

        @Step("Get file path which will install on the local device")
        private @NotNull File getFile(String filePath) {
            return new File(filePath);
        }
    }
}

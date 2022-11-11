package net.testiteasy.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import net.testiteasy.annotations.Step;
import net.testiteasy.utils.Context;
import net.testiteasy.utils.ScenarioContext;
import net.testiteasy.utils.services.MobitruService;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static net.testiteasy.utils.parameters.TestDataParams.testConfig;

public class AppiumRemoteDriver {

    private static final ScenarioContext scenarioContext = new ScenarioContext();

    private static final String CLOUD_APPIUM_HUB = "app.mobitru.com";

    public AppiumRemoteDriver() {
        prepareMobileCloud();
    }

    @Step("Create remote Appium driver based on OS")
    public AppiumDriver createDriver() {

        AppiumDriver driver = null;

        var key = URLEncoder.encode(testConfig().getMobitruAuthorizationKey(), StandardCharsets.UTF_8);
        var serviceDriveUrl = "https://%s:%s@%s/wd/hub".formatted(
                testConfig().getMobitruProjectName(),
                key,
                CLOUD_APPIUM_HUB
        );

        try {
            switch (testConfig().getOSType()) {
                case ANDROID -> {
                    driver = new AndroidDriver(
                            new URL(serviceDriveUrl),
                            new RemoteCapabilities().getAndroidCapabilities());
                    return driver;
                }

                case IOS -> {
                    driver = new IOSDriver(
                            new URL(serviceDriveUrl),
                            new RemoteCapabilities().getIOSCapabilities());
                    return driver;
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return driver;
    }

    @Step("Create context which will use as a memory storage")
    protected static ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    @Step("Preparing Mobile Cloud Farm before execution tests")
    private void prepareMobileCloud() {
        MobitruService mobitruService = new MobitruService();

        scenarioContext.setScenarioContext(Context.APP_ARTIFACT_ID, mobitruService.uploadAppIfNotPresentToStorage());
        scenarioContext.setScenarioContext(Context.DEVICE_SERIAL_NUMBER, mobitruService.findAvailableDevice());

        mobitruService.takeAvailableDeviceInUseByUniqueId(
                getScenarioContext().getScenarioContext(Context.DEVICE_SERIAL_NUMBER).toString());

        mobitruService.installApplicationOnTheDevice(
                getScenarioContext().getScenarioContext(Context.DEVICE_SERIAL_NUMBER).toString(),
                getScenarioContext().getScenarioContext(Context.APP_ARTIFACT_ID).toString());
    }

    static class RemoteCapabilities {

        @Step("Create UiAutomator 2 capabilities for Remote Android driver")
        public UiAutomator2Options getAndroidCapabilities() {

            UiAutomator2Options options = new UiAutomator2Options();

            options.setAppPackage(testConfig().getAppPackage());
            options.setAppActivity(testConfig().getAppActivity());

            options.setUdid(getScenarioContext().getScenarioContext(Context.DEVICE_SERIAL_NUMBER).toString());

            options.setNewCommandTimeout(Duration.ofSeconds(11));
            options.setFullReset(false);

            return options;
        }

        @Step("Create XCUITest capabilities for Remote iOS driver")
        public XCUITestOptions getIOSCapabilities() {

            XCUITestOptions options = new XCUITestOptions();

            options.setUdid(getScenarioContext().getScenarioContext(Context.DEVICE_SERIAL_NUMBER).toString());

            options.setNewCommandTimeout(Duration.ofSeconds(11));
            options.setFullReset(false);

            return options;
        }
    }
}

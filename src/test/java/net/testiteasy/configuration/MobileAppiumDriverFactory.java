package net.testiteasy.configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import net.testiteasy.annotations.Step;
import net.testiteasy.drivers.AppiumDriverProvider;
import net.testiteasy.drivers.AppiumLocalServer;
import net.testiteasy.utils.variables.RunningPlatform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.util.Optional;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static net.testiteasy.utils.parameters.TestDataParams.testConfig;

@SuppressWarnings("unused")
public class MobileAppiumDriverFactory {

    @Step("Configure Selenide with Appium")
    public void getWebDriverInstance() {

        Configuration.browserSize = null;
        Configuration.browser = AppiumDriverProvider.class.getName();

        WebDriverRunner.addListener(new AbstractWebDriverEventListener() {
        });
    }

    @Step("Run Appium Server")
    public void appiumServerSetup() {
        if (testConfig().getRunningPlatform().equals(RunningPlatform.LOCAL)) {
            AppiumLocalServer.startServer();
            if (AppiumLocalServer.getServerUrl() == null) {
                throw new RuntimeException("""
                        Unable to start Appium server for: device %s
                        type OS: %s
                        """.formatted(testConfig().getDeviceName(), testConfig().getOSType().getOsType())
                );
            }
        }
    }

    @Step("Shut down the driver")
    public void closeDriver() {
        try {
            if (testConfig().getRunningPlatform() == RunningPlatform.EPAM_CLOUD) {
                Optional.of(WebDriverRunner.driver().getWebDriver()).ifPresent(WebDriver::quit);
            }
            closeWebDriver();
        } catch (WebDriverException e) {
            throw new RuntimeException(e);
        }
    }

}

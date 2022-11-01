package net.testiteasy.configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import net.testiteasy.annotations.Step;
import net.testiteasy.drivers.AppiumDriverProvider;
import net.testiteasy.drivers.AppiumLocalServer;
import net.testiteasy.utils.variables.RunningPlatform;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

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
                        """.formatted(testConfig().getDeviceName().name(), testConfig().getOSType().getOsType())
                );
            }
        }
    }

}

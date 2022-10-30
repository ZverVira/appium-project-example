package net.testiteasy.drivers;

import com.codeborne.selenide.WebDriverProvider;
import net.testiteasy.annotations.Step;
import net.testiteasy.utils.variables.OSType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static net.testiteasy.utils.parameters.TestDataParams.testConfig;

@ParametersAreNonnullByDefault
public class AppiumDriverProvider implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    @Step("Create Appium Driver")
    public WebDriver createDriver(Capabilities capabilities) {

        WebDriver driver = null;

        switch (testConfig().getRunningPlatform()) {
            case LOCAL:
                if (testConfig().getOSType() == OSType.ANDROID) {
                    driver = new AppiumLocalAndroidDriver().createDriver();
                    return driver;
                } else if (testConfig().getOSType() == OSType.IOS) {
                    driver = new AppiumLocalIOSDriver().createDriver();
                    return driver;
                }
                break;
            case EPAM_CLOUD: //TODO need to add implementation
                break;

        }
        return driver;
    }
}

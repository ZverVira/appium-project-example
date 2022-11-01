package net.testiteasy.drivers;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import net.testiteasy.capatibilitesreader.DriverCapabilitiesLoader;
import net.testiteasy.utils.variables.DeviceName;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumLocalIOSDriver {

    DriverCapabilitiesLoader capabilitiesLoader = new DriverCapabilitiesLoader();

    public WebDriver createDriver() {
        XCUITestOptions options = new XCUITestOptions();
        options.merge(capabilitiesLoader.getCapabilities(DeviceName.IPHONE_SIMULATOR));
        options.setDeviceName("iPhone 8");
        options.setApp(getFile().getAbsolutePath());
        options.setPlatformVersion("15.5");
        options.setNewCommandTimeout(Duration.ofSeconds(11));
        options.setFullReset(false);
        options.autoAcceptAlerts();

        //Create IOSDriver instance and connect to the Appium server.
        //It will launch the Wikipedia App in iOS Device using the configurations specified in Desired Capabilities

        try {
            return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Contract(" -> new")
    private @NotNull File getFile() {
        return new File("apps/Wikipedia.app");
    }
}

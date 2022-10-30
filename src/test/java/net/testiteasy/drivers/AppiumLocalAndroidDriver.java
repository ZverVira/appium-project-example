package net.testiteasy.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import net.testiteasy.capatibilitesreader.DriverCapabilitiesLoader;
import net.testiteasy.utils.variables.DeviceName;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumLocalAndroidDriver {

    DriverCapabilitiesLoader capabilitiesLoader = new DriverCapabilitiesLoader();

    public WebDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilitiesLoader.getCapabilities(DeviceName.PIXEL_3_V9));
        options.setPlatformVersion("9.0"); // it seems calculator app is not available in later Android versions
        options.setAppPackage("org.wikipedia");
        options.setAppActivity(".main.MainActivity");
        options.setApp(getFile().getAbsolutePath());
        options.setNewCommandTimeout(Duration.ofSeconds(11));
        options.setFullReset(false);

        //Create AndroidDriver instance and connect to the Appium server.
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Contract(" -> new")
    private @NotNull File getFile() {
        return new File("apps/org.wikipedia.apk");
    }
}

package net.testiteasy.screens.main;

import net.testiteasy.utils.variables.OSType;

import static com.codeborne.selenide.appium.ScreenObject.screen;
import static net.testiteasy.utils.parameters.TestDataParams.testConfig;

public class MainScreenObjectFactory {

    public static MainScreen get() {
        return testConfig().getOSType().equals(OSType.ANDROID) ?
                screen(AndroidMainScreen.class) :
                screen(IOSMainScreen.class);
    }
}

package net.testiteasy.drivers;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import net.testiteasy.annotations.Step;

import java.net.URL;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.DEBUG_LOG_SPACING;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;

@SuppressWarnings("unused")
public class AppiumLocalServer {

    private static AppiumDriverLocalService service;

    @Step("Start locally Appium server")
    public static void startServer() {
        final AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withIPAddress("127.0.0.1")
                .withArgument(LOG_LEVEL, "info:debug")
                .withArgument(DEBUG_LOG_SPACING);
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    @Step("Stop locally Appium server")
    public static void stopServer() {
        service.stop();
    }

    @Step("Get locally Appium service URL")
    public static URL getServerUrl() {
        return service.getUrl();
    }
}

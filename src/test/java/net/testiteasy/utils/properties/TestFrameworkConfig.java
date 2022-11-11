package net.testiteasy.utils.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:app.properties",
        "system:properties",
        "system:env"
})
public interface TestFrameworkConfig extends Config {

    @Key("appium.service.url")
    @DefaultValue("http://127.0.0.1:4723/wd/hub")
    String appiumServiceUrl();

    @Key("ios.app.path")
    @DefaultValue("apps/Wikipedia.app")
    String iOSAppPath();

    @Key("android.app.path")
    @DefaultValue("apps/org.wikipedia.apk")
    String androidAppPath();

    @Key("device.name")
    @DefaultValue("pixel_3_v9")
    String deviceName();

    @Key("device.platform")
    @DefaultValue("android")
    String devicePlatform();

    @Key("platform.version")
    String platformVersion();

    @Key("env.type")
    @DefaultValue("qa")
    String envType();

    @Key("running.platform")
    @DefaultValue("local")
    String runningPlatform();

    @Key("app.package")
    String appPackage();

    @Key("app.activity")
    String appActivity();
}

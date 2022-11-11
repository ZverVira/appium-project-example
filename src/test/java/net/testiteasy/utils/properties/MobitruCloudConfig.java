package net.testiteasy.utils.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:app.properties",
        "system:properties",
        "system:env"
})
public interface MobitruCloudConfig extends Config {

    @Key("mobitru.project.name")
    @DefaultValue("epm-tstf")
    String mobitruProjectName();

    @Key("mobitru.auth.key")
    String mobitruAuthKey();

    @Key("mobitru.device.serial")
    @DefaultValue("711KPGS0729455")
    String mobitruDeviceSerial();
}

package net.testiteasy.utils.variables;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum OSType {

    ANDROID("Android"),
    IOS("iOS");

    private final String osType;

    OSType(String osType) {
        this.osType = osType;
    }

    public String getOsType() {
        return osType;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return """
               OSType = {
               'OS Type is ' = '%s'
               }
               """.formatted(osType);
    }
}

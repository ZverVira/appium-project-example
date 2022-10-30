package net.testiteasy.utils.variables;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum DeviceName {

    PIXEL_3_V9("net/testiteasy/devicescapabilities/local/android.capabilities"),
    IPHONE_SIMULATOR("net/testiteasy/devicescapabilities/local/ios.capabilities");

    private final String capabilities;

    DeviceName(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getCapabilities() {
        return capabilities;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return """
                DeviceName {
                'capabilities' = '%s'
                }
               """.formatted(capabilities);
    }
}

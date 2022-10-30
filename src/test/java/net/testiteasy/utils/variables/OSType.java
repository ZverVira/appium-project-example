package net.testiteasy.utils.variables;

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

    @Override
    public String toString() {
        return osType;
    }
}

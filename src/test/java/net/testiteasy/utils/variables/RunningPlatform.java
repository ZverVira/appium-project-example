package net.testiteasy.utils.variables;

@SuppressWarnings("unused")
public enum RunningPlatform {

    LOCAL("local"),
    SAUCELABS("saucelabs"),
    EPAM_CLOUD("epam_cloud");

    private final String runningPlatform;

    RunningPlatform(String runningPlatform) {
        this.runningPlatform = runningPlatform;
    }

    public String getRunningPlatform() {
        return runningPlatform;
    }

    @Override
    public String toString() {
        return """
                RunningPlatform {
                "runningPlatform" = '%s'
                }
               """.formatted(runningPlatform);
    }
}

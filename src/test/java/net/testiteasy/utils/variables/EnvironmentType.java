package net.testiteasy.utils.variables;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public enum EnvironmentType {

    DEV("dev.properties", "Development"),
    QA("qa.properties", "Staging"),
    PROD("prod.properties", "Production");

    private final String fileName;
    private final String serverTypeAlias;

    EnvironmentType(String fileName, String serverTypeAlias) {
        this.fileName = fileName;
        this.serverTypeAlias = serverTypeAlias;
    }

    public String getFileName() {
        return fileName;
    }

    public String getServerTypeAlias() {
        return serverTypeAlias;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return """
               StageType = {
               'fileName' = '%s',
               'serverTypeAlias' = '%s'
               }
               """.formatted(fileName, serverTypeAlias);
    }
}

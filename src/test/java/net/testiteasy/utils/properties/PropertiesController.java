package net.testiteasy.utils.properties;

import ch.qos.logback.classic.Logger;
import com.google.common.collect.Maps;
import net.testiteasy.utils.variables.EnvironmentType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static net.testiteasy.utils.variables.EnvironmentType.DEV;
import static net.testiteasy.utils.variables.EnvironmentType.PROD;
import static net.testiteasy.utils.variables.EnvironmentType.QA;

@SuppressWarnings("unused")
public class PropertiesController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PropertiesController.class);

    private static PropertiesController propertiesController;

    public synchronized static PropertiesController getPropertiesController() {
        if(Objects.isNull(propertiesController)) {
            var propertyControllerInstance = new PropertiesController();
            propertiesController = propertyControllerInstance;

            propertyControllerInstance.initPropertyController();
        }

        return propertiesController;
    }

    private void initPropertyController() {
        propertiesController.loadProperties("app.properties");
        String stageType = getStagePropertyFile(EnvironmentType.valueOf(propertiesController.getProperty("env.type").toUpperCase()));
        propertiesController.loadProperties(stageType);
    }

    private final Properties properties = new Properties();

    public String getProperty(final String propertyName, final String defaultValue) {
        String valueFromProperties = getProperty(propertyName);
        return valueFromProperties != null ? valueFromProperties : defaultValue;
    }

    public String getProperty(final String propertyName) {
        return properties.getProperty(propertyName);
    }

    public int getIntProperty(final String propertyName) {
        return Integer.parseInt(properties.getProperty(propertyName));
    }

    public String getSystemProperty(final String propertyName, final String defaultValue) {
        String valueFromProperties = getSystemProperty(propertyName);
        return valueFromProperties != null ? valueFromProperties : defaultValue;
    }

    public String getSystemProperty(final String propertyName) {
        return System.getProperty(propertyName);
    }

    public Map<String, String> getAllProperty() {
        return Maps.fromProperties(properties);
    }

    public void loadProperties(final String resource) {
        LOGGER.info("Reading config properties: " + resource);
        final Properties props = new Properties();
        try (final InputStream inputStream = PropertiesController.class.getClassLoader().getResourceAsStream(resource)) {
            if (inputStream == null) {
                throw new IOException("Unable to open stream for resource " + resource);
            }

            props.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Cannot read properties -> {}", e.getMessage());
        }

        for (final String propertyName : props.stringPropertyNames()) {
            if (propertyName.startsWith("+")) {
                loadProperties(propertyName.substring(1));
            }
        }
        properties.putAll(props);
    }

    private  String getStagePropertyFile(@NotNull EnvironmentType environmentType) {
        return switch (environmentType) {
            case DEV -> DEV.getFileName();
            case PROD -> PROD.getFileName();
            case QA -> QA.getFileName();
        };
    }
}

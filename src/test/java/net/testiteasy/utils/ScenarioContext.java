package net.testiteasy.utils;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ScenarioContext {

    private final Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setScenarioContext(@NotNull Context key, Object value) {
        scenarioContext.put(key.name(), value);
    }

    public Object getScenarioContext(@NotNull Context key) {
        return scenarioContext.get(key.name());
    }

    public Boolean isScenarioContextContains(@NotNull Context key) {
        return scenarioContext.containsKey(key.name());
    }
}

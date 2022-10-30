package net.testiteasy.steps;

import com.codeborne.selenide.logevents.SimpleReport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.testiteasy.configuration.MobileAppiumDriverFactory;
import org.jetbrains.annotations.NotNull;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseStepDefinitions {

    private final SimpleReport report = new SimpleReport();
    private final MobileAppiumDriverFactory driverFactory = new MobileAppiumDriverFactory();

    @Before
    public void cucumberBeforeEachScenario(@NotNull Scenario scenario) {
        scenario.log("Starting Wikipedia Tests with " + scenario.getName());
        report.start();

        driverFactory.getWebDriverInstance();
    }

    @After
    public void cucumberAfterEachScenario(@NotNull Scenario scenario) {
        scenario.log("Finished Wikipedia Tests with " + scenario.getName());

        closeWebDriver();

        report.finish(scenario.getName());
    }

}

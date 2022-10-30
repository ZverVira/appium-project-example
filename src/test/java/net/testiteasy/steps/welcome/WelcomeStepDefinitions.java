package net.testiteasy.steps.welcome;

import io.cucumber.java.en.Given;
import net.testiteasy.screens.main.MainScreen;
import net.testiteasy.screens.main.MainScreenObjectFactory;

public class WelcomeStepDefinitions {

    private final MainScreen mainScreen = MainScreenObjectFactory.get();

    @Given("^user open application$")
    public void userOpenApplication() {
        mainScreen.waitForMainContainerToAppear();
    }
}

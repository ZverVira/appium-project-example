package net.testiteasy.steps.welcome;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.testiteasy.screens.main.MainScreen;
import net.testiteasy.screens.main.MainScreenObjectFactory;

public class WelcomeStepDefinitions {

    private final MainScreen mainScreen = MainScreenObjectFactory.get();

    @Given("^user open application$")
    public void userOpenApplication() {
        System.out.println("Application was started");
    }

    @When("welcome screen ready")
    public void welcomeScreenReady() {
        mainScreen.waitForMainContainerToAppear();
    }

    @Then("user can see Explore icon")
    public void userCanSeeExploreIcon() {
        mainScreen.checkExploreIcon();
    }

    @When("user tap on the search field")
    public void userTypeInSearchLine() {
        mainScreen.clickOnSearchField();
    }
}

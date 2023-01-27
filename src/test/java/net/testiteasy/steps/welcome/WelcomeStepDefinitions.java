package net.testiteasy.steps.welcome;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.testiteasy.screens.main.MainScreen;
import net.testiteasy.screens.main.MainScreenObjectFactory;

public class WelcomeStepDefinitions {

    private final MainScreen mainScreen = MainScreenObjectFactory.get();

    @When("welcome screen ready")
    public void welcomeScreenReady() {
        mainScreen.waitForMainContainerToAppear();
    }

    @Then("user can see Explore icon")
    public void userCanSeeExploreIcon() {
        mainScreen.checkExploreIcon();
    }

    @Then("user can see the following icons")
    public void userCanSeeTheFollowingItems(DataTable dataTable) {
        mainScreen.checkPageIcons(dataTable);
    }

    @When("user tap on the search field")
    public void userTypeInSearchLine() {
        mainScreen.clickOnSearchField();
    }
}

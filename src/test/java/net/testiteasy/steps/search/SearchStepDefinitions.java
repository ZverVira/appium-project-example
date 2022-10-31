package net.testiteasy.steps.search;

import io.cucumber.java.en.When;
import net.testiteasy.screens.search.SearchScreen;
import net.testiteasy.screens.search.SearchScreenObjectFactory;

public class SearchStepDefinitions {

    private final SearchScreen searchScreen = SearchScreenObjectFactory.get();

    @When("user type in search line {string}")
    public void userTypeInSearchLine(String searchInput) {

    }
}

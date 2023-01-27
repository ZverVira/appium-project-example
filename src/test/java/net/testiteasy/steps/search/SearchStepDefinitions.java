package net.testiteasy.steps.search;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.testiteasy.screens.search.SearchScreen;
import net.testiteasy.screens.search.SearchScreenObjectFactory;

import java.awt.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

// TODO -->
// -- This step for the Search screen should be changed and finished in courses.
// -- You need to think over the logic of using this Search screen.
public class SearchStepDefinitions {

    private final SearchScreen searchScreen = SearchScreenObjectFactory.get();

    @When("user type in search line {string}")
    public void userTypeInSearchLine(String searchInput) {
        searchScreen.clickOnSearchWikipediaField();
        searchScreen.clickOnSearchField();
        searchScreen.typeTextInSearchLine(searchInput);
    }

    @Then("the article with subtitle {string} is opened")
    public void theArticleWithSubtitleIsOpened(String subtitle) {
        searchScreen.checkSubtitleText(subtitle);
    }

    @And("user opens an item with subtitle {string}")
    public void userOpensAnItemWithSubtitle(String subtitle) {
        searchScreen.openLinkWithSpecificDescription(subtitle);
    }

    @And("user waits for search results appears")
    public void userWaitsForSearchResultsAppears() {
        searchScreen.getSearchResults().shouldHave(sizeGreaterThan(0));
    }

    @And("user waits for search result {string} appears")
    public void userWaitsForSearchResultsWithSubtitleAppears(String name) {
        searchScreen.waitForSearchResult(name);
    }
}

package net.testiteasy.screens.search;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import net.testiteasy.utils.variables.OSType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static net.testiteasy.utils.parameters.TestDataParams.testConfig;

@SuppressWarnings("unused")
// TODO -->
// -- This screen should be changed and finished in courses.
// -- You need to change the selectors to the correct ones
// -- and think over the logic of using this screen.
public class SearchScreen {

    private final By SKIP_BUTTON = By.id("Skip");

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Explore\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Explore\"]")
    private SelenideElement EXPLORE_ICON;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Search Wikipedia\"])[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchField[@name=\"Search Wikipedia\"]")
    private SelenideElement SEARCH_WIKIPEDIA_FIELD;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private SelenideElement SEARCH_FIELD;

    @AndroidFindBy(id = "org.wikipedia:id/view_page_subtitle_text")
    private SelenideElement SUBTITLE;

    @AndroidFindBy(xpath = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']")
    private ElementsCollection SEARCH_RESULTS_ELEMENT;

    public void waitForMainContainerToAppear() {
        if (testConfig().getOSType() == OSType.IOS) {
            if ($(SKIP_BUTTON).is(Condition.visible)) {
                $(SKIP_BUTTON).click();
            }
        }

        EXPLORE_ICON.shouldBe(Condition.visible);
    }

    public void clickOnSearchWikipediaField() {
        SEARCH_WIKIPEDIA_FIELD.shouldBe(Condition.visible).click();
    }

    public void clickOnSearchField() {
        SEARCH_FIELD.shouldBe(Condition.visible).click();
    }

    public void typeTextInSearchLine(String searchText) {
        SEARCH_FIELD.setValue(searchText).shouldBe(visible);
    }

    public ElementsCollection getSearchResults() {
        return SEARCH_RESULTS_ELEMENT;
    }

    public void checkSubtitleText(String subtitle) {
        SUBTITLE.shouldHave(text(subtitle));
    }

    public int getSearchResultsCount() {
        return SEARCH_RESULTS_ELEMENT.size();
    }

    public void waitForSearchResult(String subtitle) {
        //$(byXpath(getResultSearchElement(subtitle))).shouldBe(visible);
        $(By.xpath(getResultSearchElement(subtitle))).shouldBe(visible);
    }

    public void openLinkWithSpecificDescription(String subtitle) {
        $(By.xpath(getResultSearchElement(subtitle))).shouldBe(visible).click();
    }

    public String getResultSearchElement(String specificDesc) {
//        String elementXpathTemplate = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBTITLE}']";
//        return elementXpathTemplate.replace("{SUBTITLE}", specificDesc);
        String elementXpathTemplate = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='%s']";
        return elementXpathTemplate.formatted(specificDesc);
    }
}

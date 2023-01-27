package net.testiteasy.screens.main;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.datatable.DataTable;
import net.testiteasy.utils.variables.OSType;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static net.testiteasy.utils.parameters.TestDataParams.testConfig;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
public class MainScreen {

    private final By SKIP_BUTTON = By.id("Skip");

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Explore\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Explore\"]")
    private SelenideElement EXPLORE_ICON;

    @AndroidFindBy(xpath = "//*[contains(@text, 'Search…')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchField[@name=\"Search Wikipedia\"]")
    private SelenideElement SEARCH_WIKIPEDIA_FIELD;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"My lists\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"My lists\"]")
    private SelenideElement MI_LISTS;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"More options\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"More options\"]")
    private SelenideElement MORE_OPTIONS;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"History\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"History\"]")
    private SelenideElement HISTORY;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Nearby\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Nearby\"]")
    private SelenideElement NEARBY;

    private String frameLayoutElements = "//android.widget.FrameLayout[@content-desc=\"%s\"]";

    public void waitForMainContainerToAppear() {
        if (testConfig().getOSType() == OSType.IOS) {
            if ($(SKIP_BUTTON).is(visible)) {
                $(SKIP_BUTTON).click();
            }
        }
    }

    public void checkExploreIcon() {
        System.out.println(WebDriverRunner.driver().getWebDriver().getPageSource());
        EXPLORE_ICON.shouldBe(visible);
    }

    public void clickOnSearchField() {
        SEARCH_WIKIPEDIA_FIELD.shouldBe(visible).click();
    }

    public boolean isIconDisplayed(String iconName) {
       String elementXpath = frameLayoutElements.formatted(iconName);
       $(By.xpath(elementXpath)).shouldBe(visible);
       return $(By.xpath(elementXpath)).isDisplayed();
    }

    public void checkPageIcons(DataTable iconNames) {
        List<String> list = iconNames.asList();
        assertAll(list.stream().map(iconName -> () -> assertTrue(isIconDisplayed(iconName), "'%s' is not displayed".formatted(iconName))));
    }
}

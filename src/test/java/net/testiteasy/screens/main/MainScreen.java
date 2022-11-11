package net.testiteasy.screens.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import net.testiteasy.utils.variables.OSType;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static net.testiteasy.utils.parameters.TestDataParams.testConfig;

@SuppressWarnings("unused")
public class MainScreen {

    private final By SKIP_BUTTON = By.id("Skip");

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Explore\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Explore\"]")
    private SelenideElement EXPLORE_ICON;

    @AndroidFindBy(xpath = "//*[contains(@text, 'Search…')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchField[@name=\"Search Wikipedia\"]")
    private SelenideElement SEARCH_WIKIPEDIA_FIELD;

    public void waitForMainContainerToAppear() {
        if (testConfig().getOSType() == OSType.IOS) {
            if ($(SKIP_BUTTON).is(Condition.visible)) {
                $(SKIP_BUTTON).click();
            }
        }
    }

    public void checkExploreIcon() {
        EXPLORE_ICON.shouldBe(Condition.visible);
    }

    public void clickOnSearchField() {
        SEARCH_WIKIPEDIA_FIELD.shouldBe(Condition.visible).click();
    }

}

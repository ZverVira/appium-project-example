package net.testiteasy.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

public class Hooks {

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            attachScreenshot(screenshot);
        }
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot(byte[] image) {
        return image;
    }
}

package net.testiteasy.utils.services;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import net.testiteasy.annotations.Step;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static net.testiteasy.utils.parameters.TestDataParams.testConfig;
import static org.apache.commons.lang3.StringUtils.lowerCase;

public class MobitruService {

    private final String APP_ALIAS = "wikipedia";

    public MobitruService() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
        RestAssured.baseURI = "https://app.mobitru.com/billing/unit/%s/automation/api"
                .formatted(testConfig().getMobitruProjectName());
    }

    @Step("First step to work with EPAM Mobile Cloud - upload needed app to the server storage")
    public String uploadAppIfNotPresentToStorage() {
        String appID;

        List<String> appList = retrieveAllUploadedArtifacts();

        if (appList.isEmpty()) {
            appID = uploadArtifact();
        } else {
            appID = appList.stream().findFirst().get();
        }

        return appID;
    }

    public List<String> retrieveAllUploadedArtifacts() {

        RestAssured.basePath = "/v1/spaces/artifacts";

        return RestAssured
                .given()
                .auth()
                .oauth2(testConfig().getMobitruAuthorizationKey())
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getList("findAll { it.alias=='%s' }.id".formatted(APP_ALIAS));
    }

    public String uploadArtifact() {

        RestAssured.basePath = "/v1/spaces/artifacts";

        String xFileName = "";
        File file = null;

        switch (testConfig().getOSType()) {
            case IOS -> {
                file = new File("apps/Wikipedia.app");
                xFileName = "Wikipedia.app";
            }
            case ANDROID -> {
                file = new File("apps/org.wikipedia.apk");
                xFileName = "org.wikipedia.apk";
            }
        }

        return RestAssured
                .given()
                .auth()
                .oauth2(testConfig().getMobitruAuthorizationKey())
                .headers(Map.of(
                        "x-File-Name", xFileName,
                        "X-Content-Type", "application/zip",
                        "X-Alias", APP_ALIAS
                ))
                .contentType("multipart/form-data")
                .multiPart("file", file)
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getString("id");
    }

    @Step("Second step to work with EPAM Mobile Cloud - find available device according to the automation needs")
    public String findAvailableDevice() {

        RestAssured.basePath = "/device/%s"
                .formatted(lowerCase(testConfig().getOSType().getOsType()));

        return Objects.requireNonNull(RestAssured
                .given()
                .auth()
                .oauth2(testConfig().getMobitruAuthorizationKey())
                .params(
                        "version", testConfig().getPlatformVersion(),
                        "type", "phone"
                )
                .when()
                .get()
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getList("desiredCapabilities.udid")
                .stream().findFirst().orElse(null)).toString();
    }

    @Step("Third step to work with EPAM Mobile Cloud - grab the device according to the automation needs")
    public void takeAvailableDeviceInUseByUniqueId(String deviceSerialNumber) {

        RestAssured.basePath = "/device/%s".formatted(deviceSerialNumber);

        RestAssured
                .given()
                .auth()
                .oauth2(testConfig().getMobitruAuthorizationKey())
                .contentType("application/json")
                .when()
                .post()
                .then()
                .statusCode(HTTP_OK);
    }

    @Step("Fourth step to work with EPAM Mobile Cloud - install needed app to the occupied device from third step")
    public void installApplicationOnTheDevice(String deviceSerialNumber, String appArtifactID) {

        RestAssured.basePath = "/storage/install/%s/%s".formatted(deviceSerialNumber, appArtifactID);

        RestAssured
                .given()
                .auth()
                .oauth2(testConfig().getMobitruAuthorizationKey())
                .when()
                .get()
                .then()
                .statusCode(HTTP_CREATED);
    }
}

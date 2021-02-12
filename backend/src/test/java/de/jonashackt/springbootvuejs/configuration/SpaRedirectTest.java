package de.jonashackt.springbootvuejs.configuration;

import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.containsString;

@SpringBootTest(
        classes = SpringBootVuejsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class SpaRedirectTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @Disabled
    void redirectSpa() {
        ValidatableResponse response = RestAssured.when().get("/path").then();
        assertSpaResponse(response);
        response = RestAssured.when().get("/nested/path").then();
        assertSpaResponse(response);
        response = RestAssured.when().get("/deep/nested/path/with/many/may/deep").then();
        assertSpaResponse(response);
    }

    @Test
    void it_should_not_interfere_with_api() {
        ValidatableResponse response = RestAssured.when().get("/api").then();
        assertNotSpaResponse(response);
    }

    @Test
    void it_should_not_interfere_with_actuator() {
        RestAssured.when().get("/actuator")
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
    }

    private static void assertSpaResponse(ValidatableResponse response) {
        response.statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(containsString("<!DOCTYPE html>"))
                .body(containsString("<script src=/static/js/chunk-vendors"))
                .body(containsString("<title>frontend</title><link href=/static/css/app"))
                .body(containsString("<link href=/static/js/app"))
                .body(containsString("<script src=/static/js/app"));
    }

    private void assertNotSpaResponse(ValidatableResponse response) {
        response.statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat()
                .body(containsString("\"status\":404,\"error\":\"Not Found\",\"message\":\"\",\"path\":\"/api\""));
    }
}
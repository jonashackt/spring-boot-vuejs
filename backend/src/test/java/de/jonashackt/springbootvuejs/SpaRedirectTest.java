package de.jonashackt.springbootvuejs;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
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
                .body(containsString("\"status\":404,\"error\":\"Not Found\",\"message\":\"No message available\",\"path\":\"/api/\""));
    }
}
package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import de.jonashackt.springbootvuejs.domain.User;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SpringBootVuejsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BackendControllerTest {

	@LocalServerPort
	private int port;

	@Before
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

	@Test
	public void saysHello() {
		when()
			.get("/api/hello")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.assertThat()
				.body(is(equalTo(BackendController.HELLO_TEXT)));
	}

	@Test
    public void addNewUserAndRetrieveItBack() {
        User norbertSiegmund = new User("Norbert", "Siegmund");

        Long userId =
            given()
                .pathParam("firstName", "Norbert")
                .pathParam("lastName", "Siegmund")
            .when()
                .post("/api/user/{lastName}/{firstName}")
            .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                    .body().as(Long.class);

	    User responseUser =
            given()
                    .pathParam("id", userId)
                .when()
                    .get("/api/user/{id}")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .assertThat()
                        .extract().as(User.class);

	    // Did Norbert came back?
        assertThat(responseUser.getFirstName(), is("Norbert"));
        assertThat(responseUser.getLastName(), is("Siegmund"));
    }

	@Test
	public void user_api_should_give_http_404_not_found_when_user_not_present_in_db() {
		Long someId = 200L;
		given()
			.pathParam("id", someId)
		.when()
			.get("/api/user/{id}")
		.then()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void secured_api_should_react_with_unauthorized_per_default() {

		given()
		.when()
			.get("/api/secured")
		.then()
			.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}

	@Test
	public void secured_api_should_give_http_200_when_authorized() {

		given()
			.auth().basic("sina", "miller")
		.when()
			.get("/api/secured")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.assertThat()
				.body(is(equalTo(BackendController.SECURED_TEXT)));
	}

}

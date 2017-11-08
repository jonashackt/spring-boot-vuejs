package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.SpringBootVuejsApplication;
import de.jonashackt.springbootvuejs.domain.User;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SpringBootVuejsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		properties = "server.port = 8089"
)
public class BackendControllerTest {

	private static final String BASE_URL = "http://localhost:8089/api";

	@Test
	public void saysHello() {
		given()
		.when()
			.get(BASE_URL + "/hello")
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
                .queryParam("firstName", "Norbert")
                .queryParam("lastName", "Siegmund")
            .when()
                .post(BASE_URL + "/user")
            .then()
                .statusCode(is(HttpStatus.SC_CREATED))
                .extract()
                    .body().as(Long.class);

	    User responseUser =
            given()
                    .pathParam("id", userId)
                .when()
                    .get(BASE_URL + "/user/{id}")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .assertThat()
                        .extract().as(User.class);

	    // Did Norbert came back?
        assertThat(responseUser.getFirstName(), is("Norbert"));
        assertThat(responseUser.getLastName(), is("Siegmund"));
    }

}

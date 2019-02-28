package br.com.b2w.resource;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.b2w.SwapiApplicationTests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlanetResourceTest extends SwapiApplicationTests{
	
	@Test
	public void should_find_by_id() {
		
		given().pathParam("id", 1)
			   .get("/planets/{id}")
			   .then().log().body().and()
			   		  .statusCode(HttpStatus.OK.value())
			   		  .body("id", equalTo(1),
			   				"name", equalTo("Alderaan"),
			   				"terrain", equalTo("grasslands, mountains"),
			   				"climate", equalTo("temperate"),
			   				"timesInMovie", equalTo(2));
	}

}

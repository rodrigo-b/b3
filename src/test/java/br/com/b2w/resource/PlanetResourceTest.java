package br.com.b2w.resource;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.b2w.SwapiApplicationTests;
import br.com.b2w.model.Planet;
import br.com.b2w.service.exception.PlanetNotFoundException;
import io.restassured.http.ContentType;
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
	
	@Test
	public void should_launch_planetnotfoundexception() throws PlanetNotFoundException{
		
		given().pathParam("id",999)
			   .get("/planets/{id}")
			   .then().log().body().and()
			   	 	  .statusCode(HttpStatus.NOT_FOUND.value())
			   	 	  .body("error",equalTo("Not exist planet with id: 999"));
		
	}

	@Test
	public void should_persist_planet() {
		
		Planet planet = new Planet(2L,
								 "Yavin IV",
								 "temperate, tropical",
								 "jungle, rainforests",
								  1);
		
		given()
			.request()
			.header("Accept", ContentType.ANY)
			.header("Content-type", ContentType.JSON)
			.body(planet)
		.when()
		.post("/planets")
		.then()
			.log().headers()
			.and()
			.log().body()
			.and()
			.statusCode(HttpStatus.CREATED.value())
			.header("Location",equalTo("http://localhost:" + port + "/planets/2"))
			.body("id", equalTo(2),
	   			   "name", equalTo("Yavin IV"),
	   			   "terrain", equalTo("jungle, rainforests"),
	   			   "climate", equalTo("temperate, tropical"),
	   			   "timesInMovie", equalTo(1));
	}
}

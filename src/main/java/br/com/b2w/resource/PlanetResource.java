package br.com.b2w.resource;


import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.model.Planet;
import br.com.b2w.repository.PlanetRepository;
import br.com.b2w.service.PlanetService;
import br.com.b2w.service.exception.PlanetNotFoundException;

@RestController
@RequestMapping("/planets")
public class PlanetResource{

	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private PlanetService planetService;
	
	@PostMapping
	public ResponseEntity create(@RequestBody Planet planet, HttpServletResponse response) throws Exception {
	
		final Planet createdPlanet = planetService.create(planet);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(planet.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return new ResponseEntity<>(planet,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable("id") Long id) throws PlanetNotFoundException {
		Planet planet = new Planet(id);
		Planet planetReturned = planetService.findById(planet);
		return new ResponseEntity<Planet>(planetReturned, HttpStatus.OK);
	}
	
	@ExceptionHandler({PlanetNotFoundException.class})
	public ResponseEntity<Error> handlePlanetNotFoundException(PlanetNotFoundException e){
		return new ResponseEntity<Error>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	class Error{
		private final String error;
		
		public Error(String error) {
			this.error = error;
		}
		
		public String getError() {
			return error;
		}
	}
	
	
}

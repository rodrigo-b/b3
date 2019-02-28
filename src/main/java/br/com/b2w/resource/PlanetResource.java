package br.com.b2w.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.model.Planet;
import br.com.b2w.repository.PlanetRepository;
import br.com.b2w.service.PlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetResource{

	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private PlanetService planetService;
	
//	@PostMapping("/create")
//	public ResponseEntity save(@RequestBody Planet planet) {
//	
//		planetRepository.save(planet);
//		return null;
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable("id") Long id) throws Exception {
		Planet planet = new Planet(id);
		Planet planetReturned = planetService.findById(planet);
		return new ResponseEntity<Planet>(planetReturned, HttpStatus.OK);
	}
}

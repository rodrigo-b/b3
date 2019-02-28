package br.com.b2w.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.b2w.model.Planet;
import br.com.b2w.repository.PlanetRepository;
import br.com.b2w.service.PlanetService;
import br.com.b2w.service.exception.PlanetNotFoundException;

@Service
public class PlanetServiceImpl implements PlanetService{

	private final PlanetRepository planetRepository;
	
	public PlanetServiceImpl(PlanetRepository planetRepository){
		this.planetRepository = planetRepository;
	}
	
	public Planet create(Planet planet) throws Exception {
		
		Optional<Planet> optional = planetRepository.findById(planet.getId());
		
		if(optional.isPresent()) {
			throw new Exception("Ja existe");
		}
		
		Planet newPlanet = new Planet(planet.getId(),
								   planet.getName(),
								   planet.getClimate(),
								   planet.getTerrain(),
								   planet.getTimesInMovie());
		
		return planetRepository.save(planet);
	}
	
	public void delete(Planet planet) throws PlanetNotFoundException{
		
		Optional<Planet> optional = planetRepository.findById(planet.getId());
		
		if(optional.isPresent()) {
			throw new Exception("Ja existe");
		}
		
		Planet newPlanet = new Planet(planet.getId(),
				   planet.getName(),
				   planet.getClimate(),
				   planet.getTerrain(),
				   planet.getTimesInMovie());
		
		planetRepository.delete(planet);
	}
	
	@Override
	public Planet findById(Planet planet) throws PlanetNotFoundException {

		final Optional<Planet> optional = planetRepository.findById(planet.getId());
	    return optional.orElseThrow(() -> new PlanetNotFoundException("Not exist planet with id: " + planet.getId()));
	}

	@Override
	public Planet findByName(Planet planet) throws PlanetNotFoundException {

		final Optional<Planet> optional = planetRepository.findByName(planet.getName());
		return optional.orElseThrow(() ->  new PlanetNotFoundException("Not exist planet with name: " + planet.getName()));
	}

}

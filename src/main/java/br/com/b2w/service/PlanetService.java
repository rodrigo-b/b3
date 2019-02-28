package br.com.b2w.service;

import br.com.b2w.model.Planet;
import br.com.b2w.service.exception.PlanetNotFoundException;

public interface PlanetService {

	Planet findById(Planet planet) throws PlanetNotFoundException;
	Planet findByName(Planet planet) throws PlanetNotFoundException;
	
}

package br.com.b2w.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.b2w.model.Planet;
import br.com.b2w.repository.PlanetRepository;
import br.com.b2w.service.impl.PlanetServiceImpl;

@RunWith(SpringRunner.class)
public class PlanetServiceTest {

	@MockBean
	private PlanetRepository planetRepository;
	
	private PlanetService sut;
	private Planet planet;
	
	private static final Long ID = 1l;
	private static final String NAME = "Alderaan";
	private static final String TERRAIN = "grasslands, mountains";
	private static final String CLIMATE = "temperate";
	private static final Integer TIMES_IN_MOVIE = 2; 
	
	@Before
	public void setUp() throws Exception{
		sut = new PlanetServiceImpl(planetRepository);
		planet = new Planet(ID,NAME,CLIMATE,TERRAIN,TIMES_IN_MOVIE);
	}
	
	@Test
	public void shouldFindPlanetById() throws Exception{
		
		when(planetRepository.findById(planet.getId())).thenReturn(Optional.of(planet));
		
		Planet planetTest = sut.findById(planet);
		
		verify(planetRepository).findById(ID);
		
		assertThat(planetTest).isNotNull();
		assertThat(planet.getName()).isEqualTo(NAME);
		
	}
}

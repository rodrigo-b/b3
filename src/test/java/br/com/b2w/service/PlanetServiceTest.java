package test.java.br.com.b2w.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class PlanetServiceTest {

	@MockBean
	private PlanetRepository planetRepository;
	
	private PessoaService sut;
	private Planet planet;
	
	private static final Long ID = 1l;
	private static final String NAME = "Alderaan";
	private static final String TERRAIN = "grasslands, mountains";
	private static final String CLIMATE = "temperate";
	private static final Integer TIMES_IN_MOVIES = 2; 
	
	@Before
	public void setUp() throws Exception{
		sut = new PlanetServiceImpl();
		planet = new Planet(ID,NAME,CLIMATE,TERRAIN,TIMES_IN_MOVIES);
	}
	
	@Test
	public void shouldFindPlanetById(){
		
		when(planetRespository.findById(planet)).thenReturn(Optional.of(planet));
		
		Planet planetTest = sut.findById();
		
		verify(planetRespository).findById(ID);
		
		assertThat(planetTest).isNotNull();
		assertThat(planet.getName()).isEqualTo(NAME);
		
	}
}

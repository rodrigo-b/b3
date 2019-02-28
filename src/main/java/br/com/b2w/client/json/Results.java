package br.com.b2w.client.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

	private String name;
	
	private String climate;
	
	private String terrain;
	
	public Results() {
	}
	
//	private List<String> films;

	public String getName() {
		return name;
	}

	public String getClimate() {
		return climate;
	}

	public String getTerrain() {
		return terrain;
	}

//	public List<String> getFilms() {
//		return films;
//	}
	
	
}

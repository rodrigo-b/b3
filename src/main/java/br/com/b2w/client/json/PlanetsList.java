package br.com.b2w.client.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetsList {

	private Results [] results;
	
	public PlanetsList() {
	}

	public Results [] getList() {
		return results;
	}
	
}

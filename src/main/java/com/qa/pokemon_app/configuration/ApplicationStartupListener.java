package com.qa.pokemon_app.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.pokemon_app.data.entity.Pokemon;
import com.qa.pokemon_app.data.repository.PokemonRepository;

@Profile("dev")
@Configuration
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

	private PokemonRepository pokemonRepository;
	
	@Autowired
	public ApplicationStartupListener(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		pokemonRepository.saveAll(List.of(
				new Pokemon("Bulbasaur", "Grass-Poison", 20),
				new Pokemon("Ivysaur", "Grass-Poison", 40),
				new Pokemon("Venusaur", "Grass-Poison", 80),
				new Pokemon("Charmander", "Fire", 20),
				new Pokemon("Charmeleon", "Fire", 40),
				new Pokemon("Charizard", "Fire", 80),
				new Pokemon("Squirtle", "Water", 20),
				new Pokemon("Wartortle", "Water", 40),
				new Pokemon("Blastoise", "Water", 80)
		));
	}

}
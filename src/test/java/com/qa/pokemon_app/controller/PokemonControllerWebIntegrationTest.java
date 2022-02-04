package com.qa.pokemon_app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.pokemon_app.data.entity.Pokemon;
import com.qa.pokemon_app.service.PokemonService;

@WebMvcTest(PokemonController.class)
public class PokemonControllerWebIntegrationTest {

	@Autowired
	private PokemonController controller;
	@MockBean
	private PokemonService pokemonService;

	private List<Pokemon> pokemons;
	private Pokemon validPokemon;
	private Pokemon pokemonCreated;

	@BeforeEach
	public void init() {
		pokemons = new ArrayList<>();
		pokemons.addAll(List.of(new Pokemon("Bulbasaur", "Grass-Poison", 20),
								new Pokemon("Ivysaur", "Grass-Poison", 40),
								new Pokemon("Venusaur", "Grass-Poison", 80),
								new Pokemon("Charmander", "Fire", 20),
								new Pokemon("Charmeleon", "Fire", 40),
								new Pokemon("Charizard", "Fire", 80),
								new Pokemon("Squirtle", "Water", 20),
								new Pokemon("Wartortle", "Water", 40),
								new Pokemon("Blastoise", "Water", 80)));
		validPokemon = new Pokemon(10, "Caterpie", "Bug", 5);
		pokemonCreated = new Pokemon("Caterpie", "Bug", 5);

	}

	@Test
	public void getAllPokemonTest() {
		ResponseEntity<List<Pokemon>> expected = new ResponseEntity<List<Pokemon>>(pokemons, HttpStatus.OK);
		when(pokemonService.getAll()).thenReturn(pokemons);
		ResponseEntity<List<Pokemon>> actual = controller.getPokemons();
		assertEquals(expected, actual);
		verify(pokemonService).getAll();
	}

	@Test
	public void getPokemonByIdTest() {
		long pokemonId = 1;
		ResponseEntity<Pokemon> expected = new ResponseEntity<Pokemon>(validPokemon, HttpStatus.OK);
		when(pokemonService.getById(pokemonId)).thenReturn(validPokemon);
		ResponseEntity<Pokemon> actual = controller.getPokemonById(pokemonId);
		assertEquals(expected, actual);
		verify(pokemonService).getById(pokemonId);
	}

	@Test
	public void createPokemonTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/pokemon/" + String.valueOf(validPokemon.getId()));
		ResponseEntity<Pokemon> expected = new ResponseEntity<Pokemon>(validPokemon, headers, HttpStatus.CREATED);
		when(pokemonService.create(pokemonCreated)).thenReturn(validPokemon);
		ResponseEntity<Pokemon> actual = controller.createPokemon(pokemonCreated);
		assertEquals(expected, actual);
		verify(pokemonService).create(pokemonCreated);
	}

	@Test
	public void updatePokemonTest() {
		Pokemon pokemonUpdated = new Pokemon(10, "Metapod", "Bug", 15);
		Pokemon pokemonToUpdate = new Pokemon("Metapod", "Bug", 15);
		long pokemonId = pokemonUpdated.getId();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/pokemon/" + String.valueOf(pokemonToUpdate.getId()));
		ResponseEntity<Pokemon> expected = new ResponseEntity<Pokemon>(pokemonToUpdate, headers, HttpStatus.ACCEPTED);
		when(pokemonService.update(pokemonId, pokemonUpdated)).thenReturn(pokemonToUpdate);
		ResponseEntity<Pokemon> actual = controller.updatePokemon(pokemonId, pokemonUpdated);
		assertEquals(expected, actual);
		verify(pokemonService).update(pokemonId, pokemonUpdated);
	}

	@Test
	public void deletePokemonTest() {
		long pokemonId = 1;
		ResponseEntity<?> expected = ResponseEntity.accepted().build();
		ResponseEntity<?> actual = controller.deletePokemon(pokemonId);
		assertEquals(expected, actual);
		verify(pokemonService).delete(pokemonId);
	}
}
package com.qa.pokemon_app.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.pokemon_app.data.entity.Pokemon;
import com.qa.pokemon_app.data.repository.PokemonRepository;

@SpringBootTest
@Transactional
public class PokemonServiceIntegrationTest {

	@Autowired
	private PokemonService pokemonService;

	@Autowired
	private PokemonRepository pokemonRepository;

	private List<Pokemon> pokemons;
	private List<Pokemon> pokemonsInDatabase;
	private long nextNewElementsId;

	@BeforeEach
	public void init() {
		pokemons = new ArrayList<>();
		pokemons = List.of(new Pokemon("Bulbasaur", "Grass-Poison", 20),
						   new Pokemon("Ivysaur", "Grass-Poison", 40),
						   new Pokemon("Venusaur", "Grass-Poison", 80),
						   new Pokemon("Charmander", "Fire", 20),
						   new Pokemon("Charmeleon", "Fire", 40),
						   new Pokemon("Charizard", "Fire", 80),
						   new Pokemon("Squirtle", "Water", 20),
						   new Pokemon("Wartortle", "Water", 40),
						   new Pokemon("Blastoise", "Water", 80));
		pokemonsInDatabase = new ArrayList<>();
		pokemonsInDatabase.addAll(pokemonRepository.saveAll(pokemons));
		int size = pokemonsInDatabase.size();
		nextNewElementsId = pokemonsInDatabase.get(size - 1).getId() + 1;
	}
/*
	@Test
	public void getAllPokemonsTest() {
		assertThat(pokemonsInDatabase).isEqualTo(pokemonService.getAll());
	}
*/
	@Test
	public void createPokemonTest() {
		Pokemon pokemonToSave = new Pokemon("Abra", "Psyhic", 5);
		Pokemon expectedPokemon = new Pokemon(nextNewElementsId, pokemonToSave.getName(), pokemonToSave.getType(),
				pokemonToSave.getLevel());

		assertThat(expectedPokemon).isEqualTo(pokemonService.create(pokemonToSave));
	}

	@Test
	public void getPokemonByIdTest() {
		Pokemon pokemonInDb = pokemonsInDatabase.get(0);
		assertThat(pokemonService.getById(pokemonInDb.getId())).isEqualTo(pokemonInDb);
	}

	@Test
	public void updatePokemonTest() {
		Pokemon pokemonInDb = pokemonsInDatabase.get(0);
		long id = pokemonInDb.getId();
		Pokemon PokemonToUpdate = new Pokemon(pokemonInDb.getId(), pokemonInDb.getName(), pokemonInDb.getType(),
				pokemonInDb.getLevel() + 1);

		Pokemon actual = pokemonService.update(id, PokemonToUpdate);
		assertThat(actual).isEqualTo(PokemonToUpdate);
	}

	@Test
	public void deletePokemonTest() {
		Pokemon pokemonInDb = pokemonsInDatabase.get(0);
		long id = pokemonInDb.getId();

		pokemonService.delete(id);

		assertThat(pokemonRepository.findById(id)).isEqualTo(Optional.empty());
	}
}

package com.qa.pokemon_app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.pokemon_app.data.entity.Pokemon;
import com.qa.pokemon_app.data.repository.PokemonRepository;
import com.qa.pokemon_app.exceptions.PokemonNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceUnitTest {

	@Mock
	private PokemonRepository pokemonRepository;

	@InjectMocks
	private PokemonService pokemonService;

	private List<Pokemon> pokemons;
	private Pokemon PokemonWithId;
	private Pokemon PokemonWithoutId;

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
		PokemonWithoutId = new Pokemon("Charmander", "Fire", 20);
		PokemonWithId = new Pokemon(1, "Charmander", "Fire", 20);
	}

	@Test
	public void getAllPokemonsTest() {
		when(pokemonRepository.findAll()).thenReturn(pokemons);
		assertThat(pokemonService.getAll()).isEqualTo(pokemons);
		verify(pokemonRepository).findAll();
	}

	@Test
	public void createPokemonTest() {
		when(pokemonRepository.save(PokemonWithoutId)).thenReturn(PokemonWithId);

		assertThat(pokemonService.create(PokemonWithoutId)).isEqualTo(PokemonWithId);
		verify(pokemonRepository).save(PokemonWithoutId);
	}

	@Test
	public void getPokemonByIdTest() {
		long id = PokemonWithId.getId();
		when(pokemonRepository.findById(id)).thenReturn(Optional.of(PokemonWithId));
		assertThat(pokemonService.getById(id)).isEqualTo(PokemonWithId);
		verify(pokemonRepository).findById(id);
	}

	@Test
	public void getPokemonByInvalidIdTest() {

		long id = 34;
		when(pokemonRepository.findById(id)).thenReturn(Optional.empty());

		PokemonNotFoundException exception = Assertions.assertThrows(PokemonNotFoundException.class, () -> {
			pokemonService.getById(id);
		});

		String expected = "Pokemon with id " + id + " does not exist";
		assertThat(exception.getMessage()).isEqualTo(expected);
		verify(pokemonRepository).findById(id);
	}

	@Test
	public void updatePokemonTest() {

		long id = PokemonWithoutId.getId();
		Pokemon PokemonToUpdate = new Pokemon(PokemonWithId.getId(), PokemonWithId.getName(), PokemonWithId.getType(),
				PokemonWithId.getLevel() + 1);

		when(pokemonRepository.existsById(id)).thenReturn(true);
		when(pokemonRepository.getById(id)).thenReturn(PokemonWithId);
		when(pokemonRepository.save(PokemonWithId)).thenReturn(PokemonToUpdate);

		assertThat(pokemonService.update(id, PokemonToUpdate)).isEqualTo(PokemonToUpdate);
		verify(pokemonRepository).existsById(id);
		verify(pokemonRepository).getById(id);
		verify(pokemonRepository).save(PokemonWithId);
	}

	@Test
	public void deletePokemonTest() {
		long id = PokemonWithId.getId();
		when(pokemonRepository.existsById(id)).thenReturn(true);
		pokemonService.delete(id);
		verify(pokemonRepository).existsById(id);
		verify(pokemonRepository).deleteById(id);
	}
}

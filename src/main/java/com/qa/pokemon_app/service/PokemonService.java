package com.qa.pokemon_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.pokemon_app.data.entity.Pokemon;
import com.qa.pokemon_app.data.repository.PokemonRepository;
import com.qa.pokemon_app.exceptions.PokemonNotFoundException;

@Service
public class PokemonService {

	private PokemonRepository pokemonRepository;

	@Autowired
	public PokemonService(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository;
	}

	public List<Pokemon> getAll() {
		return pokemonRepository.findAll();
	}

	public Pokemon getById(Long id) {
		return pokemonRepository.findById(id).orElseThrow(() -> {
			return new PokemonNotFoundException("Pokemon with id " + id + " does not exist");
		});
	}

	public Pokemon create(Pokemon pokemon) {
		Pokemon savedPokemon = pokemonRepository.save(pokemon);
		return savedPokemon;
	}

	public Pokemon update(Long id, Pokemon pokemon) {
		if (pokemonRepository.existsById(id)) {
			Pokemon pokemonInDb = pokemonRepository.getById(id);
			pokemonInDb.setLevel(pokemon.getLevel());
			pokemonInDb.setName(pokemon.getName());
			pokemonInDb.setType(pokemon.getType());
			return pokemonRepository.save(pokemonInDb);
		} else {
			throw new PokemonNotFoundException("Pokemon with id " + id + " does not exist");
		}
	}

	public void delete(Long id) {
		if (pokemonRepository.existsById(id)) {
			pokemonRepository.deleteById(id);
		} else {
			throw new PokemonNotFoundException("Pokemon with id " + id + " does not exist");
		}
	}
}
package com.qa.pokemon_app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.pokemon_app.data.entity.Pokemon;
import com.qa.pokemon_app.service.PokemonService;

@RestController
@RequestMapping(path = "/pokemon") 
public class PokemonController {
	
	private PokemonService pokemonService;
	
	@Autowired 
	public PokemonController(PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}

	@GetMapping
	public ResponseEntity<List<Pokemon>> getPokemons() {
		ResponseEntity<List<Pokemon>> pokemons = ResponseEntity.ok(pokemonService.getAll());									 
		return pokemons;
	}

	@RequestMapping(path ="/{id}", method = {RequestMethod.GET})
	public ResponseEntity<Pokemon> getPokemonById(@PathVariable("id") long id) {
		Pokemon savedPokemon = pokemonService.getById(id);
		
		ResponseEntity<Pokemon> response = ResponseEntity.status(HttpStatus.OK).body(savedPokemon);
		return response;
	}

	@PostMapping 
	public ResponseEntity<Pokemon> createPokemon(@Valid @RequestBody Pokemon pokemon) {
		Pokemon savedPokemon = pokemonService.create(pokemon);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/pokemon/" + String.valueOf(savedPokemon.getId()));
		
		ResponseEntity<Pokemon> response = new ResponseEntity<Pokemon>(savedPokemon, headers, HttpStatus.CREATED);
		return response;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Pokemon> updatePokemon(@PathVariable("id") long id, @Valid @RequestBody Pokemon pokemon) {
		Pokemon updatedPokemon = pokemonService.update(id, pokemon);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/pokemon/" + String.valueOf(updatedPokemon.getId()));
		
		return new ResponseEntity<Pokemon>(updatedPokemon, headers, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePokemon(@PathVariable("id") long id) {
		pokemonService.delete(id);
		return ResponseEntity.accepted().build();
	}

}
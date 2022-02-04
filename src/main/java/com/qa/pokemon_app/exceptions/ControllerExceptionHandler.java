package com.qa.pokemon_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = { PokemonNotFoundException.class })
	public ResponseEntity<String> pokemonNotFoundExceptions(PokemonNotFoundException pokemonNotFound) {
		return new ResponseEntity<String>(pokemonNotFound.getMessage(), HttpStatus.NOT_FOUND);
	}
}
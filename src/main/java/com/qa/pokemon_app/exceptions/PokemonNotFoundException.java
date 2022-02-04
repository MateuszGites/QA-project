package com.qa.pokemon_app.exceptions;


import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pokemon with id not found")
public class PokemonNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public PokemonNotFoundException() {
		super();
	}

	public PokemonNotFoundException(String message) {
		super(message);
	}

}
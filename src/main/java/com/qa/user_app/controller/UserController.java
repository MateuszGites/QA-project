package com.qa.user_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.user_app.data.entity.Pokemon;

@RestController
@RequestMapping(path ="/pokemon")
public class UserController {

	private List<Pokemon> pokemons = new ArrayList<>(List.of(new Pokemon(1,"Charmander","fire")));
	
	@GetMapping
	public List<Pokemon> getUsers() {
		return pokemons;
	}
}

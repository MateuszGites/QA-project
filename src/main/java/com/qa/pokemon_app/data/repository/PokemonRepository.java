package com.qa.pokemon_app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.pokemon_app.data.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

}
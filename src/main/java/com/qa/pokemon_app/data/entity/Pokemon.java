package com.qa.pokemon_app.data.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "pokemon")
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Length(min = 1, message = "Pokemon name can't be empty")
	private String name;
	
	@NotNull
	@Length(min = 1, message = "Pokemon needs to have a type")
	private String type;
	
	@Max(150)
	@Min(5)
	private int level;
	
	
	public Pokemon() {
		super();
	}
	
	
	public Pokemon(long id, String name, String type, int level) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.level = level;
	}


	public Pokemon(String name, String type, int level) {
		super();
		this.name = name;
		this.type = type;
		this.level = level;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}


	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", type=" + type + ", level=" + level + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, name, type, level);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(type, other.type) && Objects.equals(level, other.level);
	}
	
}
package com.qa.user_app.data.entity;

import java.util.Objects;

public class Pokemon {

	private int id;
	private String name;
	private String type;
	
	
	
	public Pokemon() {
		super();
	}
	
	
	public Pokemon(int id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}


	public Pokemon(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
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


	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", type=" + type + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, name, type);
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
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(type, other.type);
	}
	
}

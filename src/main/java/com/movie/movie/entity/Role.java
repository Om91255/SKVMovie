package com.movie.movie.entity;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_privillage" ,joinColumns = @JoinColumn(name="role_id"),
	inverseJoinColumns = @JoinColumn(name="privillage_id"))
	private List<Privillage> privillage;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String name, List<com.movie.movie.entity.Privillage> privillage) {
		super();
		this.id = id;
		this.name = name;
		this.privillage = privillage;
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

	public List<Privillage> getPrivillage() {
		return privillage;
	}

	public void setPrivillage(List<Privillage> privillage) {
		this.privillage = privillage;
	}

	
}

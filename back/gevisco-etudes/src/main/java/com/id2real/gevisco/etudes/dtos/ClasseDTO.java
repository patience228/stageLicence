package com.id2real.gevisco.etudes.dtos;

import com.id2real.gevisco.preinscription.dtos.NiveauDTO;

public class ClasseDTO {
	private String id;
	
	private String libelle;
	
	private NiveauDTO niveau;
	
	private boolean active;

	public ClasseDTO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public NiveauDTO getNiveau() {
		return niveau;
	}

	public void setNiveau(NiveauDTO niveau) {
		this.niveau = niveau;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}

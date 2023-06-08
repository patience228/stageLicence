package com.id2real.gevisco.preinscription.dtos;

public class NiveauDTO {

	private String id;
	 
	private String libelle;
	
	private boolean active;

	public NiveauDTO() {
		
	}
	
	

	public NiveauDTO(String libelle) {
	
		this.libelle = libelle;
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



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}

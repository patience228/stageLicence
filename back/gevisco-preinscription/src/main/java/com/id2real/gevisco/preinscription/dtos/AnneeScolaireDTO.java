package com.id2real.gevisco.preinscription.dtos;



public class AnneeScolaireDTO {
	
	private String id;
	 
	private String libelle;
	
	private long dateDebut;
	
	private long dateFin;
	
	private boolean active;

	public AnneeScolaireDTO() {
		
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

	public long getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(long dateDebut) {
		this.dateDebut = dateDebut;
	}

	public long getDateFin() {
		return dateFin;
	}

	public void setDateFin(long dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	

}

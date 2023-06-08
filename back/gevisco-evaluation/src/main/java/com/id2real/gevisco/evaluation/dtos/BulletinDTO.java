package com.id2real.gevisco.evaluation.dtos;

import com.id2real.gevisco.preinscription.dtos.EleveDTO;

public class BulletinDTO {

	private String id;
	
	private String libelle;
	
	private EleveDTO eleve;

	public BulletinDTO() {

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

	public EleveDTO getEleve() {
		return eleve;
	}

	public void setEleve(EleveDTO eleve) {
		this.eleve = eleve;
	}
	
	
}

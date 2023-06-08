package com.id2real.gevisco.utilisateurs.dtos;

import com.id2real.socle.account.dtos.UtilisateurDTO;

public class UserDTO {

	private String id;
	
	private String fonction;
	
	private boolean active;
	
	private UtilisateurDTO  utilisateur;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public UtilisateurDTO getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurDTO utilisateur) {
		this.utilisateur = utilisateur;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
}

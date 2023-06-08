package com.id2real.gevisco.inscription.dtos;



import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.dtos.NiveauDTO;

public class ScolariteDTO {

	private String id;
	
	private float montantScolarite;

	private NiveauDTO niveau;
	
	private AnneeScolaireDTO annee;

	private boolean active;
	
	public ScolariteDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getMontantScolarite() {
		return montantScolarite;
	}

	public void setMontantScolarite(float montantScolarite) {
		this.montantScolarite = montantScolarite;
	}

	public NiveauDTO getNiveau() {
		return niveau;
	}

	public void setNiveau(NiveauDTO niveau) {
		this.niveau = niveau;
	}

	public AnneeScolaireDTO getAnnee() {
		return annee;
	}

	public void setAnnee(AnneeScolaireDTO annee) {
		this.annee = annee;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}

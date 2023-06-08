package com.id2real.gevisco.etudes.dtos;



import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;

public class TitulaireDTO {
	
	private String id;
	
	private AnneeScolaireDTO annee;
	
	private ClasseDTO classe;
	
	private ProfesseurDTO professeur;
	
	private boolean active;

	public TitulaireDTO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProfesseurDTO getProfesseur() {
		return professeur;
	}

	public void setProfesseur(ProfesseurDTO professeur) {
		this.professeur = professeur;
	}

	public AnneeScolaireDTO getAnnee() {
		return annee;
	}

	public void setAnnee(AnneeScolaireDTO annee) {
		this.annee = annee;
	}

	public ClasseDTO getClasse() {
		return classe;
	}

	public void setClasse(ClasseDTO classe) {
		this.classe = classe;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	
	
	
}

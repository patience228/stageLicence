package com.id2real.gevisco.etudes.dtos;

public class EnseignementUpdateDTO {
	private ProfesseurDTO professeur;
	
	private MatiereDTO  matiereOld;
	
	private MatiereDTO  matiere;

	public ProfesseurDTO getProfesseur() {
		return professeur;
	}

	public void setProfesseur(ProfesseurDTO professeur) {
		this.professeur = professeur;
	}

	public MatiereDTO getMatiereOld() {
		return matiereOld;
	}

	public void setMatiereOld(MatiereDTO matiereOld) {
		this.matiereOld = matiereOld;
	}

	public MatiereDTO getMatiere() {
		return matiere;
	}

	public void setMatiere(MatiereDTO matiere) {
		this.matiere = matiere;
	}
	
	
}

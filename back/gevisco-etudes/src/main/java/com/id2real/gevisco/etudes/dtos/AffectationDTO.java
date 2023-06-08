package com.id2real.gevisco.etudes.dtos;



import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;

public class AffectationDTO {
	
	private String id;
	
	private EleveDTO eleve;
	
	private AnneeScolaireDTO annee;
	
	private ClasseDTO classe;
	
	private boolean active;

	public AffectationDTO() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EleveDTO getEleve() {
		return eleve;
	}

	public void setEleve(EleveDTO eleve) {
		this.eleve = eleve;
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

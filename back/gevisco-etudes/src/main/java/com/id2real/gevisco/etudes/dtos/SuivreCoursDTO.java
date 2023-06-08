package com.id2real.gevisco.etudes.dtos;

import java.util.Set;

import com.id2real.gevisco.preinscription.dtos.EleveDTO;

public class SuivreCoursDTO {

	private String id;
	
	private Set<MatiereDTO> matiere;
	
	private EleveDTO eleve;

	public SuivreCoursDTO() {

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

	public Set<MatiereDTO> getMatiere() {
		return matiere;
	}

	public void setMatiere(Set<MatiereDTO> matiere) {
		this.matiere = matiere;
	}

	

}

package com.id2real.gevisco.etudes.dtos;

import java.util.HashSet;
import java.util.Set;

import com.id2real.gevisco.etudes.entities.Classe;
import com.id2real.gevisco.etudes.entities.Matiere;
import com.id2real.gevisco.preinscription.dtos.NiveauDTO;
import com.id2real.gevisco.preinscription.entities.Niveau;

public class EnseignementDTO {
	private String id;
	
	private ProfesseurDTO professeur;
	
	private MatiereDTO  matiere;
	
	private Set<ClasseDTO>  classe=new HashSet<>();
	
	private boolean active;

	public EnseignementDTO() {

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



	public MatiereDTO getMatiere() {
		return matiere;
	}

	public void setMatiere(MatiereDTO matiere) {
		this.matiere = matiere;
	}

	public Set<ClasseDTO> getClasse() {
		return classe;
	}

	public void setClasse(Set<ClasseDTO> classe) {
		this.classe = classe;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	public void addClasse(Classe t) {
		ClasseDTO dto = new ClasseDTO();
		
		NiveauDTO n = new NiveauDTO();
		
		n.setId(t.getNiveau().getId());
		n.setLibelle(t.getNiveau().getLibelle());
			
			dto.setId(t.getId());
			dto.setLibelle(t.getLibelle());
			dto.setNiveau(n);
			
			
			
			this.classe.add(dto);
		}
	



}

package com.id2real.gevisco.etudes.services;

import java.util.Set;

import com.id2real.gevisco.etudes.dtos.EnseignementDTO;
import com.id2real.gevisco.etudes.dtos.EnseignementUpdateDTO;
import com.id2real.gevisco.etudes.entities.Classe;
import com.id2real.gevisco.etudes.entities.Enseignement;
import com.id2real.gevisco.etudes.entities.Matiere;
import com.id2real.socle.core.services.GenericServiceInt;

public interface EnseignementServiceInt extends GenericServiceInt<Enseignement, String, EnseignementDTO> {

	public void removeEnseignement(String classe_id, String matiere_id ,String professeur_id );
	public EnseignementDTO findClasseByProfesseurID(String id_prof,String id_matiere);
	public EnseignementDTO updateDTO(EnseignementUpdateDTO t);
	//public EnseignementDTO findMatiereByProfesseurID(String id);
	public Set<Matiere> findMatiereProfesseur(String id);
	public Set<Classe> findClasseProfesseur(String id_prof,String id_mat);
}

package com.id2real.gevisco.etudes.services;

import java.util.List;


import com.id2real.gevisco.etudes.dtos.AffectationDTO;
import com.id2real.gevisco.etudes.entities.Affectation;
import com.id2real.socle.core.services.GenericServiceInt;

public interface AffectationServiceInt extends GenericServiceInt<Affectation, String, AffectationDTO>{

	public List<AffectationDTO> findEleveBYall();
	public long CountEleveByClasseAndAnnee(String classe);
	public List<?> EleveByClasseAndAnnee(String classe);
	public long CountEleveByClasse(String classe, String annee);
	public List<AffectationDTO>   findAffectationByAnnee(String annee_id,String eleve_id);
	public List<AffectationDTO>  findAffectationByClasseAndAnnee(String classe_id, String annee_id);
}


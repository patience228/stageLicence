package com.id2real.gevisco.preinscription.services;

import java.util.List;

import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.socle.core.services.GenericServiceInt;

public interface EleveServiceInt extends GenericServiceInt<Eleve, String, EleveDTO> {
	public List<EleveDTO> findEleveInInscription();
	
	
}

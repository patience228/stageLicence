package com.id2real.gevisco.inscription.services;

import java.util.List;

import com.id2real.gevisco.inscription.dtos.InscriptionDTO;
import com.id2real.gevisco.inscription.entities.Inscription;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.socle.core.services.GenericServiceInt;

public interface InscriptionServiceInt extends GenericServiceInt<Inscription, String, InscriptionDTO> {
	 public String matricule();
	 
	 public List<InscriptionDTO> findEleveBYall();
	 public List<?> CountByAnnee();
	
}

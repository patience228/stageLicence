package com.id2real.gevisco.preinscription.services;

import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.entities.Annee_scolaire;
import com.id2real.socle.core.services.GenericServiceInt;

public interface AnneeScolaireServiceInt extends GenericServiceInt<Annee_scolaire, String, AnneeScolaireDTO>{
	
	Annee_scolaire findLast();

}

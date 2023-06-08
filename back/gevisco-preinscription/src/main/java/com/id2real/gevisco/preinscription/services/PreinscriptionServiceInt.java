package com.id2real.gevisco.preinscription.services;


import java.util.List;

import com.id2real.gevisco.preinscription.dtos.PreinscriptionDTO;
import com.id2real.gevisco.preinscription.entities.Preinscription;
import com.id2real.socle.core.services.GenericServiceInt;

public interface PreinscriptionServiceInt extends GenericServiceInt<Preinscription, String, PreinscriptionDTO> {
	public List<PreinscriptionDTO> findByEtat(int etat);
	public List<?> CountByAnnee();
	public long CountNewByAnnee(int etat);
}

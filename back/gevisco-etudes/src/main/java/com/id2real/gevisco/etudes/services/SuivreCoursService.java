package com.id2real.gevisco.etudes.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.dtos.SuivreCoursDTO;
import com.id2real.gevisco.etudes.entities.SuivreCours;
import com.id2real.gevisco.etudes.repositories.SuivreCoursRepository;
import com.id2real.gevisco.preinscription.services.EleveServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class SuivreCoursService extends GenericService<SuivreCours, String, SuivreCoursDTO> implements SuivreCoursServiceInt {

	
	public static final Logger LOG = LoggerFactory.getLogger(SuivreCoursService.class);

	@Autowired
	private SuivreCoursRepository repository;
	
	@Autowired
	private EleveServiceInt eleve;
	
	@Autowired
	private MatiereServiceInt matiere;
	
	@Override
	protected JpaRepository<SuivreCours, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<SuivreCours> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public SuivreCoursDTO getDTO(SuivreCours t) {
		SuivreCoursDTO dto = new SuivreCoursDTO();
		
		dto.setId(t.getId());
		//dto.setMatiere(this.matiere.getDTO(t.getMatiere()));
		dto.setEleve(this.eleve.getDTO(t.getEleve()));
		return dto;
	}

	@Override
	public SuivreCours getObjectFromDTO(SuivreCoursDTO dto) {
		SuivreCours cours = new SuivreCours();
		if (dto.getId() != null && this.exists(dto.getId())) {
			cours = this.find(dto.getId());
		}
		
		cours.setId(dto.getId());
		
	/*	if (dto.getMatiere() != null && dto.getMatiere().getId() != null) {
			cours.setMatiere(this.matiere.find(dto.getMatiere().getId()));
		}*/
		
		if (dto.getEleve() != null && dto.getEleve().getId() != null) {
			cours.setEleve(this.eleve.find(dto.getEleve().getId()));
		}
		return cours;
	}

}

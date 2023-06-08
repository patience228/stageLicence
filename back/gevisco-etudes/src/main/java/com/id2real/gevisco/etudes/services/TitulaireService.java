package com.id2real.gevisco.etudes.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.dtos.TitulaireDTO;
import com.id2real.gevisco.etudes.entities.Titulaire;
import com.id2real.gevisco.etudes.repositories.TitulaireRepository;
import com.id2real.gevisco.preinscription.services.AnneeScolaireServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class TitulaireService extends GenericService<Titulaire, String, TitulaireDTO> implements TitulaireServiceInt{

	public static final Logger LOG = LoggerFactory.getLogger(TitulaireService.class);

	@Autowired
	private TitulaireRepository repository;
	
	@Autowired
	private ProfesseurServiceInt professeur;
	
	@Autowired
	private ClasseServiceInt classe;
	
	@Autowired
	private AnneeScolaireServiceInt annee;
	
	@Override
	protected JpaRepository<Titulaire, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Titulaire> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public TitulaireDTO getDTO(Titulaire t) {
		TitulaireDTO dto = new TitulaireDTO();
		
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setProfesseur(this.professeur.getDTO(t.getProfesseur()));
		dto.setAnnee(this.annee.getDTO(t.getAnnee()));
		dto.setClasse(this.classe.getDTO(t.getClasse()));
		
		return dto;
	}

	@Override
	public Titulaire getObjectFromDTO(TitulaireDTO dto) {
		Titulaire titulaire = new Titulaire();
		
		if (dto.getId() != null && this.exists(dto.getId())) {
			titulaire = this.find(dto.getId());
		}
		
		titulaire.setId(dto.getId());
		titulaire.setActive(dto.isActive());
		
		if (dto.getProfesseur() != null && dto.getProfesseur().getId() != null) {
			titulaire.setProfesseur(this.professeur.find(dto.getProfesseur().getId()));
		}	
		
		if (dto.getAnnee() != null && dto.getAnnee().getId() != null) {
			titulaire.setAnnee(this.annee.find(dto.getAnnee().getId()));
		}
		
		if (dto.getClasse() != null && dto.getClasse().getId() != null) {
			titulaire.setClasse(this.classe.find(dto.getClasse().getId()));
		}
		
		return titulaire;
	}
	

}

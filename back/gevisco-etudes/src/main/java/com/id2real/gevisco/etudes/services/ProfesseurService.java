package com.id2real.gevisco.etudes.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.dtos.ProfesseurDTO;
import com.id2real.gevisco.etudes.entities.Professeur;
import com.id2real.gevisco.etudes.repositories.ProfesseurRepository;
import com.id2real.socle.core.services.GenericService;
import com.id2real.socle.tiers.services.PersonnePhysiqueServiceInt;

@Service
@Transactional
public class ProfesseurService extends GenericService<Professeur, String, ProfesseurDTO> implements ProfesseurServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(ProfesseurService.class);

	@Autowired
	private ProfesseurRepository repository;
	
	@Autowired
	private PersonnePhysiqueServiceInt personne;
	
	@Override
	protected JpaRepository<Professeur, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Professeur> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public ProfesseurDTO getDTO(Professeur t) {
		ProfesseurDTO dto = new ProfesseurDTO();
		
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setEtat(t.getEtat());
		dto.setNationalite(t.getNationalite());
		dto.setLieuNaissance(t.getLieu_naissance());
		dto.setDateNaissance(t.getDate_naissance());
		dto.setNom(t.getNom());
		dto.setPrenom(t.getPrenom());
		dto.setAdresse(t.getAdresse());
		dto.setSexe(t.getSexe());
		dto.setTelephone(t.getTelephone());
		
		return dto;
	}

	@Override
	public Professeur getObjectFromDTO(ProfesseurDTO dto) {
		Professeur professeur = new Professeur();
		if (dto.getId() != null && this.exists(dto.getId())) {
			professeur = this.find(dto.getId());
		}
		
		professeur.setId(dto.getId());
		professeur.setActive(dto.isActive());
		professeur.setEtat(dto.getEtat());
		professeur.setNationalite(dto.getNationalite());
		professeur.setLieu_naissance(dto.getLieuNaissance());
		professeur.setDate_naissance(dto.getDateNaissance());
		professeur.setNom(dto.getNom());
		professeur.setPrenom(dto.getPrenom());
		professeur.setSexe(dto.getSexe());
		professeur.setAdresse(dto.getAdresse());
		professeur.setTelephone(dto.getTelephone());
	
		
		return professeur;
	}

}

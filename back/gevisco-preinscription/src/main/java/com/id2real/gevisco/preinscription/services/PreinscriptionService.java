package com.id2real.gevisco.preinscription.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.preinscription.dtos.PreinscriptionDTO;
import com.id2real.gevisco.preinscription.entities.Preinscription;
import com.id2real.gevisco.preinscription.repositpories.PreinscriptionRepository;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class PreinscriptionService extends GenericService<Preinscription, String, PreinscriptionDTO>
		implements PreinscriptionServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(PreinscriptionService.class);

	@Autowired
	private PreinscriptionRepository repository;

	@Autowired
	private NiveauServiceInt niveau;

	@Autowired
	private AnneeScolaireServiceInt annee;

	@Autowired
	private EleveServiceInt eleve;

	@Override
	protected JpaRepository<Preinscription, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Preinscription> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public PreinscriptionDTO getDTO(Preinscription t) {
		PreinscriptionDTO dto = new PreinscriptionDTO();
		dto.setId(t.getId());
		dto.setFrais(t.getFrais());
		dto.setEtat(t.getEtat());
		dto.setBulletin1(t.getBulletin1());
		dto.setBulletin2(t.getBulletin2());
		dto.setBulletin3(t.getBulletin3());
		dto.setActive(t.isActive());
		
		dto.setDatePreinscription(t.getDate_preinscription());
		dto.setClasseAnterieure(t.getClasse_anterieure());
		dto.setEcoleProvenance(t.getEcole_provenance());
		dto.setMoyenne(t.getMoyenne_obtenue());
		
		dto.setNiveau(this.niveau.getDTO(t.getNiveau()));
		dto.setAnnee(this.annee.getDTO(t.getAnnee()));
		dto.setEleve(this.eleve.getDTO(t.getEleve()));

		return dto;
	}

	@Override
	public Preinscription getObjectFromDTO(PreinscriptionDTO dto) {
		Preinscription p = new Preinscription();
		if (dto.getId() != null && this.exists(dto.getId())) {
			p = this.find(dto.getId());
		}

		p.setId(dto.getId());
		p.setFrais(dto.getFrais());
		p.setEtat(dto.getEtat());
		p.setBulletin1(dto.getBulletin1());
		p.setBulletin2(dto.getBulletin2());
		p.setBulletin3(dto.getBulletin3());
		p.setActive(dto.isActive());
		
		p.setDate_preinscription(dto.getDatePreinscription());
		p.setClasse_anterieure(dto.getClasseAnterieure());
		p.setEcole_provenance(dto.getEcoleProvenance());
		p.setMoyenne_obtenue(dto.getMoyenne());

		if (dto.getNiveau() != null && dto.getNiveau().getId() != null) {
			p.setNiveau(this.niveau.find(dto.getNiveau().getId()));
		}

		if (dto.getAnnee() != null && dto.getAnnee().getId() != null) {
			p.setAnnee(this.annee.find(dto.getAnnee().getId()));
		}

		if (dto.getEleve() != null && dto.getEleve().getId() != null) {
			p.setEleve(this.eleve.find(dto.getEleve().getId()));
		}

		return p;
	}


	
	

	@Override
	public List<PreinscriptionDTO> findByEtat(int etat) {
		List<Preinscription> classe = (List<Preinscription>) this.repository.findByEtat(etat);
		List<PreinscriptionDTO> dtos = new ArrayList<>();
		
		   if (classe != null && !classe.isEmpty()) {
			   classe.forEach((t) -> {
	                dtos.add(this.getDTO(t));
	            });
	        }
	        return dtos;
	    }

	@Override
	public List<?> CountByAnnee() {
		List<?> eleves = (List<?>) this.repository.CountByAnnee();
		
		
		  
        return eleves;
		
	}

	@Override
	public long CountNewByAnnee(int etat) {
		return this.repository.CountNewByAnnee(etat);
	}

		
	
	
	

}

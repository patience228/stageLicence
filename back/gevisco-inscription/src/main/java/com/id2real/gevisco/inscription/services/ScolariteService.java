package com.id2real.gevisco.inscription.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.inscription.dtos.ScolariteDTO;
import com.id2real.gevisco.inscription.entities.Scolarite;
import com.id2real.gevisco.inscription.repositories.ScolariteRepository;
import com.id2real.gevisco.preinscription.services.AnneeScolaireServiceInt;
import com.id2real.gevisco.preinscription.services.NiveauServiceInt;
import com.id2real.socle.core.services.GenericService;


@Service
@Transactional
public class ScolariteService extends GenericService<Scolarite, String, ScolariteDTO> implements ScolariteServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(ScolariteService.class);
	
	@Autowired
    private ScolariteRepository repository;
	
	@Autowired
    private NiveauServiceInt niveau;
	
	@Autowired
    private AnneeScolaireServiceInt annee;
	
	
	@Override
	protected JpaRepository<Scolarite, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Scolarite> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public ScolariteDTO getDTO(Scolarite t) {
		ScolariteDTO dto = new ScolariteDTO();  
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setMontantScolarite(t.getMontant_scolarite());
		dto.setNiveau(this.niveau.getDTO(t.getNiveau()));
		dto.setAnnee(this.annee.getDTO(t.getAnnee()));
		return dto;
	}

	@Override
	public Scolarite getObjectFromDTO(ScolariteDTO dto) {
		Scolarite s = new Scolarite();
		
		if (dto.getId() != null && this.exists(dto.getId())) {
            s = this.find(dto.getId());
        }
		
		s.setId(dto.getId());
		s.setActive(dto.isActive());
		s.setMontant_scolarite(dto.getMontantScolarite());
		
		if (dto.getNiveau() != null && dto.getNiveau().getId() != null) {
            s.setNiveau(this.niveau.find(dto.getNiveau().getId()));
        }
		
		if (dto.getAnnee() != null && dto.getAnnee().getId() != null) {
            s.setAnnee(this.annee.find(dto.getAnnee().getId()));
        }
		return s;
	}

}

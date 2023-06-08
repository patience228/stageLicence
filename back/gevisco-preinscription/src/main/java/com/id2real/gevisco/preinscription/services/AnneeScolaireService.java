package com.id2real.gevisco.preinscription.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.entities.Annee_scolaire;
import com.id2real.gevisco.preinscription.repositpories.AnneeScolaireRepository;
import com.id2real.socle.core.services.GenericService;




@Service
@Transactional
public class AnneeScolaireService extends GenericService<Annee_scolaire, String, AnneeScolaireDTO> implements AnneeScolaireServiceInt{

	public static final Logger LOG = LoggerFactory.getLogger(AnneeScolaireService.class);

	@Autowired
    private AnneeScolaireRepository repository;
	
	@Override
	protected JpaRepository<Annee_scolaire, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Annee_scolaire> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public AnneeScolaireDTO getDTO(Annee_scolaire t) {
		AnneeScolaireDTO dto=new AnneeScolaireDTO();
		dto.setId(t.getId());
		dto.setLibelle(t.getLibelle());
		dto.setDateDebut(t.getDate_debut());
		dto.setDateFin(t.getDate_fin());
		dto.setActive(t.isActive());
		
		return dto;
	}

	@Override
	public Annee_scolaire getObjectFromDTO(AnneeScolaireDTO dto) {
		Annee_scolaire an = new Annee_scolaire();
		if (dto.getId() != null && this.exists(dto.getId())) {
            an = this.find(dto.getId());
        }
		an.setId(dto.getId());
		an.setLibelle(dto.getLibelle());
		an.setDate_debut(dto.getDateDebut());
		an.setDate_fin(dto.getDateFin());
		an.setActive(dto.isActive());
		return an;
	}

	@Override
	public Annee_scolaire findLast() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}



package com.id2real.gevisco.preinscription.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.preinscription.dtos.NiveauDTO;
import com.id2real.gevisco.preinscription.entities.Niveau;
import com.id2real.gevisco.preinscription.repositpories.NiveauRepository;
import com.id2real.socle.core.services.GenericService;


@Service
@Transactional
public class NiveauService extends GenericService<Niveau, String, NiveauDTO> implements NiveauServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(NiveauService.class);
	
	@Autowired
    private NiveauRepository repository;
	
	@Override
	protected JpaRepository<Niveau, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Niveau> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public NiveauDTO getDTO(Niveau t) {
		NiveauDTO dto = new NiveauDTO();	
		dto.setId(t.getId());
		dto.setLibelle(t.getLibelle());
		dto.setActive(t.isActive());
		return dto;
	}

	@Override
	public Niveau getObjectFromDTO(NiveauDTO dto) {
		Niveau niv = new Niveau();
		if (dto.getId() != null && this.exists(dto.getId())) {
            niv = this.find(dto.getId());
        }
		niv.setId(dto.getId());
		niv.setLibelle(dto.getLibelle());
		niv.setActive(dto.isActive());
		return niv;
	}
	
	

}

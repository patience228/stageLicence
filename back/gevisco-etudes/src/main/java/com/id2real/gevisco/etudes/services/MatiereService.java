package com.id2real.gevisco.etudes.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.dtos.MatiereDTO;
import com.id2real.gevisco.etudes.entities.Matiere;
import com.id2real.gevisco.etudes.repositories.MatiereRepository;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class MatiereService extends GenericService<Matiere, String, MatiereDTO> implements MatiereServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(MatiereService.class);

	@Autowired
	private MatiereRepository repository;
	
	@Override
	protected JpaRepository<Matiere, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Matiere> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public MatiereDTO getDTO(Matiere t) {
		MatiereDTO dto = new MatiereDTO();
		
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setLibelle(t.getLibelle());
		dto.setCoefficient(t.getCoefficient());
		dto.setType(t.getType());
		
		return dto;
	}

	@Override
	public Matiere getObjectFromDTO(MatiereDTO dto) {
		Matiere m = new Matiere();
		
		if (dto.getId() != null && this.exists(dto.getId())) {
			m = this.find(dto.getId());
		}
		
		m.setId(dto.getId());
		m.setActive(dto.isActive());
		m.setLibelle(dto.getLibelle());
		m.setCoefficient(dto.getCoefficient());
		m.setType(dto.getType());
		
		return m;
	}

}

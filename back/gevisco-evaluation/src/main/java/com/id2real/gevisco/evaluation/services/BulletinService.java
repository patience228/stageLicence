package com.id2real.gevisco.evaluation.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.evaluation.dtos.BulletinDTO;
import com.id2real.gevisco.evaluation.entities.Bulletin;
import com.id2real.gevisco.evaluation.repositories.BulletinRepository;
import com.id2real.gevisco.preinscription.services.EleveServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class BulletinService extends GenericService<Bulletin, String, BulletinDTO> implements BulletinServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(BulletinService.class);
	
	@Autowired
    private BulletinRepository repository;
	
	@Autowired
    private EleveServiceInt eleve;
	
	@Override
	protected JpaRepository<Bulletin, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Bulletin> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public BulletinDTO getDTO(Bulletin t) {
		BulletinDTO dto = new BulletinDTO();
		
		dto.setId(t.getId());
		dto.setLibelle(t.getLibelle());
		dto.setEleve(this.eleve.getDTO(t.getEleve()));
		
		return dto;
	}

	@Override
	public Bulletin getObjectFromDTO(BulletinDTO dto) {
		Bulletin b =new Bulletin();
		
		if (dto.getId() != null && this.exists(dto.getId())) {
            b = this.find(dto.getId());
        }
		
		b.setId(dto.getId());
		b.setLibelle(dto.getLibelle());
		
		if (dto.getEleve() != null && dto.getEleve().getId() != null) {
            b.setEleve(this.eleve.find(dto.getEleve().getId()));
        }
		return b;
	}

}

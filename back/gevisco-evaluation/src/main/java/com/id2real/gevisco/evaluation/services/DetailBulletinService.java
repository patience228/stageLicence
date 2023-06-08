package com.id2real.gevisco.evaluation.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.evaluation.dtos.DetailBulletinDTO;
import com.id2real.gevisco.evaluation.entities.Detail_bulletin;
import com.id2real.gevisco.evaluation.repositories.DetailBulletinRepository;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class DetailBulletinService extends GenericService<Detail_bulletin, String, DetailBulletinDTO> implements DetailBulletinServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(DetailBulletinService.class);
	
	@Autowired
    private DetailBulletinRepository repository;
	
	@Autowired
    private BulletinServiceInt bulletin;
	
	@Autowired
    private NoteServiceInt note;
	
	@Override
	protected JpaRepository<Detail_bulletin, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Detail_bulletin> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public DetailBulletinDTO getDTO(Detail_bulletin t) {
		DetailBulletinDTO dto = new DetailBulletinDTO();
		dto.setId(t.getId());
		dto.setMoyenne(t.getMoyenne());
		dto.setNote(this.note.getDTO(t.getNote()));
		dto.setBulletin(this.bulletin.getDTO(t.getBulletin()));
		
		return dto;
	}

	@Override
	public Detail_bulletin getObjectFromDTO(DetailBulletinDTO dto) {
		Detail_bulletin d = new Detail_bulletin();
		if (dto.getId() != null && this.exists(dto.getId())) {
            d = this.find(dto.getId());
        }
		
		d.setId(dto.getId());
		d.setMoyenne(dto.getMoyenne());
		
		if (dto.getNote() != null && dto.getNote().getId() != null) {
            d.setNote(this.note.find(dto.getNote().getId()));
        }
		
		if (dto.getBulletin() != null && dto.getBulletin().getId() != null) {
            d.setBulletin(this.bulletin.find(dto.getBulletin().getId()));
        }
		
		return d;
	}

}

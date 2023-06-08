package com.id2real.gevisco.etudes.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.dtos.ClasseDTO;
import com.id2real.gevisco.etudes.entities.Classe;
import com.id2real.gevisco.etudes.repositories.ClasseRepository;
import com.id2real.gevisco.preinscription.services.NiveauServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class ClasseService extends GenericService<Classe, String, ClasseDTO>  implements ClasseServiceInt{

	public static final Logger LOG = LoggerFactory.getLogger(ClasseService.class);
	
	@Autowired
    private ClasseRepository repository;
	
	@Autowired
	private NiveauServiceInt niveau;
	
	@Override
	protected JpaRepository<Classe, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Classe> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public ClasseDTO getDTO(Classe t) {
		ClasseDTO dto = new ClasseDTO();
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setLibelle(t.getLibelle());
		dto.setNiveau(this.niveau.getDTO(t.getNiveau()));
		
		return dto;
	}

	@Override
	public Classe getObjectFromDTO(ClasseDTO dto) {
		Classe classe = new Classe();
		if (dto.getId() != null && this.exists(dto.getId())) {
			classe = this.find(dto.getId());
		}
		
		classe.setId(dto.getId());
		classe.setActive(dto.isActive());
		classe.setLibelle(dto.getLibelle());
		
		if (dto.getNiveau() != null && dto.getNiveau().getId() != null) {
			classe.setNiveau(this.niveau.find(dto.getNiveau().getId()));
		}

		return classe;
	}

}

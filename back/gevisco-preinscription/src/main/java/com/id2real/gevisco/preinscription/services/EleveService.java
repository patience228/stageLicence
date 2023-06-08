package com.id2real.gevisco.preinscription.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.gevisco.preinscription.dtos.PreinscriptionDTO;
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.gevisco.preinscription.entities.Preinscription;
import com.id2real.gevisco.preinscription.repositpories.EleveRepository;
import com.id2real.socle.core.services.GenericService;
import com.id2real.socle.tiers.services.PersonnePhysiqueServiceInt;

@Service
@Transactional
public class EleveService extends GenericService<Eleve, String, EleveDTO> implements EleveServiceInt  {

	public static final Logger LOG = LoggerFactory.getLogger(EleveService.class);
	
	@Autowired
    private EleveRepository repository;
	
	@Autowired
    private PersonnePhysiqueServiceInt personne;
	
	@Override
	protected JpaRepository<Eleve, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Eleve> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public EleveDTO getDTO(Eleve t) {
		EleveDTO dto = new EleveDTO();
		dto.setId(t.getId());
		dto.setMatricule(t.getMatricule());
		dto.setNom(t.getNom());
		dto.setPrenom(t.getPrenom());
		dto.setAdresse(t.getAdresse());
		dto.setSexe(t.getSexe());
		dto.setNationalite(t.getNationalite());
		dto.setDateNaissance(t.getDate_naissance());
		dto.setLieuNaissance(t.getLieu_naissance());
		dto.setNomParent(t.getNom_parent());
		dto.setPrenomParent(t.getPrenoms_parent());
		dto.setProfessionParent(t.getProfession_parent());
		dto.setAdresseParent(t.getAdresse_parent());
		dto.setTelephoneParent(t.getTelephone_parent());
		dto.setImage(t.getImage());
		dto.setActive(t.isActive());
		
		return dto;
	}

	@Override
	public Eleve getObjectFromDTO(EleveDTO dto) {
		Eleve e = new Eleve();
		if (dto.getId() != null && this.exists(dto.getId())) {
            e = this.find(dto.getId());
        }
		
		e.setId(dto.getId());
		e.setMatricule(dto.getMatricule());
		e.setNom(dto.getNom());
		e.setPrenom(dto.getPrenom());
		e.setAdresse(dto.getAdresse());
		e.setSexe(dto.getSexe());
		e.setNationalite(dto.getNationalite());
		e.setDate_naissance(dto.getDateNaissance());
		e.setLieu_naissance(dto.getLieuNaissance());
		e.setNom_parent(dto.getNomParent());
		e.setPrenoms_parent(dto.getPrenomParent());
		e.setProfession_parent(dto.getProfessionParent());
		e.setAdresse_parent(dto.getAdresseParent());
		e.setTelephone_parent(dto.getTelephoneParent());
		e.setImage(dto.getImage());
		e.setActive(dto.isActive());
		
		return e;
	}

	@Override
	public List<EleveDTO> findEleveInInscription() {
		List<Eleve> eleves = (List<Eleve>) this.repository.findEleveInInscription();
		List<EleveDTO> dtos = new ArrayList<>();
		
		  if (eleves != null && !eleves.isEmpty()) {
			  eleves.forEach((t) -> {
	                dtos.add(this.getDTO(t));
	            });
	        }
	        return dtos;
		
	}


	
	

}

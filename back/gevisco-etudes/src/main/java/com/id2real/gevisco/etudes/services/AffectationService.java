package com.id2real.gevisco.etudes.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.dtos.AffectationDTO;
import com.id2real.gevisco.etudes.entities.Affectation;
import com.id2real.gevisco.etudes.repositories.AffectationRepository;
import com.id2real.gevisco.inscription.dtos.InscriptionDTO;
import com.id2real.gevisco.inscription.entities.Inscription;
import com.id2real.gevisco.preinscription.services.AnneeScolaireServiceInt;
import com.id2real.gevisco.preinscription.services.EleveServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class AffectationService extends GenericService<Affectation, String, AffectationDTO> implements AffectationServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(AffectationService.class);
	
	@Autowired
    private AffectationRepository repository;
	
	@Autowired
	private AnneeScolaireServiceInt annee;
	
	@Autowired
	private EleveServiceInt eleve;
	
	@Autowired
	private ClasseServiceInt classe;
	
	@Override
	protected JpaRepository<Affectation, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Affectation> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public AffectationDTO getDTO(Affectation t) {
		AffectationDTO dto = new AffectationDTO();
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setAnnee(this.annee.getDTO(t.getAnnee()));
		dto.setEleve(this.eleve.getDTO(t.getEleve()));
		dto.setClasse(this.classe.getDTO(t.getClasse()));
		return dto;
	}

	@Override
	public Affectation getObjectFromDTO(AffectationDTO dto) {
		Affectation affectation = new Affectation();
		
		if (dto.getId() != null && this.exists(dto.getId())) {
			affectation = this.find(dto.getId());
		}
		
		affectation.setId(dto.getId());
		affectation.setActive(dto.isActive());
		
		if (dto.getEleve() != null && dto.getEleve().getId() != null) {
			affectation.setEleve(this.eleve.find(dto.getEleve().getId()));
		}
		
		if (dto.getAnnee() != null && dto.getAnnee().getId() != null) {
			affectation.setAnnee(this.annee.find(dto.getAnnee().getId()));
		}
		
		if (dto.getClasse() != null && dto.getClasse().getId() != null) {
			affectation.setClasse(this.classe.find(dto.getClasse().getId()));
		}
		
		
		return affectation;
	}
	
	@Override
	public List<AffectationDTO> findEleveBYall() {
		List<Affectation> eleves = (List<Affectation>) this.repository.findEleveBYAffectation();
		List<AffectationDTO> dtos = new ArrayList<>();
		
		  if (eleves != null && !eleves.isEmpty()) {
			  eleves.forEach((t) -> {
	                dtos.add(this.getDTO(t));
	            });
	        }
	        return dtos;
	}

	@Override
	public long CountEleveByClasseAndAnnee(String classe) {
		return this.repository.CountEleveByClasseAndAnnee(classe);
	}

	@Override
	public long CountEleveByClasse(String classe, String annee) {
		return this.repository.CountEleveByClasse(classe, annee);
	}

	@Override
	public List<?> EleveByClasseAndAnnee(String classe) {
		List<?> eleves = (List<?>) this.repository.EleveByClasseAndAnnee(classe);
		
		
		  
	        return eleves;
	}

	@Override
	public List<AffectationDTO> findAffectationByClasseAndAnnee(String classe_id, String annee_id) {
		List<Affectation> eleves = (List<Affectation>) this.repository.findAffectationByClasseAndAnnee(classe_id,annee_id);
		List<AffectationDTO> dtos = new ArrayList<>();
		
		  if (eleves != null && !eleves.isEmpty()) {
			  eleves.forEach((t) -> {
	                dtos.add(this.getDTO(t));
	            });
	        }
	        return dtos;
	}
	
	@Override
	public List<AffectationDTO>  findAffectationByAnnee( String annee_id,String eleve_id) {
		
		List<Affectation> eleves = (List<Affectation>) this.repository.findAffectationByAnnee(annee_id,eleve_id);
		List<AffectationDTO> dtos = new ArrayList<>();
		
		  if (eleves != null && !eleves.isEmpty()) {
			  eleves.forEach((t) -> {
	                dtos.add(this.getDTO(t));
	            });
	        }
	        return dtos;
		
		
		
		
	        
	}

}

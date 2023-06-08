package com.id2real.gevisco.inscription.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.inscription.dtos.InscriptionDTO;
import com.id2real.gevisco.inscription.entities.Inscription;
import com.id2real.gevisco.inscription.repositories.InscriptionRepository;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.gevisco.preinscription.services.AnneeScolaireServiceInt;
import com.id2real.gevisco.preinscription.services.EleveServiceInt;
import com.id2real.gevisco.preinscription.services.NiveauServiceInt;
import com.id2real.gevisco.preinscription.services.PreinscriptionServiceInt;
import com.id2real.socle.core.services.GenericService;


@Service
@Transactional
public class InscriptionService extends GenericService<Inscription, String, InscriptionDTO> implements InscriptionServiceInt {

	public static final Logger LOG = LoggerFactory.getLogger(InscriptionService.class);
	
	@Autowired
    private InscriptionRepository repository;
	
	@Autowired
    private NiveauServiceInt niveau;
	
	@Autowired
    private AnneeScolaireServiceInt annee;
	
	@Autowired
    private EleveServiceInt eleve;
	
	@Autowired
    private PreinscriptionServiceInt preinscription;
	
	@Override
	protected JpaRepository<Inscription, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Inscription> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public InscriptionDTO getDTO(Inscription t) {
		InscriptionDTO dto = new InscriptionDTO();
		
		dto.setId(t.getId());
		dto.setEtat(t.getEtat());
		dto.setActive(t.isActive());
		dto.setMontantVerse(t.getMontant_verse());
		dto.setResteApayer(t.getReste_payer());
		dto.setDerniereClasse(t.getDerniere_classe());
		dto.setDateInscription(t.getDate_inscription());
		dto.setNiveau(this.niveau.getDTO(t.getNiveau()));
		dto.setAnnee(this.annee.getDTO(t.getAnnee()));
		dto.setEleve(this.eleve.getDTO(t.getEleve()));
		//dto.setPreinscription(this.preinscription.getDTO(t.getPreinscription()));
		return dto;
	}

	@Override
	public Inscription getObjectFromDTO(InscriptionDTO dto) {
		Inscription I = new Inscription();
		if (dto.getId() != null && this.exists(dto.getId())) {
            I = this.find(dto.getId());
        }
		
		I.setId(dto.getId());
		I.setEtat(dto.getEtat());
		I.setActive(dto.isActive());
		I.setMontant_verse(dto.getMontantVerse());
		I.setReste_payer(dto.getResteApayer());
		I.setDate_inscription(dto.getDateInscription());
		I.setDerniere_classe(dto.getDerniereClasse());
		
		if (dto.getNiveau() != null && dto.getNiveau().getId() != null) {
            I.setNiveau(this.niveau.find(dto.getNiveau().getId()));
        }
		
		if (dto.getAnnee() != null && dto.getAnnee().getId() != null) {
            I.setAnnee(this.annee.find(dto.getAnnee().getId()));
        }
		
		if (dto.getEleve() != null && dto.getEleve().getId() != null) {
            I.setEleve(this.eleve.find(dto.getEleve().getId()));
        }
		
		/*if (dto.getPreinscription() != null && dto.getPreinscription().getId() != null) {
            I.setPreinscription(this.preinscription.find(dto.getPreinscription().getId()));
        }*/
			
		return I;
	}
	
	@Override
	public String matricule() {
		long a = this.repository.count();	
		long b = a+1;	
		String m= "EPL/";
		String c=Long.toString(b);
		String r=m+c;
		System.out.println("===========================================================");
		System.out.println(r);
		System.out.println("===========================================================");
		return r;
		
	}
	
	@Override
	public List<InscriptionDTO> findEleveBYall() {
		List<Inscription> eleves = (List<Inscription>) this.repository.findEleveBYInscription();
		List<InscriptionDTO> dtos = new ArrayList<>();
		
		  if (eleves != null && !eleves.isEmpty()) {
			  eleves.forEach((t) -> {
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



}

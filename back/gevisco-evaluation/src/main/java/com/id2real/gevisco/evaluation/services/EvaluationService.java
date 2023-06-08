package com.id2real.gevisco.evaluation.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.evaluation.dtos.EvaluationDTO;
import com.id2real.gevisco.evaluation.entities.Evaluation;
import com.id2real.gevisco.evaluation.repositories.EvaluationRepository;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.gevisco.preinscription.services.AnneeScolaireServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class EvaluationService extends GenericService<Evaluation, String, EvaluationDTO>  implements EvaluationServiceInt{

	public static final Logger LOG = LoggerFactory.getLogger(EvaluationService.class);
	
	@Autowired
    private EvaluationRepository repository;
	
	@Autowired
    private AnneeScolaireServiceInt annee;
	
	@Override
	protected JpaRepository<Evaluation, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Evaluation> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public EvaluationDTO getDTO(Evaluation t) {
		EvaluationDTO dto = new EvaluationDTO();
		
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setLibelle(t.getLibelle());
		dto.setPeriode(t.getPeriode());
		dto.setDebutEval(t.getDebut_eval());
		dto.setFinEval(t.getFin_eval());
		dto.setAnnee(this.annee.getDTO(t.getAnnee()));
		
		return dto;
	}

	@Override
	public Evaluation getObjectFromDTO(EvaluationDTO dto) {
		Evaluation eval = new Evaluation();
		
		if (dto.getId() != null && this.exists(dto.getId())) {
            eval = this.find(dto.getId());
        }
		
		eval.setId(dto.getId());
		eval.setActive(dto.isActive());
		eval.setLibelle(dto.getLibelle());
		eval.setPeriode(dto.getPeriode());
		eval.setDebut_eval(dto.getDebutEval());
		eval.setFin_eval(dto.getFinEval());
		
		if (dto.getAnnee() != null && dto.getAnnee().getId() != null) {
            eval.setAnnee(this.annee.find(dto.getAnnee().getId()));
        }
		
		return eval;
	}

	@Override
	public List<EvaluationDTO> findByAnnee(String id) {
		
		List<Evaluation> eleves = (List<Evaluation>) this.repository.findByAnnee(id);
		List<EvaluationDTO> dtos = new ArrayList<>();
		
		  if (eleves != null && !eleves.isEmpty()) {
			  eleves.forEach((t) -> {
	                dtos.add(this.getDTO(t));
	            });
	        }
	        return dtos;
		
	}

}

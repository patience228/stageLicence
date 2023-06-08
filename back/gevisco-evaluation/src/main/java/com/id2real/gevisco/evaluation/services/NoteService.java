package com.id2real.gevisco.evaluation.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.services.MatiereServiceInt;
import com.id2real.gevisco.evaluation.dtos.NoteDTO;
import com.id2real.gevisco.evaluation.entities.Note;
import com.id2real.gevisco.evaluation.repositories.NoteRepository;
import com.id2real.gevisco.preinscription.services.EleveServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class NoteService extends GenericService<Note, String, NoteDTO> implements NoteServiceInt{

	public static final Logger LOG = LoggerFactory.getLogger(NoteService.class);
	
	@Autowired
    private NoteRepository repository;
	
	@Autowired
    private EleveServiceInt eleve;
	
	@Autowired
    private MatiereServiceInt matiere;
	
	@Autowired
    private EvaluationServiceInt evaluation;
	
	@Override
	protected JpaRepository<Note, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Note> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public NoteDTO getDTO(Note t) {
		NoteDTO dto = new NoteDTO();
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setNotes(t.getNotes());
		dto.setEleve(this.eleve.getDTO(t.getEleve()));
		dto.setMatiere(this.matiere.getDTO(t.getMatiere()));
		dto.setEvaluation(this.evaluation.getDTO(t.getEvaluation()));
	
		return dto;
	}

	@Override
	public Note getObjectFromDTO(NoteDTO dto) {
		Note n = new Note();
		
		if (dto.getId() != null && this.exists(dto.getId())) {
            n = this.find(dto.getId());
        }
		
		n.setId(dto.getId());
		n.setActive(dto.isActive());
		n.setNotes(dto.getNotes());
		
		if (dto.getEleve() != null && dto.getEleve().getId() != null) {
            n.setEleve(this.eleve.find(dto.getEleve().getId()));
        }
		
		if (dto.getEvaluation() != null && dto.getEvaluation().getId() != null) {
            n.setEvaluation(this.evaluation.find(dto.getEvaluation().getId()));
        }
		
		if (dto.getMatiere() != null && dto.getMatiere().getId() != null) {
            n.setMatiere(this.matiere.find(dto.getMatiere().getId()));
        }
		
		return n;
	}
	
	@Override
	public List<?> NoteByLibelle(String libelle) {
		List<?> eleves = (List<?>) this.repository.NoteByLibelle(libelle);
		
		
		  
	        return eleves;
	}

}

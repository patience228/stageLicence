package com.id2real.gevisco.evaluation.dtos;

import com.id2real.gevisco.etudes.dtos.MatiereDTO;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;

public class NoteDTO {

	private String id;
	
	private float notes;
	
	private boolean active;
	
	private EleveDTO eleve;
	
	private MatiereDTO matiere;
	
	private EvaluationDTO evaluation;

	public NoteDTO() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getNotes() {
		return notes;
	}

	public void setNotes(float notes) {
		this.notes = notes;
	}

	public EleveDTO getEleve() {
		return eleve;
	}

	public void setEleve(EleveDTO eleve) {
		this.eleve = eleve;
	}

	public MatiereDTO getMatiere() {
		return matiere;
	}

	public void setMatiere(MatiereDTO matiere) {
		this.matiere = matiere;
	}

	public EvaluationDTO getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(EvaluationDTO evaluation) {
		this.evaluation = evaluation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}


package com.id2real.gevisco.evaluation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.etudes.entities.Matiere;
import com.id2real.gevisco.evaluation.utils.EvaluationConstants;
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "note", schema = EvaluationConstants.SCHEMA)
public class Note extends AbstractAuditingEntity {

	public Note() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_note")
    private String id;
 
    @Column(name = "notes", nullable = false)
    private float notes;
    
    @Column(name = "active", nullable = true)
    private boolean active;
    
    @ManyToOne
    @JoinColumn(name = "eleve_id", referencedColumnName = "id_eleve")
    private Eleve eleve;
	
    @ManyToOne
    @JoinColumn(name = "matiere_id", referencedColumnName = "id_matiere")
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "evaluation_id", referencedColumnName = "id_evaluation")
    private Evaluation evaluation;

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

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
    
	
}

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

import com.id2real.gevisco.evaluation.utils.EvaluationConstants;
import com.id2real.gevisco.preinscription.entities.Annee_scolaire;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "evaluation", schema = EvaluationConstants.SCHEMA)
public class Evaluation extends AbstractAuditingEntity  {

	public Evaluation() {
	
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_evaluation")
    private String id;
 
    @Column(name = "libelle_evaluation", nullable = false)
    private String libelle;
    
    @Column(name = "debut_eval", nullable = true)
    private long debut_eval;
	
    @Column(name = "fin_eval", nullable = true)
    private long fin_eval;
    
    @Column(name = "periode", nullable = false)
    private String periode;
    
    
    @Column(name = "active", nullable = true)
    private boolean active;
    
    @ManyToOne
    @JoinColumn(name = "annee_id", referencedColumnName = "id_annee")
    private Annee_scolaire annee;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public long getDebut_eval() {
		return debut_eval;
	}

	public void setDebut_eval(long debut_eval) {
		this.debut_eval = debut_eval;
	}

	public long getFin_eval() {
		return fin_eval;
	}

	public void setFin_eval(long fin_eval) {
		this.fin_eval = fin_eval;
	}

	
	
	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public Annee_scolaire getAnnee() {
		return annee;
	}

	public void setAnnee(Annee_scolaire annee) {
		this.annee = annee;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
    
    
}

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
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "bulletin", schema = EvaluationConstants.SCHEMA)
public class Bulletin extends AbstractAuditingEntity {

	public Bulletin() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_bulletin")
    private String id;
 
    @Column(name = "libelle_bulletin", nullable = false)
    private String libelle;
    
    @ManyToOne
    @JoinColumn(name = "eleve_id", referencedColumnName = "id_eleve")
    private Eleve eleve;

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

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	
	
}

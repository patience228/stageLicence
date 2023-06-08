package com.id2real.gevisco.etudes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.etudes.utils.EtudesConstants;
import com.id2real.gevisco.preinscription.entities.Annee_scolaire;
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;


@Entity
@Table(name = "affectation", schema = EtudesConstants.SCHEMA)
public class Affectation extends AbstractAuditingEntity {

	public Affectation() {
	
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_affectation")
    private String id;
 
    @ManyToOne
    @JoinColumn(name = "eleve_id", referencedColumnName = "id_eleve")
    private Eleve eleve;

    @ManyToOne
    @JoinColumn(name = "classe_id", referencedColumnName = "id_classe")
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "annee_id", referencedColumnName = "id_annee")
    private Annee_scolaire annee;
    
    @Column(name = "active", nullable=true)
    private boolean active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
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

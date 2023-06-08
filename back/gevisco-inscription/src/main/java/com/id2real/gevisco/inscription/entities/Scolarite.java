package com.id2real.gevisco.inscription.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.inscription.utils.InscriptionConstants;
import com.id2real.gevisco.preinscription.entities.Annee_scolaire;
import com.id2real.gevisco.preinscription.entities.Niveau;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;



@Entity
@Table(name = "scolarite", schema = InscriptionConstants.SCHEMA)
public class Scolarite extends AbstractAuditingEntity{

	public Scolarite() {
	
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_scolarite")
    private String id;
 
    @Column(name = "montant_scolarite", nullable = false)
    private float montant_scolarite;
    
    @Column(name = "active", nullable = true)
    private boolean active;
    
   @ManyToOne
    @JoinColumn(name = "niveau_id", referencedColumnName = "id_niveau")
    private Niveau niveau;
    
    @ManyToOne
    @JoinColumn(name = "annee_id", referencedColumnName = "id_annee")
    private Annee_scolaire annee;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getMontant_scolarite() {
		return montant_scolarite;
	}

	public void setMontant_scolarite(float montant_scolarite) {
		this.montant_scolarite = montant_scolarite;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
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

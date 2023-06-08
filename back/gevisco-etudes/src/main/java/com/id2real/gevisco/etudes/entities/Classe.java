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
import com.id2real.gevisco.preinscription.entities.Niveau;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "classe", schema = EtudesConstants.SCHEMA)
public class Classe extends AbstractAuditingEntity{

	public Classe() {
	
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_classe")
    private String id;
 
    @Column(name = "libelle_classe", nullable = false)
    private String libelle;
    
    @Column(name = "active", nullable = true)
    private boolean active;
    
    @ManyToOne
    @JoinColumn(name = "niveau_id", referencedColumnName = "id_niveau")
    private Niveau niveau;

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

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}

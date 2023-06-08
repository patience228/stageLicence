package com.id2real.gevisco.inscription.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.inscription.utils.InscriptionConstants;
import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.entities.Annee_scolaire;
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.gevisco.preinscription.entities.Niveau;
import com.id2real.gevisco.preinscription.entities.Preinscription;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;



@Entity
@Table(name = "inscription", schema = InscriptionConstants.SCHEMA)
public class Inscription extends AbstractAuditingEntity {

	public Inscription() {
	
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_inscription")
    private String id;
 
    @Column(name = "montant_verse", nullable = true)
    private float montant_verse;
    
    
    
   @Column(name = "reste_payer", nullable = true)
    private float reste_payer;
    
    @Column(name = "derniere_classe", nullable = false)
    private String derniere_classe;
    
    @Column(name = "date_inscription", nullable = true)
    private long date_inscription;
    
    @ManyToOne
    @JoinColumn(name = "niveau_id", referencedColumnName = "id_niveau")
    private Niveau niveau;
    
    @ManyToOne
    @JoinColumn(name = "annee_id", referencedColumnName = "id_annee")
    private Annee_scolaire annee;
    
    @ManyToOne
    @JoinColumn(name = "eleve_id", referencedColumnName = "id_eleve")
    private Eleve eleve;
    
    @Column(name = "etat", nullable = true)
    private int etat;
    
    @Column(name = "active", nullable = true)
    private boolean active;
    
    @OneToOne
    @JoinColumn(name = "preinscription_id", referencedColumnName = "id_preinscription")
    private Preinscription preinscription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getMontant_verse() {
		return montant_verse;
	}

	public void setMontant_verse(float montant_verse) {
		this.montant_verse = montant_verse;
	}

	public float getReste_payer() {
		return reste_payer;
	}

	public void setReste_payer(float reste_payer) {
		this.reste_payer = reste_payer;
	}

	public String getDerniere_classe() {
		return derniere_classe;
	}

	public void setDerniere_classe(String derniere_classe) {
		this.derniere_classe = derniere_classe;
	}

	public long getDate_inscription() {
		return date_inscription;
	}

	public void setDate_inscription(long date_inscription) {
		this.date_inscription = date_inscription;
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

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	
	

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Preinscription getPreinscription() {
		return preinscription;
	}

	public void setPreinscription(Preinscription preinscription) {
		this.preinscription = preinscription;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	

	  

    
    
}

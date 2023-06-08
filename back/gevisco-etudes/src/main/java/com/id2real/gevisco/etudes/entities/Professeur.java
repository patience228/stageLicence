package com.id2real.gevisco.etudes.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.etudes.utils.EtudesConstants;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "professeur", schema = EtudesConstants.SCHEMA)
public class Professeur extends AbstractAuditingEntity {
	
	
	public Professeur() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_professeur")
    private String id;
 
	@Column(name = "nom_prof", nullable = false)
	private String nom;
	    
	@Column(name = "prenom_prof", nullable = false)
	private String prenom;
	  
	@Column(name = "sexe_prof", nullable = false)
	private String sexe;
	    
	@Column(name = "telephone_prof", nullable = false)
	private String telephone;
	    
	@Column(name = "adresse_prof", nullable = false)
	private String adresse;
	    
	@Column(name = "nationalite_prof", nullable = false)
	private String nationalite;
	    
	@Column(name = "date_naissance_prof", nullable = true)
	private long date_naissance;
	    
	@Column(name = "lieu_naissance_prof", nullable = false)
	private String lieu_naissance;
	
	
	  @Column(name = "etat", nullable = true)
	    private int etat;
	    
	  @Column(name = "active", nullable = true)
	    private boolean active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public long getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(long date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getLieu_naissance() {
		return lieu_naissance;
	}

	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	
	
}

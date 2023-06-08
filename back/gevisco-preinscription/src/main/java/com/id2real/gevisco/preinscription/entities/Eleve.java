package com.id2real.gevisco.preinscription.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.preinscription.utils.PreinscriptionConstants;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;


@Entity
@Table(name = "eleve", schema = PreinscriptionConstants.SCHEMA,
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"matricule"})})
public class Eleve extends AbstractAuditingEntity  {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_eleve")
    private String id;

    @Column(name = "matricule", nullable = true, unique = true)
    private String matricule;
    
    @Column(name = "nom_eleve", nullable = false)
    private String nom;
    
    @Column(name = "prenom_eleve", nullable = false)
    private String prenom;
    
    @Column(name = "sexe", nullable = false)
    private String sexe;
    
    @Column(name = "adresse_eleve", nullable = false)
    private String adresse;
    
    @Column(name = "nationalite", nullable = false)
    private String nationalite;
    
    @Column(name = "date_naissance", nullable = true)
    private long date_naissance;
    
    @Column(name = "lieu_naissance", nullable = false)
    private String lieu_naissance;

    @Column(name = "nom_parent", nullable = false)
    private String nom_parent;

    @Column(name = "prenoms_parent", nullable = false)
    private String prenoms_parent;
    
    @Column(name = "adresse_parent", nullable = true)
    private String adresse_parent;
    
    @Column(name = "profession_parent", nullable = true)
    private String profession_parent;
    
    @Column(name = "telephone_parent", nullable = true)
    private String telephone_parent;
    
    @Column(name = "image", nullable = true)
    private String image;
   
    @Column(name = "active", nullable = true)
    private boolean active;
    
    

	public Eleve() {
		
	}

	

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}





	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
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





	public String getNom_parent() {
		return nom_parent;
	}


	public void setNom_parent(String nom_parent) {
		this.nom_parent = nom_parent;
	}





	public String getPrenoms_parent() {
		return prenoms_parent;
	}

	public void setPrenoms_parent(String prenoms_parent) {
		this.prenoms_parent = prenoms_parent;
	}





	public String getAdresse_parent() {
		return adresse_parent;
	}

	public void setAdresse_parent(String adresse_parent) {
		this.adresse_parent = adresse_parent;
	}





	public String getProfession_parent() {
		return profession_parent;
	}

	public void setProfession_parent(String profession_parent) {
		this.profession_parent = profession_parent;
	}





	public String getTelephone_parent() {
		return telephone_parent;
	}

	public void setTelephone_parent(String telephone_parent) {
		this.telephone_parent = telephone_parent;
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





	public String getAdresse() {
		return adresse;
	}





	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}





	public String getImage() {
		return image;
	}





	public void setImage(String image) {
		this.image = image;
	}





	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}







	


	
    
    
}






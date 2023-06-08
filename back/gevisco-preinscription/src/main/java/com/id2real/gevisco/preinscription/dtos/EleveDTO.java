package com.id2real.gevisco.preinscription.dtos;




public class EleveDTO {

	 private String id;
	 
	 private String matricule;
	 
	 private String nom;
	 
	 private String prenom;
	 
	 private String sexe;
	 
	 private String adresse;
	 
	 private String nationalite;
	 
	 private long dateNaissance;
	 
	 private String lieuNaissance;
	 
	 private String image;
	 
	 private String nomParent;
	 
	 private String prenomParent;
	 
	 private String adresseParent;
	 
	 private String professionParent;
	 
	 private String telephoneParent;
	 
	 private boolean active;
	 
	 

	public EleveDTO() {
		
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

	public long getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(long dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getNomParent() {
		return nomParent;
	}

	public void setNomParent(String nomParent) {
		this.nomParent = nomParent;
	}

	public String getPrenomParent() {
		return prenomParent;
	}

	public void setPrenomParent(String prenomParent) {
		this.prenomParent = prenomParent;
	}

	public String getAdresseParent() {
		return adresseParent;
	}

	public void setAdresseParent(String adresseParent) {
		this.adresseParent = adresseParent;
	}

	public String getProfessionParent() {
		return professionParent;
	}

	public void setProfessionParent(String professionParent) {
		this.professionParent = professionParent;
	}

	public String getTelephoneParent() {
		return telephoneParent;
	}

	public void setTelephoneParent(String telephoneParent) {
		this.telephoneParent = telephoneParent;
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

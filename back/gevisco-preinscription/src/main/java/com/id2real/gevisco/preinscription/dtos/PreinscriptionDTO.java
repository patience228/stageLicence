package com.id2real.gevisco.preinscription.dtos;


public class PreinscriptionDTO {
	
	private String id;
	
	private float frais;
	
	private String classeAnterieure;
	
	private String ecoleProvenance;
	
	private long datePreinscription;
	
	private float moyenne;

	private NiveauDTO niveau;
	
	private AnneeScolaireDTO annee;
	
	private EleveDTO eleve;
	
	 private int etat;
	 
	 private boolean active;
	 
	 private String bulletin1;
	 
	 private String bulletin2;
	 
	 private String bulletin3;

	public PreinscriptionDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getFrais() {
		return frais;
	}

	public void setFrais(float frais) {
		this.frais = frais;
	}

	public String getClasseAnterieure() {
		return classeAnterieure;
	}

	public void setClasseAnterieure(String classeAnterieure) {
		this.classeAnterieure = classeAnterieure;
	}

	public String getEcoleProvenance() {
		return ecoleProvenance;
	}

	public void setEcoleProvenance(String ecoleProvenance) {
		this.ecoleProvenance = ecoleProvenance;
	}

	public long getDatePreinscription() {
		return datePreinscription;
	}

	public void setDatePreinscription(long datePreinscription) {
		this.datePreinscription = datePreinscription;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}

	public NiveauDTO getNiveau() {
		return niveau;
	}

	public void setNiveau(NiveauDTO niveau) {
		this.niveau = niveau;
	}

	public AnneeScolaireDTO getAnnee() {
		return annee;
	}

	public void setAnnee(AnneeScolaireDTO annee) {
		this.annee= annee;
	}

	public EleveDTO getEleve() {
		return eleve;
	}

	public void setEleve(EleveDTO eleve) {
		this.eleve = eleve;
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

	public String getBulletin1() {
		return bulletin1;
	}

	public void setBulletin1(String bulletin1) {
		this.bulletin1 = bulletin1;
	}

	public String getBulletin2() {
		return bulletin2;
	}

	public void setBulletin2(String bulletin2) {
		this.bulletin2 = bulletin2;
	}

	public String getBulletin3() {
		return bulletin3;
	}

	public void setBulletin3(String bulletin3) {
		this.bulletin3 = bulletin3;
	}

	
	
	
	
}

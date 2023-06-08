package com.id2real.gevisco.inscription.dtos;



import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.gevisco.preinscription.dtos.NiveauDTO;
import com.id2real.gevisco.preinscription.dtos.PreinscriptionDTO;

public class InscriptionDTO {

	
	private String id;
	
	private float montantVerse;
	
	private float resteApayer;
	
	private String derniereClasse;
	
	private long dateInscription;

	private NiveauDTO niveau;
	
	private AnneeScolaireDTO annee;
	
	private EleveDTO eleve;
	
	private int etat;
	
	private boolean active;
	
	private PreinscriptionDTO preinscription;

	public InscriptionDTO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getMontantVerse() {
		return montantVerse;
	}

	public void setMontantVerse(float montantVerse) {
		this.montantVerse = montantVerse;
	}

	public float getResteApayer() {
		return resteApayer;
	}

	public void setResteApayer(float resteApayer) {
		this.resteApayer = resteApayer;
	}

	public String getDerniereClasse() {
		return derniereClasse;
	}

	public void setDerniereClasse(String derniereClasse) {
		this.derniereClasse = derniereClasse;
	}

	public long getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(long dateInscription) {
		this.dateInscription = dateInscription;
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
		this.annee = annee;
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

	public PreinscriptionDTO getPreinscription() {
		return preinscription;
	}

	public void setPreinscription(PreinscriptionDTO preinscription) {
		this.preinscription = preinscription;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}

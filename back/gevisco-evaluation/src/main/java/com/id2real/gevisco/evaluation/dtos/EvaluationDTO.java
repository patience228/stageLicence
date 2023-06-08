package com.id2real.gevisco.evaluation.dtos;



import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;

public class EvaluationDTO {

	private String id;
	
	private String libelle;
	
	private long debutEval;
	
	private long finEval;
	
	private boolean active;
	
	private String periode;
	
	private AnneeScolaireDTO annee;

	public EvaluationDTO() {

	}

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

	public long getDebutEval() {
		return debutEval;
	}

	public void setDebutEval(long debutEval) {
		this.debutEval = debutEval;
	}

	public long getFinEval() {
		return finEval;
	}

	public void setFinEval(long finEval) {
		this.finEval = finEval;
	}

	
	
	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public AnneeScolaireDTO getAnnee() {
		return annee;
	}

	public void setAnnee(AnneeScolaireDTO annee) {
		this.annee = annee;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}

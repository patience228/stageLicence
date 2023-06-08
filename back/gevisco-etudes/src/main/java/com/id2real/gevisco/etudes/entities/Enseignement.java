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
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "enseignement", schema = EtudesConstants.SCHEMA)
public class Enseignement extends AbstractAuditingEntity {

	public Enseignement() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_enseignement")
    private String id;
	
    @ManyToOne
    @JoinColumn(name = "professeur_id", referencedColumnName = "id_professeur")
    private Professeur professeur;
    
    @ManyToOne
    @JoinColumn(name = "matiere_id", referencedColumnName = "id_matiere")
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "classe_id", referencedColumnName = "id_classe")
    private Classe classe;

    
    @Column(name = "active", nullable=true)
    private boolean active;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Professeur getProfesseur() {
		return professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
    
    
}

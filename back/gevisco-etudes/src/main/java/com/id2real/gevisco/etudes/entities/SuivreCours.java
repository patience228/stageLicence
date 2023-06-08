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
import com.id2real.gevisco.preinscription.entities.Eleve;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "suivreCours", schema = EtudesConstants.SCHEMA)
public class SuivreCours extends AbstractAuditingEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_suivreCours")
    private String id;
	
    @ManyToOne
    @JoinColumn(name = "eleve_id", referencedColumnName = "id_eleve")
    private Eleve eleve;
    
    @ManyToOne
    @JoinColumn(name = "matiere_id", referencedColumnName = "id_matiere")
    private Matiere matiere;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	
    
}

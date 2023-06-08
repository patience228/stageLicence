package com.id2real.gevisco.preinscription.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


import com.id2real.gevisco.preinscription.utils.PreinscriptionConstants;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "annee_scolaire", schema = PreinscriptionConstants.SCHEMA)
public class Annee_scolaire extends AbstractAuditingEntity {
	
	 public Annee_scolaire() {
		
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
	    @Column(name = "id_annee")
	    private String id;
	 
	    @Column(name = "libelle_annee", nullable = false)
	    private String libelle;
	    
	    @Column(name = "date_debut", nullable = true)
	    private long date_debut;
	    
	    @Column(name = "date_fin", nullable = true)
	    private long date_fin;
	    
	    @Column(name = "active", nullable = true)
	    private boolean active;

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

		public long getDate_debut() {
			return date_debut;
		}

		public void setDate_debut(long date_debut) {
			this.date_debut = date_debut;
		}

		public long getDate_fin() {
			return date_fin;
		}

		public void setDate_fin(long date_fin) {
			this.date_fin = date_fin;
		}

		
		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		@Override
		public String toString() {
			return "Annee_scolaire [id=" + id + ", libelle=" + libelle + ", date_debut=" + date_debut + ", date_fin="
					+ date_fin + "]";
		}
	    
	    

}

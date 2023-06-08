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
@Table(name = "niveau", schema = PreinscriptionConstants.SCHEMA)
public class Niveau extends AbstractAuditingEntity  {
	
	 public Niveau() {
		
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
	    @Column(name = "id_niveau")
	    private String id;
	 
	    @Column(name = "libelle_niveau", nullable = false)
	    private String libelle;
	    
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
		

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		@Override
		public String toString() {
			return "Niveau [id=" + id + ", libelle=" + libelle + "]";
		}
	    
	    

}

package com.id2real.gevisco.preinscription.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.preinscription.utils.PreinscriptionConstants;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "preinscription", schema = PreinscriptionConstants.SCHEMA)
public class Preinscription extends AbstractAuditingEntity  {
	
	
	 public Preinscription() {
		
	}

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
	    @Column(name = "id_preinscription")
	    private String id;
	 
	    @Column(name = "frais_etude_dossier", nullable = false)
	    private float frais;
	    
	    @Column(name = "classe_anterieure", nullable = false)
	    private String classe_anterieure;
	    
	    @Column(name = "ecole_provenance", nullable = false)
	    private String ecole_provenance;
	    
	    @Column(name = "date_preinscription", nullable = true)
	    private long date_preinscription;
	    
	    @Column(name = "moyenne_obtenue", nullable = false)
	    private float moyenne_obtenue;
	    
	    @Column(name = "bulletin1", nullable = true)
	    private String bulletin1;
	    
	    @Column(name = "bulletin2", nullable = true)
	    private String bulletin2;
	    
	    
	    @Column(name = "bulletin3", nullable = true)
	    private String bulletin3;
	    
	    @Column(name = "etat", nullable = true)
	    private int etat;
	    
	    @Column(name = "active", nullable = true)
	    private boolean active;
	    
	    @ManyToOne
	    @JoinColumn(name = "niveau_id", referencedColumnName = "id_niveau")
	    private Niveau niveau;
	    
	    @ManyToOne
	    @JoinColumn(name = "annee_id", referencedColumnName = "id_annee")
	    private Annee_scolaire annee;
	    
	    @ManyToOne
	    @JoinColumn(name = "eleve_id", referencedColumnName = "id_eleve")
	    private Eleve eleve;

		
	    

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




		public String getClasse_anterieure() {
			return classe_anterieure;
		}


		public void setClasse_anterieure(String classe_anterieure) {
			this.classe_anterieure = classe_anterieure;
		}




		public String getEcole_provenance() {
			return ecole_provenance;
		}

		public void setEcole_provenance(String ecole_provenance) {
			this.ecole_provenance = ecole_provenance;
		}




		public long getDate_preinscription() {
			return date_preinscription;
		}

		public void setDate_preinscription(long date_preinscription) {
			this.date_preinscription = date_preinscription;
		}




		public float getMoyenne_obtenue() {
			return moyenne_obtenue;
		}

		public void setMoyenne_obtenue(float moyenne_obtenue) {
			this.moyenne_obtenue = moyenne_obtenue;
		}




		public Niveau getNiveau() {
			return niveau;
		}

		public void setNiveau(Niveau niveau) {
			this.niveau = niveau;
		}




		public Annee_scolaire getAnnee() {
			return annee;
		}

		public void setAnnee(Annee_scolaire annee) {
			this.annee = annee;
		}




		public Eleve getEleve() {
			return eleve;
		}

		public void setEleve(Eleve eleve) {
			this.eleve = eleve;
		}


		public int getEtat() {
			return etat;
		}


		public void setEtat(int etat) {
			this.etat = etat;
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


		public boolean isActive() {
			return active;
		}


		public void setActive(boolean active) {
			this.active = active;
		}


		@Override
		public String toString() {
			return "Preinscription [id=" + id + ", frais=" + frais + ", classe_anterieure=" + classe_anterieure
					+ ", ecole_provenance=" + ecole_provenance + ", moyenne_obtenue=" + moyenne_obtenue + ", niveau="
					+ niveau + ", annee=" + annee + ", eleve=" + eleve + "]";
		}
	    

	    
}

package com.id2real.gevisco.evaluation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.id2real.gevisco.evaluation.utils.EvaluationConstants;
import com.id2real.socle.utils.audit.AbstractAuditingEntity;

@Entity
@Table(name = "detail_bulletin", schema = EvaluationConstants.SCHEMA)
public class Detail_bulletin extends AbstractAuditingEntity  {

	public Detail_bulletin() {

	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id_detail_bulletin")
    private String id;
    
    @Column(name = "moyenne")
    private float moyenne;
	
    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "id_note")
    private Note note;
    
    @ManyToOne
    @JoinColumn(name = "bulletin_id", referencedColumnName = "id_bulletin")
    private Bulletin bulletin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

    

}

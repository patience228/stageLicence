package com.id2real.gevisco.evaluation.dtos;


public class DetailBulletinDTO {

	private String id;
	
	private float moyenne;
	
	private NoteDTO note;
	
	private BulletinDTO bulletin;

	public DetailBulletinDTO() {

	}

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

	public NoteDTO getNote() {
		return note;
	}

	public void setNote(NoteDTO note) {
		this.note = note;
	}

	public BulletinDTO getBulletin() {
		return bulletin;
	}

	public void setBulletin(BulletinDTO bulletin) {
		this.bulletin = bulletin;
	}
	
	
	
}

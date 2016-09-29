package fr.uha.miage.vod.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Avis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int note;
	private String commentaire;
	
	@ManyToOne
	private Film film;
	
	@ManyToOne
	private Utilisateur utilisateur;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}

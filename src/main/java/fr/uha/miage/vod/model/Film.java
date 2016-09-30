package fr.uha.miage.vod.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String titre;
	private String synopsis;
	private String duree;
	private String jaquette;
	private int sortie;
	private String video;
	private String langue;
	
	@ManyToOne
	private Realisateur realisateur;
	
	@ManyToMany
	private Set<Acteur> acteurs = new HashSet<Acteur>(); 
	
	@ManyToOne
	private Pays pays;
	
	@ManyToMany
	private Set<Categorie> categories = new HashSet<Categorie>();
	
	@OneToMany(mappedBy="film")
	private Set<Avis> avis = new HashSet<Avis>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getJaquette() {
		return jaquette;
	}
	public void setJaquette(String jaquette) {
		this.jaquette = jaquette;
	}
	public int getSortie() {
		return sortie;
	}
	public void setSortie(int sortie) {
		this.sortie = sortie;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	
}

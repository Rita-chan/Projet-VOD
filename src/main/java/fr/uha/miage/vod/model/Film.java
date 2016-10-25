package fr.uha.miage.vod.model;

import java.util.ArrayList;
import java.util.List;

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
	private String version;
	
	@ManyToOne
	private Realisateur realisateur;
	
	@ManyToMany
	private List<Acteur> acteurs = new ArrayList<Acteur>(); 
	
	@ManyToOne
	private Pays pays;
	
	@ManyToMany
	private List<Categorie> categories = new ArrayList<Categorie>();
	
	@OneToMany(mappedBy="film")
	private List<Avis> avis = new ArrayList<Avis>();
	
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
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Realisateur getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}
	public List<Acteur> getActeurs() {
		return acteurs;
	}
	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}
	public List<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
	public List<Avis> getAvis() {
		return avis;
	}
	public void setAvis(List<Avis> avis) {
		this.avis = avis;
	}
	public void ajouterAvis(Avis avi)
	{
		//this.avis.add(avi);
	}
	
}

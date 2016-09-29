package fr.uha.miage.vod.model;

public class Film {
	private long id;
	private String titre;
	private String synopsis;
	private String duree;
	private String jaquette;
	private int sortie;
	private String video;
	private String langue;
	
	
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

package fr.uha.miage.vod.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categorie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String intitule;
	
	@ManyToMany(mappedBy="categories")
	private List<Film> films = new ArrayList<Film>();

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public void ajouterFilm(Film film)
	{
		this.films.add(film);
	}

	public void supprimerFilm(Film film)
	{
		this.films.remove(film);
	}
}

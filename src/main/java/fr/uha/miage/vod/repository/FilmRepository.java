package fr.uha.miage.vod.repository;

import org.springframework.data.repository.CrudRepository;

import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long>{
	public Iterable<Film> findByCategories(Categorie categorie);
}

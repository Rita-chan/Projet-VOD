package fr.uha.miage.vod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.uha.miage.vod.model.Acteur;
import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Film;
import fr.uha.miage.vod.model.Pays;
import fr.uha.miage.vod.model.Realisateur;
import fr.uha.miage.vod.repository.ActeurRepository;
import fr.uha.miage.vod.repository.CategorieRepository;
import fr.uha.miage.vod.repository.FilmRepository;
import fr.uha.miage.vod.repository.PaysRepository;
import fr.uha.miage.vod.repository.RealisateurRepository;

@Controller
public class RechercheController {

	@Autowired
	ActeurRepository acteurrepository;
	
	@Autowired
	CategorieRepository categorierepository;
	
	@Autowired
	PaysRepository paysrepository;
	
	@Autowired
	RealisateurRepository realisateurrepository;
	
	@Autowired
	private FilmRepository filmrepository;
	
	//Recherche par acteur
	@GetMapping("/paracteur")
	public String rechercherparacteur(Model model) {

		Iterable<Acteur> listeActeur = acteurrepository.findAll();

		model.addAttribute("acteurs", listeActeur);
		return "rechacteur";
	}
	
	
	//Liste de films par acteur
	@PostMapping("/paracteur")
	public String rechercherparacteurliste(Model model, @RequestParam("select") Acteur acteur) {

		model.addAttribute("acteur", acteur);
		
		return "filmsparacteur";
	}
	
	//Recherche par categorie
	@GetMapping("/parcategorie")
	public String rechercherparcategorie(Model model) {

		Iterable<Categorie> listeCategorie = categorierepository.findAll();

		model.addAttribute("categories", listeCategorie);
		return "rechcategorie";
	}

	//Liste de films par categorie
	@PostMapping("/parcategorie")
	public String rechercherparcategorieliste(Model model, @RequestParam("select") Categorie categorie) {

		model.addAttribute("categorie", categorie);
		
		return "filmsparcat";
	}
	
	//Recherche par pays
	@GetMapping("/parpays")
	public String rechercherparpays(Model model) {

		Iterable<Pays> listePays = paysrepository.findAll();

		model.addAttribute("payss", listePays);
		return "rechpays";
	}

	//Liste de films par pays
	@PostMapping("/parpays")
	public String rechercherparpaysliste(Model model, @RequestParam("select") Pays pays) {

		model.addAttribute("pays", pays);
		
		return "filmsparpays";
	}
	
	//Recherche par realisateur
	@GetMapping("/parrealisateur")
	public String rechercherparrealisateur(Model model) {

		Iterable<Realisateur> listeRealisateur = realisateurrepository.findAll();

		model.addAttribute("realisateurs", listeRealisateur);
		return "rechrealisateur";
	}

	//Liste de films par realisateur
	@PostMapping("/parrealisateur")
	public String rechercherparrealisateurliste(Model model, @RequestParam("select") Realisateur realisateur) {

		model.addAttribute("realisateur", realisateur);
		
		return "filmsparreal";
	}
	
	//Recherche par titre
		@GetMapping("/partitre")
		public String rechercherpartitre(Model model) {

			Iterable<Film> listeFilm = filmrepository.findAll();

			model.addAttribute("films", listeFilm);
			return "rechtitre";
		}

		//Liste de films par titre
		@PostMapping("/partitre")
		public String rechercherpartitreliste(Model model, @RequestParam("select") Film film) {
			long id = film.getId();
			model.addAttribute("film", film);
			
			return "filmspartitre";
		}
	
}

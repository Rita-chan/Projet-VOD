package fr.uha.miage.vod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Film;
import fr.uha.miage.vod.model.Utilisateur;
import fr.uha.miage.vod.repository.CategorieRepository;
import fr.uha.miage.vod.repository.FilmRepository;
import fr.uha.miage.vod.repository.UtilisateurRepository;

@Controller
public class WebController {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping("/")
	public String home(Model model){
		
		Iterable<Categorie> listeCategorie = categorieRepository.findAll();
		model.addAttribute("categories", listeCategorie);
			
		
		return "index";
	}
	
	@GetMapping("/adminindex")
	public String adminindex(Model model){
		
		/*
		 * Gestion des statistiques
		 */
		
		Iterable<Categorie> listeCategorie = categorieRepository.findAll();
		int nbCategorie = ((List<Categorie>) listeCategorie).size();	
		model.addAttribute("nbCategorie", nbCategorie);
		model.addAttribute("categories", listeCategorie);
		
		Iterable<Utilisateur> listeUtilisateur = utilisateurRepository.findAll();
		int nbUtilisateur = ((List<Utilisateur>) listeUtilisateur).size();	
		model.addAttribute("nbUtilisateur", nbUtilisateur);
		
		Iterable<Film> listeFilm = filmRepository.findAll();
		int nbFilm = ((List<Film>) listeFilm).size();	
		model.addAttribute("nbFilm", nbFilm);
		
		return "adminindex";
	}
	

}

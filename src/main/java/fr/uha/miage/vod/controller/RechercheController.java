package fr.uha.miage.vod.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.uha.miage.vod.model.Acteur;
import fr.uha.miage.vod.repository.ActeurRepository;
import fr.uha.miage.vod.repository.FilmRepository;

@Controller
public class RechercheController {

	@Autowired
	ActeurRepository acteurrepository;
	
	@Autowired
	private FilmRepository filmRepository;

	@GetMapping("/paracteur")
	public String rechercherparacteur(Model model) {
		Acteur act = new Acteur();
		model.addAttribute("acteur", act);
		Iterable<Acteur> listeActeur = acteurrepository.findAll();

		model.addAttribute("acteurs", listeActeur);
		return "rechacteur";
	}

	@PostMapping("/paracteur")
	public String rechercherparacteurliste(Model model) {
		return "rechfilms";
	}
	
}

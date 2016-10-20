package fr.uha.miage.vod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Film;
import fr.uha.miage.vod.repository.CategorieRepository;
import fr.uha.miage.vod.repository.FilmRepository;

@Controller
public class WebController {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@GetMapping("/")
	public String home(Model model){
		
		Iterable<Categorie> listeCategorie = categorieRepository.findAll();
		model.addAttribute("categories", listeCategorie);
			
		
		return "index";
	}
	
	@GetMapping("/adminindex")
	public String adminindex(){
		return "adminindex";
	}
	

}

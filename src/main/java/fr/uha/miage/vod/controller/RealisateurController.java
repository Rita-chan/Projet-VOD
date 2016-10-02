package fr.uha.miage.vod.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.uha.miage.vod.model.Realisateur;
import fr.uha.miage.vod.repository.RealisateurRepository;


@Controller
public class RealisateurController {

	@Autowired
	private RealisateurRepository realisateurRepository;
	
	//Affiche le formulaire de création d'un réalisateur
	@GetMapping("/realisateurcreer")
	public String realisateurcreerform(Model model){
		model.addAttribute("realisateur", new Realisateur());
		return "realisateurcreer";
	}
	//Enregistre le réalisateur créé, en verifiant qu'il corresponde aux critères
	@PostMapping("/realisateurcreer")
	public String realisateurcreer(@Valid Realisateur realisateur, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "realisateurcreer";
		
		realisateurRepository.save(realisateur);
		
		return "redirect:/realisateur";
	}
		
	//Affiche le formulaire d'édition d'un réalisateur		
	@GetMapping("/realisateurmodifier/{id}")
	public String realisateurmodifierform(@PathVariable("id") Long id, Model model){
		Realisateur realisateur = realisateurRepository.findOne(id);
		model.addAttribute("realisateur", realisateur);
		return "realisateurmodifier";
	}
	//Enregistre le réalisateur modifié, en vérifiant qu'il corresponde aux critères
	@PostMapping("/realisateurmodifier")
	public String realisateurmodifier(@Valid Realisateur realisateur, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "realisateurmodifier";
		
		realisateurRepository.save(realisateur);
		return "redirect:/realisateur";
	}

	//Affiche la liste des réalisateurs
	@GetMapping("/realisateur")
	public String realisateur(Model model){
		Iterable<Realisateur> liste = realisateurRepository.findAll();
		model.addAttribute("realisateurs", liste);
		return "realisateur";
	}
	
	//Supprime le realisateur sélectionné
	@GetMapping("/realisateursupprimer/{id}")
	public String realisateursupprimer(@PathVariable("id") Long id){
		realisateurRepository.delete(id);
		return "redirect:/realisateur";
	}
}

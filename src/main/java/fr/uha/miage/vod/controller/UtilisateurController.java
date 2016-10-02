package fr.uha.miage.vod.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.uha.miage.vod.model.Utilisateur;
import fr.uha.miage.vod.repository.UtilisateurRepository;


@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	//Affiche le formulaire de création d'un utilisateur
	@GetMapping("/utilisateurcreer")
	public String utilisateurcreerform(Model model){
		model.addAttribute("utilisateur", new Utilisateur());
		return "utilisateurcreer";
	}
	//Enregistre l'utilisateur créé, en verifiant qu'il corresponde aux critères
	@PostMapping("/utilisateurcreer")
	public String utilisateurcreer(@Valid Utilisateur utilisateur, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "utilisateurcreer";
		
		utilisateurRepository.save(utilisateur);
		
		return "redirect:/utilisateur";
	}
		
	//Affiche le formulaire d'édition d'un utilisateur		
	@GetMapping("/utilisateurmodifier/{id}")
	public String utilisateurmodifierform(@PathVariable("id") Long id, Model model){
		Utilisateur utilisateur = utilisateurRepository.findOne(id);
		model.addAttribute("utilisateur", utilisateur);
		return "utilisateurmodifier";
	}
	//Enregistre l'utilisateur modifié, en vérifiant qu'il corresponde aux critères
	@PostMapping("/utilisateurmodifier")
	public String utilisateurmodifier(@Valid Utilisateur utilisateur, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "utilisateurmodifier";
		
		utilisateurRepository.save(utilisateur);
		return "redirect:/utilisateur";
	}

	//Affiche la liste des utilisateurs
	@GetMapping("/utilisateur")
	public String utilisateur(Model model){
		Iterable<Utilisateur> liste = utilisateurRepository.findAll();
		model.addAttribute("utilisateurs", liste);
		return "utilisateur";
	}
	
	//Supprime l'Utilisateur sélectionné
	@GetMapping("/utilisateursupprimer/{id}")
	public String utilisateursupprimer(@PathVariable("id") Long id){
		utilisateurRepository.delete(id);
		return "redirect:/utilisateur";
	}
}

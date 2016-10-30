package fr.uha.miage.vod.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.uha.miage.vod.model.Acteur;
import fr.uha.miage.vod.repository.ActeurRepository;

@Controller
public class ActeurController {

	@Autowired
	private ActeurRepository acteurRepository;

	// Affiche le formulaire de création d'un acteur
	@GetMapping("/acteurcreer")
	public String acteurcreerform(Model model, HttpSession session) {
		
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			model.addAttribute("acteur", new Acteur());
			Iterable<Acteur> liste = acteurRepository.findAll();
			model.addAttribute("acteurs", liste);
			return "acteurcreer";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre l'acteur créé, en verifiant qu'il corresponde aux critères
	@PostMapping("/acteurcreer")
	public String acteurcreer(@Valid Acteur acteur, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "acteurcreer";

		acteurRepository.save(acteur);

		return "redirect:/acteur";
	}

	// Affiche le formulaire d'édition d'un acteur
	@GetMapping("/acteurmodifier/{id}")
	public String acteurmodifierform(@PathVariable("id") Long id, Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Acteur acteur = acteurRepository.findOne(id);
			model.addAttribute("acteur", acteur);
			Iterable<Acteur> liste = acteurRepository.findAll();
			model.addAttribute("acteurs", liste);
			return "acteurmodifier";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre l'acteur modifié, en vérifiant qu'il corresponde aux critères
	@PostMapping("/acteurmodifier")
	public String acteurmodifier(@Valid Acteur acteur, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "acteurmodifier";

		acteurRepository.save(acteur);
		return "redirect:/acteur";
	}

	// Affiche la liste des acteurs
	@GetMapping("/acteur")
	public String acteur(Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Iterable<Acteur> liste = acteurRepository.findAll();
			model.addAttribute("acteurs", liste);
			return "acteur";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Supprime l'acteur sélectionné
	@GetMapping("/acteursupprimer/{id}")
	public String acteursupprimer(@PathVariable("id") Long id, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			acteurRepository.delete(id);
			return "redirect:/acteur";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}
}

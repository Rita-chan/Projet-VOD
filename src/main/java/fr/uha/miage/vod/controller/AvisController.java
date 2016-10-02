package fr.uha.miage.vod.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.uha.miage.vod.model.Avis;
import fr.uha.miage.vod.repository.AvisRepository;

@Controller
public class AvisController {

	@Autowired
	private AvisRepository avisRepository;

	// Affiche le formulaire de création d'un avis
	@GetMapping("/aviscreer")
	public String aviscreerform(Model model) {
		model.addAttribute("avis", new Avis());
		return "aviscreer";
	}

	// Enregistre l'avis créé, en verifiant qu'il corresponde aux critères
	@PostMapping("/aviscreer")
	public String aviscreer(@Valid Avis avis, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "aviscreer";

		avisRepository.save(avis);

		return "redirect:/avis";
	}

	// Affiche le formulaire d'édition d'un avis
	@GetMapping("/avismodifier/{id}")
	public String avismodifierform(@PathVariable("id") Long id, Model model) {
		Avis avis = avisRepository.findOne(id);
		model.addAttribute("avis", avis);
		return "avismodifier";
	}

	// Enregistre l'avis modifié en vérifiant qu'il corresponde aux critères
	@PostMapping("/avismodifier")
	public String avismodifier(@Valid Avis avis, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "avismodifier";

		avisRepository.save(avis);
		return "redirect:/avis";
	}

	// Affiche la liste des avis
	@GetMapping("/avis")
	public String acteur(Model model) {
		Iterable<Avis> liste = avisRepository.findAll();
		model.addAttribute("aviss", liste);
		return "avis";
	}

	// Supprime l'acteur sélectionné
	@GetMapping("/avissupprimer/{id}")
	public String avissupprimer(@PathVariable("id") Long id) {
		avisRepository.delete(id);
		return "redirect:/avis";
	}
}

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

import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.repository.CategorieRepository;

@Controller
public class CategorieController {

	@Autowired
	private CategorieRepository categorieRepository;

	// Affiche le formulaire de création d'une catégorie
	@GetMapping("/categoriecreer")
	public String categoriecreerform(Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			model.addAttribute("categorie", new Categorie());
			Iterable<Categorie> liste = categorieRepository.findAll();
			model.addAttribute("categories", liste);
			return "categoriecreer";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre la catégorie créée, en verifiant qu'elle corresponde aux
	// critères
	@PostMapping("/categoriecreer")
	public String categoriecreer(@Valid Categorie categorie, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "categoriecreer";

		categorieRepository.save(categorie);

		return "redirect:/categorie";
	}

	// Affiche le formulaire d'édition d'une catégorie
	@GetMapping("/categoriemodifier/{id}")
	public String categoriemodifierform(@PathVariable("id") Long id, Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Categorie categorie = categorieRepository.findOne(id);
			model.addAttribute("categorie", categorie);
			Iterable<Categorie> liste = categorieRepository.findAll();
			model.addAttribute("categories", liste);
			return "categoriemodifier";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre la catégorie modifiée, en vérifiant qu'elle corresponde aux
	// critères
	@PostMapping("/categoriemodifier")
	public String categoriemodifier(@Valid Categorie categorie, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "categoriemodifier";

		categorieRepository.save(categorie);
		return "redirect:/categorie";
	}

	// Affiche la liste des catégories
	@GetMapping("/categorie")
	public String categorie(Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Iterable<Categorie> liste = categorieRepository.findAll();
			model.addAttribute("categories", liste);
			return "categorie";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Supprime la catégorie sélectionnée
	@GetMapping("/categoriesupprimer/{id}")
	public String categoriesupprimer(@PathVariable("id") Long id, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			categorieRepository.delete(id);
			return "redirect:/categorie";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}
}

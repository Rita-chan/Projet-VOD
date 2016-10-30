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

import fr.uha.miage.vod.model.Pays;
import fr.uha.miage.vod.repository.PaysRepository;

@Controller
public class PaysController {

	@Autowired
	private PaysRepository paysRepository;

	// Affiche le formulaire de création d'un pays
	@GetMapping("/payscreer")
	public String payscreerform(Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			model.addAttribute("pays", new Pays());
			Iterable<Pays> liste = paysRepository.findAll();
			model.addAttribute("payss", liste);
			return "payscreer";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre le pays créé, en verifiant qu'il corresponde aux critères
	@PostMapping("/payscreer")
	public String payscreer(@Valid Pays pays, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "payscreer";

		paysRepository.save(pays);

		return "redirect:/pays";
	}

	// Affiche le formulaire d'édition d'un pays
	@GetMapping("/paysmodifier/{id}")
	public String paysmodifierform(@PathVariable("id") Long id, Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Pays pays = paysRepository.findOne(id);
			model.addAttribute("pays", pays);
			Iterable<Pays> liste = paysRepository.findAll();
			model.addAttribute("payss", liste);
			return "paysmodifier";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre le pays modifié, en vérifiant qu'il corresponde aux critères
	@PostMapping("/paysmodifier")
	public String paysmodifier(@Valid Pays pays, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "paysmodifier";

		paysRepository.save(pays);
		return "redirect:/pays";
	}

	// Affiche la liste des pays
	@GetMapping("/pays")
	public String pays(Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Iterable<Pays> liste = paysRepository.findAll();
			model.addAttribute("payss", liste);
			return "pays";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Supprime le pays sélectionné
	@GetMapping("/payssupprimer/{id}")
	public String payssupprimer(@PathVariable("id") Long id, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			paysRepository.delete(id);
			return "redirect:/pays";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}
}
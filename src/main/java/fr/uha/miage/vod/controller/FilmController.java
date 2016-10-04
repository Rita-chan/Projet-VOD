package fr.uha.miage.vod.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.uha.miage.vod.model.Film;
import fr.uha.miage.vod.repository.FilmRepository;

@Controller
public class FilmController {
	@Autowired
	private FilmRepository filmRepository;

	// Affiche le formulaire de création d'un film
	@GetMapping("/filmcreer")
	public String filmcreerform(Model model) {
		model.addAttribute("film", new Film());
		return "filmcreer";
	}

	// Enregistre le film créé, en verifiant qu'il corresponde aux
	// critères
	@PostMapping("/filmcreer")
	public String filmcreer(@Valid Film film, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "filmcreer";

		filmRepository.save(film);

		return "redirect:/film";
	}

	// Affiche le formulaire d'édition d'un film
	@GetMapping("/filmmodifier/{id}")
	public String filmmodifierform(@PathVariable("id") Long id, Model model) {
		Film film = filmRepository.findOne(id);
		model.addAttribute("film", film);
		return "filmmodifier";
	}

	// Enregistre le film modifiée, en vérifiant qu'il corresponde aux
	// critères
	@PostMapping("/filmmodifier")
	public String filmmodifier(@Valid Film film, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "filmmodifier";

		filmRepository.save(film);
		return "redirect:/film";
	}

	// Affiche la liste des films
	@GetMapping("/film")
	public String film(Model model) {
		Iterable<Film> liste = filmRepository.findAll();
		model.addAttribute("films", liste);
		return "film";
	}

	// Supprime le film sélectionné
	@GetMapping("/filmsupprimer/{id}")
	public String filmsupprimer(@PathVariable("id") Long id) {
		filmRepository.delete(id);
		return "redirect:/film";
	}
}

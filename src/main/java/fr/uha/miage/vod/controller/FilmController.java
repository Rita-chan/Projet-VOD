package fr.uha.miage.vod.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.uha.miage.vod.file.StorageFileNotFoundException;
import fr.uha.miage.vod.file.StorageService;
import fr.uha.miage.vod.model.Acteur;
import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Film;
import fr.uha.miage.vod.model.Pays;
import fr.uha.miage.vod.model.Realisateur;
import fr.uha.miage.vod.repository.ActeurRepository;
import fr.uha.miage.vod.repository.CategorieRepository;
import fr.uha.miage.vod.repository.FilmRepository;
import fr.uha.miage.vod.repository.PaysRepository;
import fr.uha.miage.vod.repository.RealisateurRepository;

@Controller
public class FilmController {

	private final StorageService storageService;

	@Autowired
	public FilmController(StorageService storageService) {
		this.storageService = storageService;
	}

	// Création des repository
	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private ActeurRepository acteurRepository;

	@Autowired
	private PaysRepository paysRepository;

	@Autowired
	private RealisateurRepository realisateurRepository;

	// Affiche le formulaire de création d'un film
	@GetMapping("/filmcreer")
	public String filmcreerform(Model model) {
		model.addAttribute("film", new Film());

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FilmController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		List<Categorie> listeCategories = (List<Categorie>) categorieRepository.findAll();
		model.addAttribute("listeCategories", listeCategories);

		List<Acteur> listeActeurs = (List<Acteur>) acteurRepository.findAll();
		model.addAttribute("listeActeurs", listeActeurs);

		List<Pays> listePayss = (List<Pays>) paysRepository.findAll();
		model.addAttribute("listePayss", listePayss);

		List<Realisateur> listeRealisateurs = (List<Realisateur>) realisateurRepository.findAll();
		model.addAttribute("listeRealisateurs", listeRealisateurs);

		return "filmcreer";
	}

	// Enregistre le film créé, en verifiant qu'il corresponde aux
	// critères
	@PostMapping("/filmcreer")
	public String filmcreer(@Valid Film film, BindingResult bindingResult, @RequestParam("file") MultipartFile jaquette, @RequestParam("file1") MultipartFile video,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors())
			return "filmcreer";

		film.setJaquette(jaquette.getOriginalFilename());		
		film.setVideo(video.getOriginalFilename());
		filmRepository.save(film);

		for (Acteur acteur : film.getActeurs()) {
			acteur.ajouterFilm(film);
			acteurRepository.save(acteur);
		}

		for (Categorie categorie : film.getCategories()) {
			categorie.ajouterFilm(film);
			categorieRepository.save(categorie);
		}

		film.getPays().ajouterFilm(film);
		paysRepository.save(film.getPays());

		film.getRealisateur().ajouterFilm(film);
		realisateurRepository.save(film.getRealisateur());

		storageService.store(jaquette);
		redirectAttributes.addFlashAttribute("message", "La jaquette a bien été uploadée.");

		
		storageService.store(video);
		//redirectAttributes.addFlashAttribute("message", "La video a bien été uploadée.");

		return "redirect:/film";
	}

	// Affiche le formulaire d'édition d'un film
	@GetMapping("/filmmodifier/{id}")
	public String filmmodifierform(@PathVariable("id") Long id, Model model) {
		Film film = filmRepository.findOne(id);
		model.addAttribute("film", film);

		List<Categorie> listeCategories = (List<Categorie>) categorieRepository.findAll();
		model.addAttribute("listeCategories", listeCategories);

		List<Acteur> listeActeurs = (List<Acteur>) acteurRepository.findAll();
		model.addAttribute("listeActeurs", listeActeurs);

		List<Pays> listePayss = (List<Pays>) paysRepository.findAll();
		model.addAttribute("listePayss", listePayss);

		List<Realisateur> listeRealisateurs = (List<Realisateur>) realisateurRepository.findAll();
		model.addAttribute("listeRealisateurs", listeRealisateurs);

		return "filmmodifier";
	}

	// Enregistre le film modifiée, en vérifiant qu'il corresponde aux
	// critères
	@PostMapping("/filmmodifier")
	public String filmmodifier(@Valid Film film, BindingResult bindingResult) {
		/*
		 * f (bindingResult.hasErrors()) return "filmmodifier";
		 */
		filmsupprimer(film.getId());
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
	
	// Visionne le film sélectionné
		@GetMapping("/filmvisionner/{id}")
		public String filmvisionner(Model model, @PathVariable("id") Long id) {
			Film film = filmRepository.findOne(id);
			model.addAttribute("film", film);
			return "filmvisionner";
		}
		

	// Supprime le film sélectionné
	@GetMapping("/filmsupprimer/{id}")
	public String filmsupprimer(@PathVariable("id") Long id) {

		Film film = filmRepository.findOne(id);
		for (Acteur acteur : film.getActeurs()) {
			acteur.supprimerFilm(film);
			acteurRepository.save(acteur);
		}

		for (Categorie categorie : film.getCategories()) {
			categorie.supprimerFilm(film);
			categorieRepository.save(categorie);
		}

		film.getPays().supprimerFilm(film);
		paysRepository.save(film.getPays());

		film.getRealisateur().supprimerFilm(film);
		realisateurRepository.save(film.getRealisateur());

		filmRepository.delete(id);
		return "redirect:/film";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}

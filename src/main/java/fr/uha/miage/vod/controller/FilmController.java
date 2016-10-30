package fr.uha.miage.vod.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import fr.uha.miage.vod.Application;
import fr.uha.miage.vod.file.StorageFileNotFoundException;
import fr.uha.miage.vod.file.StorageService;
import fr.uha.miage.vod.model.Acteur;
import fr.uha.miage.vod.model.Avis;
import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Film;
import fr.uha.miage.vod.model.Pays;
import fr.uha.miage.vod.model.Realisateur;
import fr.uha.miage.vod.model.Utilisateur;
import fr.uha.miage.vod.repository.ActeurRepository;
import fr.uha.miage.vod.repository.AvisRepository;
import fr.uha.miage.vod.repository.CategorieRepository;
import fr.uha.miage.vod.repository.FilmRepository;
import fr.uha.miage.vod.repository.PaysRepository;
import fr.uha.miage.vod.repository.RealisateurRepository;
import fr.uha.miage.vod.repository.UtilisateurRepository;

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
	
	@Autowired
	private AvisRepository avisRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	// Affiche le formulaire de création d'un film
	@GetMapping("/filmcreer")
	public String filmcreerform(Model model, HttpSession session) {
		
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
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

		Iterable<Film> liste = filmRepository.findAll();
		model.addAttribute("films", liste);

		return "filmcreer";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre le film créé, en verifiant qu'il corresponde aux
	// critères
	@PostMapping("/filmcreer")
	public String filmcreer(@Valid Film film, BindingResult bindingResult, @RequestParam("file") MultipartFile jaquette,
			@RequestParam("file1") MultipartFile video, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors())
			return "filmcreer";

		film.setNote(-1);
		
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
		// redirectAttributes.addFlashAttribute("message", "La video a bien été
		// uploadée.");

		return "redirect:/film";
	}

	// Affiche le formulaire d'édition d'un film
	@GetMapping("/filmmodifier/{id}")
	public String filmmodifierform(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
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

		Iterable<Film> liste = filmRepository.findAll();
		model.addAttribute("films", liste);

		return "filmmodifier";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Enregistre le film modifiée, en vérifiant qu'il corresponde aux
	// critères
	@PostMapping("/filmmodifier")
	public String filmmodifier(@Valid Film film, BindingResult bindingResult, HttpSession session) {
		/*
		 * f (bindingResult.hasErrors()) return "filmmodifier";
		 */
		filmsupprimer(film.getId(), session);
		filmRepository.save(film);
		return "redirect:/film";
	}

	// Affiche la liste des films
	@GetMapping("/film")
	public String film(Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
		Iterable<Film> liste = filmRepository.findAll();
		model.addAttribute("films", liste);
		return "film";
		}
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Visionne le film sélectionné
	@GetMapping("/filmvisionner/{id}")
	public String filmvisionner(Model model, @PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("id") != null) {
		Film film = filmRepository.findOne(id);
		model.addAttribute("film", film);
		return "filmvisionner"; 
		}
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Supprime le film sélectionné
	@GetMapping("/filmsupprimer/{id}")
	public String filmsupprimer(@PathVariable("id") Long id, HttpSession session) {

		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
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
		else if(session.getAttribute("id") != null) {
			return "redirect:/";
		}
		
		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);

	}
	
	@GetMapping("/files/ed.jpg")
	@ResponseBody
	public ResponseEntity<Resource> serveFile1() {

		Resource file = storageService.loadAsResource("ed.jpg");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; \"" + "ed.jpg" + "\"").body(file);

	}
	
	@GetMapping("/files/bbb.png")
	@ResponseBody
	public ResponseEntity<Resource> serveFile2() {

		Resource file = storageService.loadAsResource("bbb.png");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; \"" + "bbb.png" + "\"").body(file);

	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	// Afficher les informations d'un film, permet de creer un avis, affiche les avis
	@GetMapping("/film/{id}")
	public String informationfilm(Model model, @PathVariable("id") Long id, HttpSession session) {
		Film film = filmRepository.findOne(id);
		model.addAttribute("film", film);
		
		Avis avis = new Avis();
		avis.setFilm(film);
		if (session.getAttribute("id") != null)
		{
			long idUtil = (long) session.getAttribute("id");
			Utilisateur util = utilisateurRepository.findOne(idUtil);
			avis.setUtilisateur(util);
		}
		model.addAttribute("avis", avis);
		List<Avis> listeAvis = (List<Avis>) avisRepository.findAll();
		model.addAttribute("listeAvis", listeAvis);
		
		return "filminfo";
	}

	@PostMapping("/filminfo")
	public String informationfilm(@Valid Avis avis, BindingResult bindingResult) {

		avisRepository.save(avis);
		
		Film film = avis.getFilm();
		double note = film.getNote();
		if(note == -1)
		{
			note = 0;
		}
		int nb = film.getAvis().size() -1;
		double moyenne = (note * nb + avis.getNote()) / (nb+1);
		film.ajouterAvis(avis);
		
		//pour avoir deux chiffres après la virgule
		double virgule = moyenne * 100;
		int arrondi = (int)virgule;
		film.setNote((double)arrondi/100);
		
		filmRepository.save(film);

		String redirect = "redirect:/film/" + film.getId();
		
		return redirect;
	}
}

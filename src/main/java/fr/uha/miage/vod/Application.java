package fr.uha.miage.vod;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import fr.uha.miage.vod.file.StorageProperties;
import fr.uha.miage.vod.file.StorageService;
import fr.uha.miage.vod.model.Acteur;
import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Film;
import fr.uha.miage.vod.model.Pays;
import fr.uha.miage.vod.model.Realisateur;
import fr.uha.miage.vod.model.Utilisateur;
import fr.uha.miage.vod.repository.ActeurRepository;
import fr.uha.miage.vod.repository.CategorieRepository;
import fr.uha.miage.vod.repository.FilmRepository;
import fr.uha.miage.vod.repository.PaysRepository;
import fr.uha.miage.vod.repository.RealisateurRepository;
import fr.uha.miage.vod.repository.UtilisateurRepository;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application implements CommandLineRunner {

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
	private UtilisateurRepository utilisateurRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
	
		
		Acteur act1 = new Acteur();
		act1.setNom("Gernandt");
		act1.setPrenom("Tygo");
		Acteur act2= new Acteur();
		act2.setNom("Jansen");
		act2.setPrenom("Cas");
		Acteur act3= new Acteur();
		act3.setNom("Valenza");
		act3.setPrenom("Enrico");
		Acteur act4= new Acteur();
		act4.setNom("Vegd");
		act4.setPrenom("Nathan");
		Acteur act5= new Acteur();
		act5.setNom("Reynish");
		act5.setPrenom("William");
		
		Categorie cat1 = new Categorie();
		cat1.setIntitule("Fantastique");
		Categorie cat2 = new Categorie();
		cat2.setIntitule("Animation");

		
		Pays pays1 = new Pays();
		pays1.setNom("Pays-Bas");

		
		Realisateur real1 = new Realisateur();
		real1.setNom("Roosendaal");
		real1.setPrenom("Ton");
		Realisateur real2 = new Realisateur();
		real2.setNom("Goedegebure");
		real2.setPrenom("sacha");
		
		List <Categorie>cate1 = new ArrayList<Categorie>();
		cate1.add(cat1);
		List <Categorie>cate2 = new ArrayList<Categorie>();
		cate2.add(cat2);
		cate2.add(cat1);

		
		List <Acteur>acte1 = new ArrayList<Acteur>();
		acte1.add(act1);
		acte1.add(act2);
		List <Acteur>acte2 = new ArrayList<Acteur>();
		acte2.add(act2);
		List <Acteur>acte3 = new ArrayList<Acteur>();
		acte3.add(act3);
		acte3.add(act4);
		acte3.add(act5);
		List <Acteur>acte4 = new ArrayList<Acteur>();
		acte4.add(act4);
		List <Acteur>acte5 = new ArrayList<Acteur>();
		acte5.add(act5);
		
		Film film1 = new Film();
		film1.setTitre("Elephants Dream");
		film1.setSynopsis("Elephants Dream est une petite histoire mettant en scène deux personnages, Emo et Proog, dans un monde étrange. En effet, ce dernier est modelé par les pensées des deux personnages.");
		film1.setDuree("11mn");
		film1.setJaquette("ed.jpg");
		film1.setSortie(2006);
		film1.setVideo("ed.mp4");
		film1.setVersion("VF");
		film1.setCategories(cate1);
		//film1.setActeurs(acte2);
		film1.setActeurs(acte1);
		film1.setNote(-1);
		
		film1.setRealisateur(real1);
		film1.setPays(pays1);
			
		
		Film film2 = new Film();
		film2.setTitre("Big Buck Bunny");
		film2.setSynopsis("Dans un monde coloré, tout va pour le mieux: un gros lapin se réveille et sort de sa tanière. Il respire à pleins poumons les essences du printemps et admire les papillons. Seulement, c'est sans compter la méchanceté de trois rongeurs.");
		film2.setDuree("9mn 57s");
		film2.setJaquette("bbb.png");
		film2.setSortie(2016);
		film2.setVideo("bbb.mp4");
		film2.setVersion("VF");
		film2.setCategories(cate2);
		film2.setActeurs(acte2);
		film2.setRealisateur(real2);
		film2.setPays(pays1);
		film2.setNote(-1);
		
		
		acteurRepository.save(act1);
		acteurRepository.save(act2);
		acteurRepository.save(act3);
		acteurRepository.save(act4);
		acteurRepository.save(act5);
		categorieRepository.save(cat1);
		categorieRepository.save(cat2);
		paysRepository.save(pays1);
		realisateurRepository.save(real1);
		realisateurRepository.save(real2);
		filmRepository.save(film1);
		filmRepository.save(film2);
		
		
		act1.ajouterFilm(film1);
		act2.ajouterFilm(film1);
		act3.ajouterFilm(film2);
		act4.ajouterFilm(film2);
		act5.ajouterFilm(film2);


		acteurRepository.save(act1);
		acteurRepository.save(act2);
		acteurRepository.save(act3);
		acteurRepository.save(act4);
		acteurRepository.save(act5);
		
		cat1.ajouterFilm(film1);
		cat1.ajouterFilm(film2);
		cat2.ajouterFilm(film1);
		cat2.ajouterFilm(film2);

		
		categorieRepository.save(cat1);
		categorieRepository.save(cat2);

		
		pays1.ajouterFilm(film1);
		pays1.ajouterFilm(film2);

		
		paysRepository.save(pays1);
		
		real1.ajouterFilm(film1);
		real2.ajouterFilm(film2);
		
		realisateurRepository.save(real1);
		realisateurRepository.save(real2);
	
		
		Utilisateur util1 = new Utilisateur();
		util1.setNom("Util1");
		util1.setPrenom("Nous");
		util1.setLogin("Util1");
		util1.setMail("util1@util1.fr");
    	String hashed1 = BCrypt.hashpw("util1", BCrypt.gensalt(12));
    	util1.setMdp(hashed1);
		
		Utilisateur util2 = new Utilisateur();
		util2.setNom("Util2");
		util2.setPrenom("Vous");
		util2.setLogin("Util2");
		util2.setMail("util2@util2.fr");
		String hashed2 = BCrypt.hashpw("util2", BCrypt.gensalt(12));
    	util2.setMdp(hashed2);
		
		Utilisateur admin = new Utilisateur();
		admin.setNom("Admin");
		admin.setPrenom("Ils");
		admin.setLogin("Admin");
		admin.setMail("admin@admin.fr");
		String hashed3 = BCrypt.hashpw("admin", BCrypt.gensalt(12));
    	admin.setMdp(hashed3);
		admin.setRole(1);
		
		utilisateurRepository.save(util1);
		utilisateurRepository.save(util2);
		utilisateurRepository.save(admin);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}

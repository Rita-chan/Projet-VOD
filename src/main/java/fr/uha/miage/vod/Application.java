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
		act1.setNom("Act1");
		act1.setPrenom("Moi");
		Acteur act2= new Acteur();
		act2.setNom("Act2");
		act2.setPrenom("Toi");
		
		Categorie cat1 = new Categorie();
		cat1.setIntitule("Cat1");
		Categorie cat2 = new Categorie();
		cat2.setIntitule("Cat2");
		
		Pays pays1 = new Pays();
		pays1.setNom("Pays1");
		Pays pays2 = new Pays();
		pays2.setNom("Pays2");
		
		Realisateur real1 = new Realisateur();
		real1.setNom("Real1");
		real1.setPrenom("Lui");
		Realisateur real2 = new Realisateur();
		real2.setNom("Real2");
		real2.setPrenom("Elle");
		
		List <Categorie>cate1 = new ArrayList<Categorie>();
		cate1.add(cat1);
		List <Categorie>cate2 = new ArrayList<Categorie>();
		cate2.add(cat2);
		List <Acteur>acte1 = new ArrayList<Acteur>();
		acte1.add(act1);
		List <Acteur>acte2 = new ArrayList<Acteur>();
		acte2.add(act2);
		
		Film film1 = new Film();
		film1.setTitre("Titre1");
		film1.setSynopsis("Synopsis passionnant du premier film. Synopsis passionnant du premier film. Synopsis passionnant du premier film. Synopsis passionnant du premier film. Synopsis passionnant du premier film.");
		film1.setDuree("20");
		film1.setJaquette("ch1.jpg");
		film1.setSortie(2015);
		film1.setVideo("Là");
		film1.setVersion("VO");
		film1.setCategories(cate1);
		film1.setActeurs(acte1);
		film1.setRealisateur(real1);
		film1.setPays(pays1);
			
		
		Film film2 = new Film();
		film2.setTitre("Titre2");
		film2.setSynopsis("Synopsis passionnant du deuxième film");
		film2.setDuree("30");
		film2.setJaquette("ch2.jpg");
		film2.setSortie(2016);
		film2.setVideo("Là");
		film2.setVersion("VF");
		film2.setCategories(cate2);
		film2.setActeurs(acte2);
		film2.setRealisateur(real2);
		film2.setPays(pays2);
		
		Film film3 = new Film();
		film3.setTitre("Titre3");
		film3.setSynopsis("Synopsis passionnant du troisième film");
		film3.setDuree("50 min");
		film3.setJaquette("ch3.jpg");
		film3.setSortie(2015);
		film3.setVideo("Là");
		film3.setVersion("VO");
		film3.setCategories(cate1);
		film3.setActeurs(acte1);
		film3.setRealisateur(real1);
		film3.setPays(pays1);
			
		
		Film film4 = new Film();
		film4.setTitre("Titre4");
		film4.setSynopsis("Synopsis passionnant du quatrième film");
		film4.setDuree("30 min");
		film4.setJaquette("ch4.jpg");
		film4.setSortie(2016);
		film4.setVideo("Là");
		film4.setVersion("VF");
		film4.setCategories(cate2);
		film4.setActeurs(acte2);
		film4.setRealisateur(real2);
		film4.setPays(pays2);
		
		Film film5 = new Film();
		film5.setTitre("Titre5");
		film5.setSynopsis("Synopsis passionnant du cinquième film");
		film5.setDuree("75 min");
		film5.setJaquette("c1.jpg");
		film5.setSortie(2015);
		film5.setVideo("Là");
		film5.setVersion("VO");
		film5.setCategories(cate1);
		film5.setActeurs(acte1);
		film5.setRealisateur(real1);
		film5.setPays(pays1);
			
		
		Film film6 = new Film();
		film6.setTitre("Titre6");
		film6.setSynopsis("Synopsis passionnant du sixième film");
		film6.setDuree("60 min");
		film6.setJaquette("cc2.jpg");
		film6.setSortie(2016);
		film6.setVideo("Là");
		film6.setVersion("VF");
		film6.setCategories(cate1);
		film6.setActeurs(acte2);
		film6.setRealisateur(real2);
		film6.setPays(pays2);
		
		
		acteurRepository.save(act1);
		acteurRepository.save(act2);
		categorieRepository.save(cat1);
		categorieRepository.save(cat2);
		paysRepository.save(pays1);
		paysRepository.save(pays2);
		realisateurRepository.save(real1);
		realisateurRepository.save(real2);
		filmRepository.save(film1);
		filmRepository.save(film2);
		filmRepository.save(film3);
		filmRepository.save(film4);
		filmRepository.save(film5);
		filmRepository.save(film6);
		
		act1.ajouterFilm(film1);
		act2.ajouterFilm(film2);
		act1.ajouterFilm(film3);
		act2.ajouterFilm(film4);
		act1.ajouterFilm(film5);
		act2.ajouterFilm(film6);
		acteurRepository.save(act1);
		acteurRepository.save(act2);
		
		cat1.ajouterFilm(film1);
		cat2.ajouterFilm(film2);
		cat1.ajouterFilm(film3);
		cat2.ajouterFilm(film4);
		cat1.ajouterFilm(film5);
		cat1.ajouterFilm(film6);
		categorieRepository.save(cat1);
		categorieRepository.save(cat2);
		
		pays1.ajouterFilm(film1);
		pays2.ajouterFilm(film2);
		pays1.ajouterFilm(film3);
		pays2.ajouterFilm(film4);
		pays1.ajouterFilm(film5);
		pays2.ajouterFilm(film6);
		paysRepository.save(pays1);
		paysRepository.save(pays2);
		
		real1.ajouterFilm(film1);
		real2.ajouterFilm(film2);
		real1.ajouterFilm(film3);
		real2.ajouterFilm(film4);
		real1.ajouterFilm(film5);
		real2.ajouterFilm(film6);
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

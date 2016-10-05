package fr.uha.miage.vod;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class Application implements CommandLineRunner{
	
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
		
		Set <Categorie>cate1 = new HashSet<Categorie>();
		cate1.add(cat1);
		Set <Categorie>cate2 = new HashSet<Categorie>();
		cate2.add(cat2);
		Set <Acteur>acte1 = new HashSet<Acteur>();
		acte1.add(act1);
		Set <Acteur>acte2 = new HashSet<Acteur>();
		acte2.add(act2);
		
		Film film1 = new Film();
		film1.setTitre("Titre1");
		film1.setSynopsis("Synopsis passionnant du premier film");
		film1.setDuree("20");
		film1.setJaquette("Ici");
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
		film2.setJaquette("Ici");
		film2.setSortie(2016);
		film2.setVideo("Là");
		film2.setVersion("VF");
		film2.setCategories(cate2);
		film2.setActeurs(acte2);
		film2.setRealisateur(real2);
		film2.setPays(pays2);
		
		
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
		
		act1.ajouterFilm(film1);
		act2.ajouterFilm(film2);
		acteurRepository.save(act1);
		acteurRepository.save(act2);
		
		cat1.ajouterFilm(film1);
		cat2.ajouterFilm(film2);
		categorieRepository.save(cat1);
		categorieRepository.save(cat2);
		
		pays1.ajouterFilm(film1);
		pays2.ajouterFilm(film2);
		paysRepository.save(pays1);
		paysRepository.save(pays2);
		
		real1.ajouterFilm(film1);
		real2.ajouterFilm(film2);
		realisateurRepository.save(real1);
		realisateurRepository.save(real2);
		
		
		
		Utilisateur util1 = new Utilisateur();
		util1.setNom("Util1");
		util1.setPrenom("Nous");
		util1.setLogin("Util1");
		util1.setMail("util1@util1.fr");
		util1.setMdp("mdp");
		
		Utilisateur util2 = new Utilisateur();
		util2.setNom("Util2");
		util2.setPrenom("Vous");
		util2.setLogin("Util2");
		util2.setMail("util2@util2.fr");
		util2.setMdp("mdp");
		
		Utilisateur admin = new Utilisateur();
		admin.setNom("Admin");
		admin.setPrenom("Ils");
		admin.setLogin("Admin");
		admin.setMail("admin@admin.fr");
		admin.setMdp("mdp");
		admin.setRole(1);
		
		utilisateurRepository.save(util1);
		utilisateurRepository.save(util2);
		utilisateurRepository.save(admin);
	}
}

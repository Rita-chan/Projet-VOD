package fr.uha.miage.vod.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.uha.miage.vod.model.Categorie;
import fr.uha.miage.vod.model.Utilisateur;
import fr.uha.miage.vod.repository.UtilisateurRepository;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	/*
	 * Inscription d'un utilisateur Création, modification et suppression d'un
	 * nouvel utilisateur
	 */

	// Affiche le formulaire de création d'un utilisateur
	@GetMapping("/utilisateurcreer")
	public String utilisateurcreerform(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "utilisateurcreer";
	}

	// Enregistre l'utilisateur créé, en verifiant qu'il corresponde aux
	// critères
	@PostMapping("/utilisateurcreer")
	public String utilisateurcreer(@Valid Utilisateur utilisateur, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors())
			return "utilisateurcreer";

		// Vérifie que l'email saisi n'est pas déjà utilisé
		if (utilisateurRepository.findByMail(utilisateur.getMail()) == null) {

			/*
			 * Hashage d'un mot de passe Il est possible d'augmenter la
			 * complexité (et donc le temps de traitement) en passant le
			 * "workfactor" en paramètre ici : 12 La valeur par défaut est 10
			 */
			String hashed = BCrypt.hashpw(utilisateur.getMdp(), BCrypt.gensalt(12));

			// Enregistre le mdp hashé
			utilisateur.setMdp(hashed);

			utilisateur.setRole(1);

			// Sauvegarde l'utilisateur
			utilisateurRepository.save(utilisateur);

			// Récupère l'id de l'utilisateur connecté
			long id = utilisateurRepository.findByMail(utilisateur.getMail()).getId();

			// Récupère le nom et le prénom de l'utilisateur connecté
			String nom = utilisateurRepository.findByMail(utilisateur.getMail()).getNom();
			String prenom = utilisateurRepository.findByMail(utilisateur.getMail()).getPrenom();

			// Met en session l'id de l'utilisateur connecté et du nom et prenom
			session.setAttribute("id", id);
			session.setAttribute("nomprenom", prenom + " " + nom);

			// return "redirect:/utilisateur";
			return "redirect:/";
		} else {
			return "utilisateurcreer";
		}
	}

	// Affiche le formulaire d'édition d'un utilisateur
	@GetMapping("/utilisateurmodifier/{id}")
	public String utilisateurmodifierform(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 0) && (Long)session.getAttribute("id") == id) {
			Utilisateur utilisateur = utilisateurRepository.findOne(id);
			model.addAttribute("utilisateur", utilisateur);
			return "utilisateurmodifier";
		}
		
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Utilisateur utilisateur = utilisateurRepository.findOne(id);
			model.addAttribute("utilisateur", utilisateur);
			return "utilisateurmodifier";
		}
		
		else {
			return "redirect:/";
			
		}
	}

	// Enregistre l'utilisateur modifié, en vérifiant qu'il corresponde aux
	// critères
	@PostMapping("/utilisateurmodifier")
	public String utilisateurmodifier(@Valid Utilisateur utilisateur, BindingResult bindingResult) {
		
			if (bindingResult.hasErrors())
				return "utilisateurmodifier";

			utilisateurRepository.save(utilisateur);
			return "redirect:/utilisateur";
		
	}

	// Affiche la liste des utilisateurs
	@GetMapping("/utilisateur")
	public String utilisateur(Model model, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Iterable<Utilisateur> liste = utilisateurRepository.findAll();
			model.addAttribute("utilisateurs", liste);
			return "utilisateur";
		} else if (session.getAttribute("id") != null) {
			return "redirect:/";
		}

		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Supprime l'utilisateur sélectionné
	@GetMapping("/utilisateursupprimer/{id}")
	public String utilisateursupprimer(@PathVariable("id") Long id, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			utilisateurRepository.delete(id);
			return "redirect:/utilisateur";
		} else if (session.getAttribute("id") != null) {
			return "redirect:/";
		}

		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Donne le rôle d'Admin à l'utilisateur sélectionné
	@GetMapping("/utilisateurattribueradmin/{id}")
	public String utilisateurattribueradmin(@PathVariable("id") Long id, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Utilisateur util = utilisateurRepository.findOne(id);
			util.setRole(1);
			utilisateurRepository.save(util);
			return "redirect:/utilisateur";
		} else if (session.getAttribute("id") != null) {
			return "redirect:/";
		}

		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	// Retire le rôle d'Admin à l'utilisateur sélectionné
	@GetMapping("/utilisateurretireradmin/{id}")
	public String utilisateurretireradmin(@PathVariable("id") Long id, HttpSession session) {
		if ((session.getAttribute("id") != null) && ((int) session.getAttribute("role") == 1)) {
			Utilisateur util = utilisateurRepository.findOne(id);
			util.setRole(0);
			utilisateurRepository.save(util);
			return "redirect:/utilisateur";
		} else if (session.getAttribute("id") != null) {
			return "redirect:/";
		}

		else {
			return "redirect:/utilisateurconnecter";
		}
	}

	/*
	 * Connexion
	 */

	// Affiche le formulaire de connexion
	@GetMapping("/utilisateurconnecter")
	public String utilisateurconnecterform(HttpSession session, Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "utilisateurconnecter";
	}

	// Authentifie et vérifie l'utilisateur
	@PostMapping("/utilisateurconnecter")
	String utilisateurconnecter(HttpSession session, Model model, @Valid Utilisateur utilisateur) {

		// suppression du message d'erreur
		session.removeAttribute("msgerreur");

		/*
		 * //Quand l'administrateur se connecte if
		 * (utilisateurRepository.findByMail(utilisateur.getMail()).getMail() ==
		 * "admin@admin.fr") { System.out.println("admin email"); if
		 * (BCrypt.checkpw(utilisateur.getMdp(),
		 * utilisateurRepository.findByMail(utilisateur.getMail()).getMdp())) {
		 * System.out.println("mdp admin ok"); return "redirect:/"; } }
		 */

		// Quand tous les autres utilisateurs se connectent

		// Cherche si un mail correspond à un utilisateur
		if (utilisateurRepository.findByMail(utilisateur.getMail()) != null) {

			// Vérifie le mdp à partir du hash
			if (BCrypt.checkpw(utilisateur.getMdp(),
					utilisateurRepository.findByMail(utilisateur.getMail()).getMdp())) {

				// Récupère l'id de l'utilisateur connecté
				long id = utilisateurRepository.findByMail(utilisateur.getMail()).getId();

				// Récupère le nom et prénom de l'utilisateur connecté
				String nom = utilisateurRepository.findByMail(utilisateur.getMail()).getNom();
				String prenom = utilisateurRepository.findByMail(utilisateur.getMail()).getPrenom();

				// Récupère le rôle de l'utilisateur connecté
				int role = utilisateurRepository.findByMail(utilisateur.getMail()).getRole();

				// Met en session l'id, le nom et le prénom de l'utilisateur
				// connecté
				session.setAttribute("id", id);
				session.setAttribute("nomprenom", prenom + " " + nom);
				session.setAttribute("role", role);

				// Supprime le message d'erreur
				session.removeAttribute("msgerreur");

				return "redirect:/";
			} else {
				System.out.println("mdp faux");
				session.setAttribute("msgerreur", "Le mot de passe que vous avez saisi est incorrect !");
				return "utilisateurconnecter";
			}

		} else {
			System.out.println("pas d'utilisateur avec cette adresse email");
			session.setAttribute("msgerreur", "L'adresse mail n'est pas valide !");
			return "utilisateurconnecter";
		}
	}

	// Déconnecte l'utilisateur
	@GetMapping("/deconnexion")
	public String utilisateurdeconneter(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
	}
}

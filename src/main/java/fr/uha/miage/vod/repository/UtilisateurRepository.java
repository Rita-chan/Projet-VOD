package fr.uha.miage.vod.repository;

import org.springframework.data.repository.CrudRepository;

import fr.uha.miage.vod.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long>{

	public Utilisateur findByMailAndMdp(String email, String mdp );
	public Utilisateur findByMail(String email);
	public Utilisateur findByNom(String nom);
	public Utilisateur findByPrenom(String prenom);
	
}

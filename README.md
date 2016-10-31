# Projet-VOD
Il s'agit de notre projet d'application web en M2 MIAGE <br>
BOHN Cindy <br>
JEAN Anita <br>
<br>
##Présentation
Il nous a été demandé de créer via le framework Spring de coder en JEE une application Web de vidéo à la demande.<br>
Notre application se nomme Great Movies.<br>
<br>
##Importation du projet
Pour récupérer le projet sur éclipse :<br>
_ Dans la barre de menu, cliquer sur File -> Import...<br>
_ Projects from Git<br>
_ Clone URI<br>
_ Location:<br>
&nbsp; URI: &nbsp; https://github.com/Rita-chan/Projet-VOD<br>
&nbsp; Host: &nbsp; github.com<br>
&nbsp; Repository path: &nbsp; /Rita-chan/Projet-VOD<br>
<br>
Authentification : Entrez votre nom d'utilisateur ainsi que votre mot de passe<br>
_ Next<br>
_ Choisissez où stocker le projet<br>
...<br>
<br>
##Utilisation de l'application
Pour lancer l'application sur eclipse : clic droit sur le fichier src/main/java/fr/uha/miage/vod/Application.java -> Run as -> Java Application<br>
Ensuite ouvrez votre navigateur web sur l'addresse http://localhost:8080<br>
<br>
Il existe 3 interfaces:<br>
<br>
###Utilisateur Anonyme
Vous pouvez accéder aux films via la page d'acceuil qui affiche les 4 derrniers films ajoutés dans chaque catégories existantes, ou bien en effectuant des recherches : par acteur, par catégorie, par pays, par réalisateur ou avec le titre du film dans la barre de navigation sur la gauche de vore écran.<br>
Après avoir validé votre recherche, une liste de films vous est présentée.<br>
Vous pouvez cliquer sur l'affiche du film afin de voir les informations le concernant.<br>
Sur cette page vous pouvez visionner le film en cliquant sur le bouton du même nom, et lire les avis des utilisateurs connectés sur ce film.<br>
Vous pouvez aussi vous inscrire sur le site avec le bouton Inscription sur la barre de navigation en haut de votre écran, ou vous connecter.<br>
<br>
###Utilisateur Connecté
Sur la page d'informations d'un film, vous pouvez désormais écrire votre avis concernant le film.
Vous pouvez modifier les informations liés à votre compte en cliquant sur le bouton Bienvenue sur la barre de navigation en haut de votre écran.<br>
<br>
###Utilisateur Administrateur
En cliquant sur le bouton Administration sur la barre de navigation en haut de votre écran vous pouvez accéder à l'interface administrateur.<br>
La barre de navigation sur la gauche de votre écran permet de retourner à l'acceuil du site (bouton en forme de maison), ou de vous déconnecter(bouton off).<br>
La page d'acceuil affiche certaines statistiques.<br>
Pour chaque éléments : Acteur, Catégorie, Films, Pays, Réalisateur,  il vous est permis d'en créer de nouveaux et de voir, modifier ou supprimer les existants.<br>
Les Avis ne peuvent qu'être visualisé et supprimé.<br>
Les utilisateurs peuvent être visualisé, attribué ou retiré d'un rôle d'administrateur ou être supprimés.<br>
<br>
<br>
##Utilisateurs déjà existant
###Utilisateurs simples
mail: util1@util1.fr<br>
mdp: util1<br>
<br>
mail: util2@util2.fr<br>
mdp: util2<br>
<br>
###Utilisateur Administrateur
mail: admin@admin.fr<br>
mdp: admin<br>

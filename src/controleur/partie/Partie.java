/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package controleur.partie;

import java.util.Scanner;

import config.ConfigurationJeu;
import modele.grille.Grille;
import modele.mobile.Coordonnees;
import outils.OutilsJeu;
import vu.cli.AffichageCLI;

public class Partie {

	private int nombreLigneGrille = ConfigurationJeu.NB_LIGNE;
	private int nombreColonneGrille = ConfigurationJeu.NB_COLONNE;

	private Grille grilleJoueur = new Grille(nombreLigneGrille, nombreColonneGrille);

	private Grille grilleRobot = new Grille(nombreLigneGrille, nombreColonneGrille);

	private Joueur joueur;
	private Joueur robot;
	private boolean modeTriche;

	private AffichageCLI affichageCLI = new AffichageCLI();

	/**
	 * @param in
	 * @param joueur
	 * @param robot
	 * @param modeTriche
	 */
	public Partie(Scanner in) {
		this.joueur = new Joueur(grilleJoueur, grilleRobot);
		this.robot = new Joueur(grilleRobot, grilleJoueur);

		System.out.println("\nBienvenue dans le jeu Bataille Navale en mode console !!! \n");
		System.out.println("\nVoulez-vous lancer le jeu en mode triche ? ( OUI => o et NON => n )");
		// dimensions
		String scanValue = in.nextLine(); // Lecture de la console
//		String scanValue = "o";
		while (!(scanValue.equals("o") || scanValue.equals("n"))) {
			System.out
					.println("\nVous n\'avez pas rentré \"n\" ou \"o\" , veuillez respecter la consigne demandé !!!!");
			System.out.println("\nVoulez-vous lancer le jeu en mode triche ? ( OUI => o et NON => n )");
			scanValue = in.nextLine(); // Lecture de la console
		}

		if (scanValue.equals("o")) {
			this.modeTriche = true;
		} else {
			this.modeTriche = false;
		}

		System.out.println("\n\t Votre partie à été créé avec succes !! ");

	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Joueur getRobot() {
		return robot;
	}

	public boolean isModeTriche() {
		return modeTriche;
	}

	public void tourJoueur(Scanner in) {
		System.out.println("\n-------------------------------------------------\n");

		System.out.println("\nAffichage de votre carte contenant vos navires : \n");

		Grille grilleJoueur = joueur.getGrilleJoueur();
		affichageCLI.afficheGrille(grilleJoueur);

		System.out.println("\n-------------------------------------------------\n");

		System.out.println("\nAffichage de la carte ennemi : \n");

		Grille grilleEnnemi = joueur.getGrilleEnnemi();
		affichageCLI.afficheGrilleEnnemi(grilleEnnemi, modeTriche);

		System.out.println("\n-------------------------------------------------\n");

		System.out.println("\nVoulez-vous tirer ou deplacer un de vos navires ? ( TIRER => t et DEPLACER => d ) ");
		String scanValue = in.nextLine(); // Lecture de la console

		while (!(scanValue.equals("t") || scanValue.equals("d"))) {
			System.out.println(
					"\nVous n\'avez pas rentré la valeur attendu, veuillez respecter la consigne demandé !!!!");
			System.out.println("\nVoulez-vous tirer ou deplacer un de vos navires ? ( TIRER => t et DEPLACER => d ) ");
			scanValue = in.nextLine(); // Lecture de la console
		}
		
		if(!joueur.NbNavireDeplacable()) {
			System.out.println("\nTous vos navires ont été touché, vous ne pouvez donc pas en deplacer un. Nous modifions votre choix de coup a realiser en tirer !!!\n");
			scanValue = "t";
		}
		
		if (scanValue.equals("t")) {
			System.out.println(
					"\nDonner les coordonnees d'ou vous voulez tirer ? (Coordonnees de type : Lettre Nombre (exemples : a 10 | m 16 / Dans le cas ou le nombre est un chiffre veuillez ajouter un zéro devant celui-ci (exemples: d 06 | k 02 ) )");
			String coordonneesString = in.nextLine();

			while ((coordonneesString.length() != 4) && !coordonneesString.contains(" ")) {
				System.out.println(
						"\nVous n\'avez pas rentré la valeur attendu, veuillez respecter la consigne demandé !!!!");
				System.out.println(
						"\nDonner les coordonnees d'ou vous voulez tirer ? (Coordonnees de type : Lettre Nombre (exemples : a 10 | m 16 / Dans le cas ou le nombre est un chiffre veuillez ajouter un zéro devant celui-ci (exemples: d 06 | k 02 ) )");
				coordonneesString = null;
				coordonneesString = in.nextLine(); // Lecture de la console
			}

			Coordonnees coordonnees = OutilsJeu.traduitCoordonnees(coordonneesString);

			System.out.println(
					"\nAvec quel type de navire voulez-vous tirer ? (CUIRASSE => 1 | CROISSEUR => 2 | DESTROYER => 3 | SOUS-MARAIN => 4) ");
			int idNavire = in.nextInt(); // Lecture de la console
			while (!(idNavire >= 1 && idNavire <= 4) || !joueur.verifNavireDispo(idNavire)) {
				System.out.println(
						"\nVous n\'avez pas rentré la valeur attendu ou ce navire est indisponible, veuillez respecter la consigne demandé ou le type de navire demander n'est plus disponible !!!!");
				System.out.println(
						"\nAvec quel type de navire voulez-vous tirer, regardez votre carte pour ne pas choisir un Navire coulé ? (CUIRASSE => 1 | CROISSEUR => 2 | DESTROYER => 3 | SOUS-MARAIN => 4) ");
				idNavire = in.nextInt(); // Lecture de la console
			}

			joueur.tirer(coordonnees, idNavire);

			if (idNavire != 3) {
				joueur.afficheNbTouche();

				robot.verifNavireCoule();
				System.out.println("NOMBRE DE NAVIRE COULE :" + robot.getNbNavireCoule());
			}

			System.out.println("\nAffichage de la carte ennemi après le tire : \n");
			affichageCLI.afficheGrilleEnnemi(grilleEnnemi, modeTriche);

		} else {
			joueur.afficheFlotteNumerote();

			System.out.println(
					"\nDonner le numéro du navire que vous souhaitez deplacer ? (Voir la carte ci-dessus : )  )");
			int indiceNavire = in.nextInt();

			while (!(indiceNavire >= 0 && indiceNavire <= 9) && !joueur.verifDeplacable(indiceNavire)) {
				System.out.println(
						"\nLe Navire choisi est touché ou coulé il ne peut donc pas se deplacer,choississez en un autre !!!!");
				System.out.println(
						"\nDonner le numéro du navire que vous souhaitez deplacer ? (Voir la carte ci-dessus : )  ");
				indiceNavire = in.nextInt(); // Lecture de la console
			}

			System.out.println(
					"\nDonner la direction d'ou vous voulez vous deplacer ? (Direction : Gauche => 0 | Haut => 1 | Droite => 2 | Bas => 3 )  )");
			int direction = in.nextInt();

			while (!(direction >= 0 && direction <= 3)) {
				System.out.println(
						"\nVous n\'avez pas rentré la valeur attendu, veuillez respecter la consigne demandé !!!!");
				System.out.println(
						"\nDonner la direction d'ou vous voulez vous deplacer ? (Direction : Gauche => 0 | Haut => 1 | Droite => 2 | Bas => 3 )  )");
				direction = in.nextInt(); // Lecture de la console
			}

			while (!joueur.deplacer(indiceNavire, direction)) {
				System.out.println(
						"\nLe deplacement est impossible soit car : \n\t - La case est déjà prise \n\t - Le bateau touche les bornes de la carte \n\t - Le bateau choisi n'est pas dans le bon sens ( Exemple : Bateau Horizontale et deplacement en haut => IMPOSSIBLE )");

				System.out.println(
						"\nDonner la direction d'ou vous voulez vous deplacer ? (Direction : Gauche => 0 | Haut => 1 | Droite => 2 | Bas => 3 ) ");
				direction = in.nextInt(); // Lecture de la console
			}
			robot.afficheFlotteNumerote();
			System.out.println("\nAffichage de votre carte après déplacement de votre navire : \n");

			affichageCLI.afficheGrille(grilleJoueur);

		}
	}

	public void tourRobot() {
		System.out.println("\n-------------------------------------------------\n");

		System.out
				.println("\nROBOT voulez-vous tirer ou deplacer un de vos navires ? ( TIRER => t et DEPLACER => d ) ");

		String scanValue = robot.coupAleatoire();// Lecture de la console
//		String scanValue = "d";// Lecture de la console
		
		if(!joueur.NbNavireDeplacable()) {
			System.out.println("\nTous vos navires ont été touché, vous ne pouvez donc pas en deplacer un. Nous modifions votre choix de coup a realiser en tirer !!!\n");
			scanValue = "t";
		}
		
		if (scanValue.equals("t")) {
			System.out.println("\nROBOT a choisi de tirer !!!");

			System.out.println("\nChoix des coordonnées !!!!");
			Coordonnees coordonnees = OutilsJeu.generateurCoordonneesAleatoire();
			
			System.out.println(
					"\nChoix du navire avec lequel le ROBOT veur tirer ? (CUIRASSE => 1 | CROISSEUR => 2 | DESTROYER => 3 | SOUS-MARAIN => 4) ");
			
			robot.afficheFlotteNumerote();
			
			int idNavire = OutilsJeu.idNavireAleatoire();
			System.out.println("\nNAVIRE CHOISI: " + idNavire);
			while (!robot.verifNavireDispo(idNavire)) {
				System.out.println("\nLe type de Navire choisi est indisponible il ne peut donc pas etre utilise !!!!");
				System.out.println("\nROBOT recommence le choix du type navire avec lequel tiree ? ");
				System.out.println("NAVIRE CHOISI: " + idNavire);
				idNavire = OutilsJeu.idNavireAleatoire();
			}
			System.out.println("NAVIRE CHOISI: " + idNavire);

			robot.tirer(coordonnees, idNavire);

			if (idNavire != 3) {
				robot.afficheNbTouche();

				joueur.verifNavireCoule();
				System.out.println("NOMBRE DE NAVIRE COULE :" + joueur.getNbNavireCoule());
			}

			affichageCLI.afficheGrille(robot.getGrilleEnnemi());

		} else {
			System.out.println("\nROBOT a choisi de deplacer un navire !!!");

			System.out.println("\nChoix aleatoire du navire à deplacer ? ");

			robot.afficheFlotteNumerote();

			int indiceNavire = OutilsJeu.numNavireDeplacer();
//			int indiceNavire = 0;
			System.out.println("\nVERIF DEPLACABLE  : " + robot.verifDeplacable(indiceNavire));
			System.out.println("NAVIRE CHOISI : " + indiceNavire);
			while (!robot.verifDeplacable(indiceNavire)) {
				System.out.println(robot.verifDeplacable(indiceNavire));

				System.out.println("VERIF DEPLACABLE  : " + robot.verifDeplacable(indiceNavire));
				System.out.println("NAVIRE CHOISI : " + indiceNavire);
				indiceNavire = OutilsJeu.numNavireDeplacer();
			}
			System.out.println("VERIF DEPLACABLE  : " + robot.verifDeplacable(indiceNavire));
			System.out.println("NAVIRE CHOISI : " + indiceNavire);

			System.out.println("\nChoix aleatoire de la direction d'ou vous voulez vous deplacer ?   (Direction : Gauche => 0 | Haut => 1 | Droite => 2 | Bas => 3 ) ");
			int direction = robot.directionAleatoire();
//			int direction = 0;

			while (!robot.checkDeplacement(indiceNavire, direction)) {

				System.out.println(
						"\nLe deplacement est impossible soit car : \n\t - La case est déjà prise \n\t - Le bateau coule les bornes de la carte \n\t - Le bateau choisi n'est pas dans le bon sens ( Exemple : Bateau Horizontale et deplacement en haut => IMPOSSIBLE");

				System.out.println(
						"\nDonner la direction d'ou vous voulez vous deplacer ? (Direction : Gauche => 0 | Haut => 1 | Droite => 2 | Bas => 3 )  ");
				direction = robot.directionAleatoire();
				System.out.println("NAVIRE CHOISI : " + indiceNavire);
				System.out.println("DIRECTION CHOISI : " + direction);
			}

			System.out.println("NAVIRE CHOISI : " + indiceNavire + " || DIRECTION CHOISI : " + direction);
			System.out.println("\nROBOT choisi de deplace un navire \n");
			robot.deplacer(indiceNavire, direction);

			if (modeTriche) {
				affichageCLI.afficheGrilleEnnemi(robot.getGrilleJoueur(),modeTriche);
			}

		}

	}

//	public static void main(String[] args) {
//
//		try (Scanner in = new Scanner(System.in)) {
//
//			Partie p = new Partie(in);
//
//			while (true) {
//				p.tourJoueur(in);
//				p.tourRobot();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}

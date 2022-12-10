/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 28 nov. 2022
 */
package vu.cli;

import java.util.Scanner;

import config.ConfigurationJeu;
import controleur.partie.Jeu;
import controleur.partie.Joueur;
import modele.map.Carte;

public class MainCli {

	private Carte carteJoueur;

	private Carte carteRobot;

	private Joueur joueur;

	private Joueur robot;

	private Jeu jeu;

	boolean modeTriche;

	boolean win = false;

	String winnerName;

	public MainCli() {
		init();
	}

	public void init() {
		System.out.println("Bienvenue dans le jeu Bataille Navale en mode console  !!!!");

		System.out.println("\nCreation des 2 cartes de jeu : ");

		carteJoueur = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);

		System.out.println("\n\t La carte du joueur ..... Creee avec succes !! ");

		carteRobot = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);

		System.out.println("\n\t La carte du robot ..... Creee avec succes !! ");

		System.out.println("\nCreation des deux joueurs de la partie : ");

		joueur = new Joueur(carteJoueur, carteRobot);

		System.out.println("\n\t Joueur ..... Cree avec succes !! ");

		robot = new Joueur(carteRobot, carteJoueur);

		System.out.println("\n\t Robot ..... Cree avec succes !! ");

		System.out.println("\nCreation de la partie en cours ...");

		System.out.println("\nVoulez-vous lancer le jeu en mode triche ? ( OUI => o et NON => n )");

		modeTriche = false;

		try (Scanner in = new Scanner(System.in)) { // Scanner pour lecture de la console pour recuperation des
													// dimensions
//			String scanValue = in.nextLine(); // Lecture de la console
			String scanValue = "o";
			while (!(scanValue.equals("o") || scanValue.equals("n"))) {
				System.out.println(
						"\nVous n\'avez pas rentré \"n\" ou \"o\" , veuillez respecter la consigne demandé !!!!");
				System.out.println("\nVoulez-vous lancer le jeu en mode triche ? ( OUI => o et NON => n )");
				scanValue = in.nextLine(); // Lecture de la console
			}

			if (scanValue.equals("o")) {
				modeTriche = true;
			} else {
				modeTriche = false;
			}

			jeu = new Jeu(joueur, robot, modeTriche);

			System.out.println("\n\t Votre partie à été créé avec succes !! ");

			run();

		} catch (NumberFormatException exception) {
			exception.printStackTrace();
			System.out.println(
					"ERREUR lors de la lecture de la console. Veuillez relancer le programme et entrer une valeur numerique !!!!");
		}
	}

	public void run() {
		String scanValue = " ";
		try (Scanner in = new Scanner(System.in)) { // Scanner pour lecture de la console pour recuperation dimensions
			while (!win) {
				System.out.println("\n-------------------------------------------------\n");

				System.out.println("\nAffichage de votre carte contenant vos navires : \n");

				joueur.getCarteJoueur().afficheCarte();

				System.out.println("\n-------------------------------------------------\n");

				System.out.println("\nAffichage de la carte ennemi : \n");

				joueur.getCarteEnnemi().afficheCarteEnnemi(modeTriche);

				System.out.println("\n-------------------------------------------------\n");

				System.out.println(
						"\nVoulez-vous tirer ou deplacer un de vos navires ? ( TIRER => t et DEPLACER => d ) ");

				scanValue = in.nextLine(); // Lecture de la console
				while (!(scanValue.equals("t") || scanValue.equals("d"))) {
					System.out.println(
							"\nVous n\'avez pas rentré la valeur attendu, veuillez respecter la consigne demandé !!!!");
					System.out.println(
							"\nVoulez-vous tirer ou deplacer un de vos navires ? ( TIRER => t et DEPLACER => d ) ");
					scanValue = in.nextLine(); // Lecture de la console
				}

				if (scanValue.equals("t")) {
					System.out.println(
							"\nDonner les coordonnees d'ou vous voulez tirer ? (Coordonnees de type : Lettre Nombre (exemples : a 10 | m 16 / Dans le cas ou le nombre est un chiffre veuillez ajouter un zéro devant celui-ci (exemples: d 06 | k 02 ) )");
					String coordonnees = in.nextLine();
					

					while ((coordonnees.length() != 4) && !coordonnees.contains(" ")) {
						System.out.println(
								"\nVous n\'avez pas rentré la valeur attendu, veuillez respecter la consigne demandé !!!!");
						System.out.println(
								"\nDonner les coordonnees d'ou vous voulez tirer ? (Coordonnees de type : Lettre Nombre (exemples : a 10 | m 16 / Dans le cas ou le nombre est un chiffre veuillez ajouter un zéro devant celui-ci (exemples: d 06 | k 02 ) )");
						coordonnees = null;
						coordonnees = in.nextLine(); // Lecture de la console
					}

					String[] coordonneesSplit = new String[2];
					coordonneesSplit = coordonnees.split(" ");
					
					System.out.println(
							"\nAvec quel type de navire voulez-vous tirer ? (CUIRASSE => 1 | CROISSEUR => 2 | DESTROYER => 3 | SOUS-MARAIN => 4) ");
					int idNavire = in.nextInt(); // Lecture de la console
					while (!(idNavire >= 1 && idNavire <= 4) || !joueur.verifNavireDispo(idNavire)) {
						System.out.println(
								"\nVous n\'avez pas rentré la valeur attendu ou ce navire est indisponible, veuillez respecter la consigne demandé !!!!");
						System.out.println(
								"\nAvec quel type de navire voulez-vous tirer, regardez votre carte pour ne pas choisir un Navire coulé ? (CUIRASSE => 1 | CROISSEUR => 2 | DESTROYER => 3 | SOUS-MARAIN => 4) ");
						idNavire = in.nextInt(); // Lecture de la console
					}

					joueur.tirer(coordonneesSplit, idNavire);
					
					if(idNavire!=3) {
						joueur.afficheNbTouche();
						
						robot.verifNavireCoule();
						System.out.println("NOMBRE DE NAVIRE COULE :" + robot.getNbNavireCoule());
					}

					System.out.println("\nAffichage de la carte ennemi après le tire : \n");
					joueur.getCarteEnnemi().afficheCarteEnnemi(modeTriche);

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
					robot.getCarteJoueur().afficheCarte();

					
				}
				
				jeu.joueAleatoirement(); // TO DO
				
				winnerName = jeu.verifWin();
				if (winnerName != null) {
					win = true;
				}
			}
			System.out.println("\n-------------------------------------------------\n");

			System.out.println("\nFIN DE PARTIE !!!!");
			if (winnerName.equals("Joueur")) {
				System.out.println("\nLe JOUEUR a remporte la partie car il a coule tous les navires du ROBOT !!!");
			} else {
				System.out.println("\nLe ROBOT a remporte la partie car il a coule tous les navires du JOUEUR !!!");
			}
		} catch (NumberFormatException exception) {
			exception.printStackTrace();
			System.out.println(
					"ERREUR lors de la lecture de la console. Veuillez relancer le programme et entrer une valeur numerique !!!!");
		}
	}

//	public void run() {
//		while(!win) {
//			tour(joueur);
//			tour(robot);
//		}
//	}

	public static void main(String[] args) {
		new MainCli();
	}

}

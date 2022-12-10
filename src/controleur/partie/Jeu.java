/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package controleur.partie;

import config.ConfigurationJeu;

public class Jeu {

	private Joueur joueur;
	private Joueur robot;
	private boolean modeTriche;

	/**
	 * @param joueur
	 * @param robot
	 * @param modeTriche
	 */
	public Jeu(Joueur joueur, Joueur robot, boolean modeTriche) {
		this.joueur = joueur;
		this.robot = robot;
		this.modeTriche = modeTriche;
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
	
	public void affichePartie() {
		System.out.println("VOTRE CARTE CONTENANT VOS NAVIRES : ");
		joueur.getCarteJoueur().afficheCarte();
		
		System.out.println("LA CARTE DE L'ENNEMI : ");
		joueur.getCarteEnnemi().afficheCarteEnnemi(modeTriche);
	}
	
	public String verifWin() {
		if (joueur.getNbNavireCoule() == ConfigurationJeu.NB_NAVIRE) {
			return "Robot";
		} else if (robot.getNbNavireCoule() == ConfigurationJeu.NB_NAVIRE) {
			return "Joueur";
		}
		return null;
	}
	
	public void joueAleatoirement() {
		System.out.println("\n-------------------------------------------------\n");
		
		System.out.println(
				"\nROBOT voulez-vous tirer ou deplacer un de vos navires ? ( TIRER => t et DEPLACER => d ) ");

		String scanValue = robot.coupAleatoire();// Lecture de la console
//		String scanValue = "d";// Lecture de la console
		if (scanValue.equals("t")) {
			System.out.println("\nROBOT a choisi de tirer !!!");
			
			System.out.println("\nChoix de la coordonnées !!!!");
			String[] coordonneesSplit = robot.coordAleatoireSplit();
			
			System.out.println(
					"\n Choix du navire avec lequel le ROBOT veur tirer ? (CUIRASSE => 1 | CROISSEUR => 2 | DESTROYER => 3 | SOUS-MARAIN => 3) ");
			int idNavire = robot.idNavireAleatoire();
			
			while (!robot.verifNavireDispo(idNavire)) {
				System.out.println(
						"\nLe Navire choisi est indisponible il ne peut donc pas etre utilise !!!!");
				System.out.println(
						"\nROBOT recommence le choix du type navire avec lequel tiree ? ");
				System.out.println("NAVIRE : "+idNavire);
				idNavire = robot.idNavireAleatoire();
			}
			System.out.println("NAVIRE : "+idNavire);
			
			String nomNavire = robot.recupNomBateau(idNavire);

			System.out.println("\nROBOT tire sur la case de coordonnees : ["+coordonneesSplit[0]+"]["+coordonneesSplit[1]+"] avec le bateau "+ nomNavire);
			robot.tirer(coordonneesSplit, idNavire);
			
			if(idNavire!=3) {
				robot.afficheNbTouche();

				joueur.verifNavireCoule();
				System.out.println("NOMBRE DE NAVIRE COULE :" + joueur.getNbNavireCoule());
			}

			robot.getCarteEnnemi().afficheCarte();

		} else {
			System.out.println("\nROBOT a choisi de deplacer un navire !!!");

			System.out.println(
					"\nChoix aleatoire du navire à deplacer ? ");
			int indiceNavire = robot.idNavireAleatoire();
//			int indiceNavire = 0;
			System.out.println("VERIF TOUCHE : "+robot.verifDeplacable(indiceNavire));
			System.out.println("NAVIRE CHOISI : "+indiceNavire);
			while (!robot.verifDeplacable(indiceNavire)) {
				System.out.println(robot.verifDeplacable(indiceNavire));
				System.out.println("NAVIRE CHOISI : "+indiceNavire);
				indiceNavire = robot.idNavireAleatoire(); // Lecture de la console
			}
			System.out.println("NAVIRE CHOISI : "+indiceNavire);
			
			System.out.println(
					"\nChoix aleatoire de la direction d'ou vous voulez vous deplacer ?  )");
			int direction = robot.directionAleatoire();
//			int direction = 0;
			
			while (!robot.checkDeplacement(indiceNavire, direction)) {
				System.out.println(
						"\nLe deplacement est impossible soit car : \n\t - La case est déjà prise \n\t - Le bateau coule les bornes de la carte \n\t - Le bateau choisi n'est pas dans le bon sens ( Exemple : Bateau Horizontale et deplacement en haut => IMPOSSIBLE");

				System.out.println(
						"\nDonner la direction d'ou vous voulez vous deplacer ? (Direction : Gauche => 0 | Haut => 1 | Droite => 2 | Bas => 3 )  )");
				direction = robot.directionAleatoire(); 
				System.out.println("NAVIRE CHOISI : "+indiceNavire);
				System.out.println("DIRECTION CHOISI : "+direction);
			}

			System.out.println("NAVIRE CHOISI : "+indiceNavire +" || DIRECTION CHOISI : "+direction);
			System.out.println("\nROBOT choisi de deplace un navire \n");
			robot.deplacer(indiceNavire, direction);
			
		}

	}

}

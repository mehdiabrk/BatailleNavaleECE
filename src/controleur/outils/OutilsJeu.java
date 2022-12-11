/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * EceBatailleNavale
 *
 * 4 dec. 2022
 */
package controleur.outils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import config.ConfigurationJeu;
import controleur.partie.Joueur;
import modele.grille.Bloc;
import modele.grille.Grille;
import modele.mobile.Coordonnees;
import modele.mobile.Croiseur;
import modele.mobile.Cuirasse;
import modele.mobile.Destroyer;
import modele.mobile.Flotte;
import modele.mobile.Navire;
import modele.mobile.SousMarin;

/**
 * Cette classe contient toutes pleins de methpdes utiles au jeu sans pour autant etre liee au classe du projet
 */

public class OutilsJeu {
	/**
	 * Methode servant a la genereation aleatoire d'une direction
	 *
	 * @return 0 : Horizontale | 1 : Verticale
	 */
	public static int directionAleatoire() {
		Random r = new Random();
		return (r.nextInt(2));
	}

	/**
	 * Methode servant a la genereation d'une valeur aleatoire entre 1 et une borne superieur
	 * 
	 * @param borneSup : Valeur Borne superieur
	 *
	 * @return 0 : Horizontale | 1 : Verticale
	 */
	public static int valeurAleatoire(int borneSup) {
		Random r = new Random();

		return (r.nextInt(borneSup) + 1);
	}

	/**
	 * Methode servant a la genereation d'une valeur aleatoire entre 1 et une borne superieur
	 * 
	 * @param borneSup : Valeur Borne superieur
	 *
	 * @return 0 : Horizontale | 1 : Verticale
	 */
	public static int valeurZeroAleatoire(int borneSup) {
		Random r = new Random();

		return (r.nextInt(borneSup));
	}

	/**
	 * Methode servant a la genereation d'une coordonnees
	 * 
	 * @return une coordonnees
	 */
	public static Coordonnees generateurCoordonneesAleatoire() {
		int indiceLigne = valeurAleatoire(ConfigurationJeu.NB_LIGNE - 2);

		int indiceColonne = valeurAleatoire(ConfigurationJeu.NB_COLONNE - 2);

		return new Coordonnees(indiceLigne, indiceColonne);

	}

	/**
	 * Methode servant a verifier si l'indice de la ligne donne est coherent
	 * 
	 * @param indice : indice donne en console
	 *
	 * @return Vrai si coherant , Faux sinon
	 */
	public static boolean verifIndiceLigne(String indice) {
		String tabIndiceLigne[] = ConfigurationJeu.INDICE_LIGNE.split(",");
		for (int i = 0; i < tabIndiceLigne.length; i++) {
			if (indice.equals(tabIndiceLigne[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode servant a verifier si l'indice de la colonne donne est coherent
	 * 
	 * @param indice : indice donne en console
	 *
	 * @return Vrai si coherant , Faux sinon
	 */
	public static boolean verifIndiceColonne(String indice) {
		String tabIndiceColonne[] = ConfigurationJeu.INDICE_COLONNE.split(",");
		for (int i = 0; i < tabIndiceColonne.length; i++) {
			if (indice.equals(tabIndiceColonne[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode servant a la traduction de la coordonnees en parametre
	 * 
	 * @param coordString : coordonnness en chaine en caractere donne en console
	 *
	 * @return Coordonnees en type coordonnees
	 */
	public static Coordonnees traduitCoordonnees(String coordString) {
		String[] tabCoordString = coordString.split(" ");

		String indiceLigneString = tabCoordString[0];
		String indiceColonneString = tabCoordString[1];

		System.out.println("VERIFILGNE : " + verifIndiceLigne(indiceLigneString) + " && VERIFCOLONNE :"
				+ verifIndiceColonne(indiceColonneString));

		if (verifIndiceLigne(indiceLigneString) && verifIndiceColonne(indiceColonneString)) {
			int indiceColonne = Integer.parseInt(indiceColonneString);

			Coordonnees c = null;

			int i = 0;

			for (String string : ConfigurationJeu.INDICE_LIGNE.split(",")) {
				if (indiceLigneString.equals(string)) {
					c = new Coordonnees(i, indiceColonne);
				}
				i++;
			}

			return c;
		} else {
			System.out.println("\nLES COORDONNEES NE SONT SOIT : \n\t- PAS COMPRISE DANS LA GRILLE \n\t- FAUSSES");
			return null;
		}

	}

	/**
	 * Methode servant au choix d'une valeure aleatoire entre 1 et 4 
	 * 
	 * @return Valeure entre 1 et 4
	 */
	public static int idNavireAleatoire() {
		return OutilsJeu.valeurAleatoire(4);
	}

	/**
	 * Methode servant au choix d'une valeure aleatoire entre 0 et 9
	 * 
	 * @return Valeure entre 0 et 9
	 */
	public static int numNavireDeplacer() {
		return OutilsJeu.valeurZeroAleatoire(10);
	}

	/**
	 * Methode servant a verifier si les coordonnees entrees sont coherentes
	 * 
	 * @return Vrai si c'est le cas, Faux sinon
	 */
	public static boolean verifEntrees(String coordonneesString) {
		if (coordonneesString.contains(" ")) {
			String[] coordonneesSplit = coordonneesString.split(" ");

			if ((coordonneesString.length() == 4) && coordonneesSplit.length == 2 && coordonneesSplit[0].length() == 1
					&& coordonneesSplit.length == 2) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	
	/**
	 * Methode servant a la sauvegarde de la partie dans un fichier
	 * 
	 */
	public static void sauvegardeFichier(Joueur joueur, Joueur robot) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Date date = new Date();
		String dateString = simpleDateFormat.format(date);
		String chemin = "src/sauvegarde/" + dateString + ".txt";
		File fichier = new File(chemin);

		ArrayList<Navire> listeNavireJoueur = joueur.getFlotte().getListeNavire();

		Grille grilleJoueur = joueur.getGrilleJoueur();
		Bloc[][] joueurBlocs = grilleJoueur.getBlocs();
		ArrayList<Bloc> listeBlocsJoueur = new ArrayList<>();

		for (int i = 0; i < ConfigurationJeu.NB_LIGNE; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE; j++) {
				listeBlocsJoueur.add(joueurBlocs[i][j]);
			}
		}

		ArrayList<Navire> listeNavireRobot = robot.getFlotte().getListeNavire();

		Grille grilleRobot = robot.getGrilleJoueur();
		Bloc[][] robotBlocs = grilleRobot.getBlocs();
		ArrayList<Bloc> listeBlocsRobot = new ArrayList<>();

		for (int i = 0; i < ConfigurationJeu.NB_LIGNE; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE; j++) {
				listeBlocsRobot.add(robotBlocs[i][j]);
			}
		}

		try {// Essaye
			FileWriter ecrire = new FileWriter(fichier);// Creer une variable ecrire de type FileWritter permettant
														// d'ecrire dans le fichier donne en parametre
			@SuppressWarnings("resource")
			BufferedWriter bufferedWriter = new BufferedWriter(ecrire); // Memoire tampon pour interagir avec le fichier
																		// ou l'on veut ecrire

//			bufferedWriter.write("FICHIER DE SAUVEGARDE DE VOTRE PARTIE DE BATAILLE NAVALE DU " + dateString);
//			bufferedWriter.newLine();
			for (Navire navire : listeNavireJoueur) {
				bufferedWriter.write(navire.getId() + "," + navire.getTaille() + "," + navire.getImpactMissile() + ","
						+ navire.isCoule() + "," + navire.getDirection() + "," + navire.isTouche());
				bufferedWriter.newLine();

				for (Coordonnees c : navire.getListeCoordonnees()) {
					bufferedWriter.write(c.getLigne() + "," + c.getColonne() + " ");
				}
				bufferedWriter.newLine();
			}
			bufferedWriter.write(joueur.getNbBlocTouche() + "," + joueur.getNbNavireCoule());
			bufferedWriter.newLine();
			bufferedWriter.newLine();

			for (Navire navire : listeNavireRobot) {
				bufferedWriter.write(navire.getId() + "," + navire.getTaille() + "," + navire.getImpactMissile() + ","
						+ navire.isCoule() + "," + navire.getDirection() + "," + navire.isTouche());
				bufferedWriter.newLine();

				for (Coordonnees c : navire.getListeCoordonnees()) {
					bufferedWriter.write(c.getLigne() + "," + c.getColonne() + " ");
				}
				bufferedWriter.newLine();

			}
			bufferedWriter.write(robot.getNbBlocTouche() + "," + robot.getNbNavireCoule());

			bufferedWriter.close();
			ecrire.close();

		} catch (IOException e) {// Si l'essai n'est pas positif
			// TODO Auto-generated catch block
			e.printStackTrace(); // Affiche erreur en console
		}

		// Methode servant a lire l'entiete d'un fichier ligne par ligne
		try {
			FileReader lire = new FileReader(fichier);
			BufferedReader bufferedReader = new BufferedReader(lire);
			String resultLecture = bufferedReader.readLine();
			while (resultLecture != null) {
				System.out.println(resultLecture);
				resultLecture = bufferedReader.readLine();
			}

			bufferedReader.close();
			lire.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * Methode servant au chargement d'une partie dans un fichier
	 * 
	 * @param cheminFichier : le chemin du fichier de sauvegarde 
	 * 
	 * @return la liste des deux joueurs que l'on a charger
	 * 
	 */
	public static ArrayList<Joueur> chargerJoueur(String cheminFichier) {

		File fichier = new File(cheminFichier);

		ArrayList<Joueur> listeJoueurs = new ArrayList<>();
		
		Grille grilleJoueur = new Grille(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
		Grille grilleRobot = new Grille(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);

		try {
			FileReader lire = new FileReader(fichier);
			BufferedReader bufferedReader = new BufferedReader(lire);
			String resultLecture = bufferedReader.readLine();

			int id = 0;
			int taille = 0;
			int impactMissile = 0;
			boolean coule = false;
			int direction = 0;
			boolean touche = false;
			int nbBlocTouche = 0;
			int nbNavireCoule = 0;
			ArrayList<Navire> listeNavires = new ArrayList<>();
			ArrayList<Coordonnees> c = new ArrayList<>();

			String[] splitLineNavire;
			String[] splitLineDetails;
			String[] splitLineCoord;
			
			Joueur joueur = null;
			Joueur robot = null;

			int i = 1;
			while (resultLecture != null) {
				if (resultLecture.equals("")) {

					joueur = new Joueur(grilleJoueur, grilleRobot, new Flotte(listeNavires), nbBlocTouche, nbNavireCoule);
					listeNavires = new ArrayList<>();

					i = 0;
				} else if (i % 21 == 0) {
					splitLineDetails = resultLecture.split(",");
					nbBlocTouche = Integer.parseInt(splitLineDetails[0]);
					nbNavireCoule = Integer.parseInt(splitLineDetails[1]);
				} else if (i % 2 == 1) {
					splitLineNavire = resultLecture.split(",");
					id = Integer.parseInt(splitLineNavire[0]);
					taille = Integer.parseInt(splitLineNavire[1]);
					impactMissile = Integer.parseInt(splitLineNavire[2]);
					coule = Boolean.parseBoolean(splitLineNavire[3]);
					direction = Integer.parseInt(splitLineNavire[4]);
					touche = Boolean.parseBoolean(splitLineNavire[5]);

				} else {
					splitLineCoord = resultLecture.split(" ");
					c = coodonneesCharger(splitLineCoord);

					if (id == 1) {
						listeNavires.add(new Cuirasse(id, taille, impactMissile, coule, direction, touche, c));
					} else if (id == 2) {
						listeNavires.add(new Croiseur(id, taille, impactMissile, coule, direction, touche, c));
					} else if (id == 3) {
						listeNavires.add(new Destroyer(id, taille, impactMissile, coule, direction, touche, c));
					} else {
						listeNavires.add(new SousMarin(id, taille, impactMissile, coule, direction, touche, c));
					}
				}
				i++;
				resultLecture = bufferedReader.readLine();
			}

			bufferedReader.close();
			lire.close();
			
			robot = new Joueur(grilleRobot, grilleJoueur, new Flotte(listeNavires), nbBlocTouche, nbNavireCoule);
			
			listeJoueurs.add(joueur);
			listeJoueurs.add(robot);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return listeJoueurs;
	}

	/**
	 * Methode servant a decoupe la liste de coordonnees des navires lue dans le fichier de sauvegarde de la partie 
	 * 
	 * @param : Tableau contenant la ligne premierement decoupe
	 * 
	 * @return liste de coordonnees
	 * 
	 */
	private static ArrayList<Coordonnees> coodonneesCharger(String[] splitLineCoord) {

		ArrayList<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();

		for (int i = 0; i < splitLineCoord.length; i++) {
			String ligne = splitLineCoord[i].split(",")[0];
			String colonne = splitLineCoord[i].split(",")[1];

			Coordonnees c = new Coordonnees(Integer.parseInt(ligne), Integer.parseInt(colonne));
			listeCoordonnees.add(c);
		}

		return listeCoordonnees;
	}

//	public static void main(String[] args) {
//		String[] tab = { "9,9", "9,10", "9,11", "9,12", "9,13", "9,14", "9,15" };
//		coodonneesCharger(tab);
//
//	}

}

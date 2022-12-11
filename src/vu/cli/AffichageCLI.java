/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * EceBatailleNavale
 *
 * 4 déc. 2022
 */
package vu.cli;


import config.ConfigurationJeu;
import controleur.partie.Joueur;
import modele.grille.Bloc;
import modele.grille.Grille;

/**
 * Cette classe sert a l'affichage de nos grille durant la partie
 * 
 */

public class AffichageCLI {

	/**
	 * Methode servant a l'affichage de la grille du Joueur durant la partie
	 * 
	 * @param grilleJoueur : la grille du joueur a afficher
	 *
	 */
	public void afficheGrille(Grille grilleJoueur) {

		System.out
				.println(ConfigurationJeu.ANSI_VERT + "\n AFFICHAGE DE VOTRE GRILLE \n" + ConfigurationJeu.ANSI_RESET);

		Bloc[][] blocs = grilleJoueur.getBlocs();
		for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
				if (i < 1 || j < 1) {
					System.out.print("[" + blocs[i][j].getValeur() + "]");
				} else if(blocs[i][j].isCoule()) {
					System.out.print("[::]");
				} else if(blocs[i][j].isTouche() == true && !blocs[i][j].isCoule()) {
					if (!blocs[i][j].getValeur().equals("  ") ){
						System.out.print("[//]");
					} else {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					}
				}else {
					System.out.print("[" + blocs[i][j].getValeur() + "]");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Methode servant a l'affichage de la grille de l'ennemi durant la partie
	 * 
	 * @param grilleJoueur : la grille de l'ennemi a afficher
	 * 
	 * @param modeTriche : Vrai : affichage en mode triche , Faux: affichage en mode classique
	 *
	 */
	public void afficheGrilleEnnemi(Grille grilleEnnemi, boolean modeTriche) {
		Bloc[][] blocs = grilleEnnemi.getBlocs();
		if (modeTriche) {

			System.out.println(ConfigurationJeu.ANSI_VERT + "\n AFFICHAGE DE LA GRILLE ENNEMI EN MODE TRICHE \n"
					+ ConfigurationJeu.ANSI_RESET);

			for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
				for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
					if (i < 1 || j < 1) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
						System.out.print("[::]");
					} else if (blocs[i][j].isTouche() == true) {
						if (!blocs[i][j].getValeur().equals("  ") ){
							System.out.print("[//]");
						} else {
							System.out.print("[" + blocs[i][j].getValeur() + "]");
						}
					} else {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					}
				}
				System.out.println();
			}
		} else {

			System.out.println(
					ConfigurationJeu.ANSI_VERT + "\n AFFICHAGE DE LA GRILLE ENNEMI \n" + ConfigurationJeu.ANSI_RESET);

			for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
				for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
					if (i < 1 || j < 1) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else if (blocs[i][j].isEclaire()) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else if (blocs[i][j].isTouche() == true) {
						if (!blocs[i][j].getValeur().equals("  ") ) {
							System.out.print("[//]");
						} else {
							System.out.print("[" + blocs[i][j].getValeur() + "]");
						}
					} else {
						System.out.print("[--]");
					}
				}
				System.out.println();
			}
		}
	}
	
	
	/**
	 * Methode servant a l'affichage de la grille avec les navire numerote par indice (Ordre de la Flotte)
	 * 
	 * @param joueur : joueur pour lequel il faut afficher sa flotte numerote 
	 * 
	 */
	public void afficheFlotteNumerote(Joueur joueur) {

		System.out.println("\nAFFICHAGE GRILLE NUMEROTE : \n");

		Bloc[][] blocs = joueur.getGrilleJoueur().getBlocs();

		for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
				if ((i < 1 || j < 1) || blocs[i][j].getValeur().equals("//") || blocs[i][j].getValeur().equals("::")) {
					System.out.print("[" + blocs[i][j].getValeur() + "]");
				} else if (!blocs[i][j].getValeur().equals("  ") && !blocs[i][j].getValeur().equals("//")
						&& !blocs[i][j].getValeur().equals("::")) {
					System.out.print("[0" + joueur.recupIndice(i, j) + "]");
				} else {
					System.out.print("[--]");
				}
			}
			System.out.println();
		}
	}


}

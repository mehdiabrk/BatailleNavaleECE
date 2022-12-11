/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * EceBatailleNavale
 *
 * 4 déc. 2022
 */
package vu.cli;

import config.ConfigurationJeu;
import modele.grille.Bloc;
import modele.grille.Grille;

public class AffichageCLI {

	public void afficheGrille(Grille grilleJoueur) {

		System.out
				.println(ConfigurationJeu.ANSI_VERT + "\n AFFICHAGE DE VOTRE GRILLE \n" + ConfigurationJeu.ANSI_RESET);

		Bloc[][] blocs = grilleJoueur.getBlocs();
		for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
				System.out.print("[" + blocs[i][j].getValeur() + "]");
			}
			System.out.println();
		}
	}

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
						if (!blocs[i][j].getValeur().equals("  ")) {
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
					} else if (blocs[i][j].isTouche() == true) {
						if (!blocs[i][j].getValeur().equals("  ")) {
							System.out.print("[//]");
						} else {
							System.out.print("[" + blocs[i][j].getValeur() + "]");
						}
					} else if (blocs[i][j].isEclaire()) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else {
						System.out.print("[--]");
					}
				}
				System.out.println();
			}
		}
	}
	
	

//	public void afficheCarte(Grille carte) {
//		Bloc[][] blocs = carte.getBlocs();
//
//		System.out.println(
//				"\n ---------------------------------------------------------------------------------------- \n");
//
//		System.out.println(ConfigurationJeu.ANSI_VERT + "\n AFFICHAGE DE VOTRE GRILLE EN MODE TRICHE \n"
//				+ ConfigurationJeu.ANSI_RESET);
//
//		for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
//			for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
//				if (i < 1 || j < 1) {
//					System.out.print("[" + blocs[i][j].getValeur() + "]");
//				} else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
//					System.out.print("[::]");
//				} else if (blocs[i][j].isTouche() == true) {
//					if (!blocs[i][j].getValeur().equals("  ")) {
//						System.out.print("[//]");
//					} else {
//						System.out.print("[  ]");
//					}
//				} else {
//					System.out.print("[" + blocs[i][j].getValeur() + "]");
//				}
//			}
//			System.out.println();
//		}
//
//	}
//
//	public void afficheCarteEnnemi(Grille carte, boolean modeTriche) {
//		Bloc[][] blocs = carte.getBlocs();
//
//		System.out.println(
//				"\n ---------------------------------------------------------------------------------------- \n");
//
//		if (modeTriche) {
//
//			System.out.println(ConfigurationJeu.ANSI_VERT + "\n AFFICHAGE DE LA GRILLE ENNEMI EN MODE TRICHE \n"
//					+ ConfigurationJeu.ANSI_RESET);
//
//			for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
//				for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
//					if (i < 1 || j < 1) {
//						System.out.print("[" + blocs[i][j].getValeur() + "]");
//					} else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
//						System.out.print("[" + ConfigurationJeu.ANSI_NOIR + "::" + ConfigurationJeu.ANSI_RESET + "]");
//					} else if (blocs[i][j].isTouche() == true) {
//						if (!blocs[i][j].getValeur().equals("  ")) {
//							System.out.print(
//									"[" + ConfigurationJeu.ANSI_ROUGE + "//" + ConfigurationJeu.ANSI_RESET + "]");
//						} else {
//							System.out.print("[  ]");
//						}
//					} else if (blocs[i][j].isEclaire() == true) {
//						System.out.print("[" + ConfigurationJeu.ANSI_VERT + blocs[i][j].getValeur()
//								+ ConfigurationJeu.ANSI_RESET + "]");
//					} else {
//						System.out.print("[" + ConfigurationJeu.ANSI_CYAN + blocs[i][j].getValeur()
//								+ ConfigurationJeu.ANSI_RESET + "]");
//					}
//
//				}
//				System.out.println();
//			}
//		} else {
//
//			System.out.println(
//					ConfigurationJeu.ANSI_VERT + "\n AFFICHAGE DE LA GRILLE ENNEMI \n" + ConfigurationJeu.ANSI_RESET);
//
//			for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
//				for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
//					if (i < 1 || j < 1) {
//						System.out.print("[" + blocs[i][j].getValeur() + "]");
//					} else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
//						System.out.print("[" + blocs[i][j].getValeur() + "]");
//					} else if (blocs[i][j].isTouche() == true) {
//						if (!blocs[i][j].getValeur().equals("  ")) {
//							System.out.print(
//									"[" + ConfigurationJeu.ANSI_ROUGE + "//" + ConfigurationJeu.ANSI_RESET + "]");
//						} else {
//							System.out.print("[  ]");
//						}
//					} else if (blocs[i][j].isEclaire() == true) {
//						System.out.print("[" + ConfigurationJeu.ANSI_CYAN + blocs[i][j].getValeur()
//								+ ConfigurationJeu.ANSI_RESET + "]");
//					} else {
//						System.out.print("[--]");
//					}
//				}
//				System.out.println();
//			}
//		}
//	}
}

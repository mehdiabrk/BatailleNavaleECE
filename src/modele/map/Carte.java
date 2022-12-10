/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.map;

import config.ConfigurationJeu;

public class Carte {
	private Bloc[][] blocs;

	private int nombreLigne;
	private int nombreColonne;

	public Carte(int nombreLigne, int nombreColonne) {
		this.nombreLigne = nombreLigne;
		this.nombreColonne = nombreColonne;

		blocs = new Bloc[nombreLigne][nombreColonne];

		String indicePremiereColonne = ConfigurationJeu.INDICE_LIGNE;
		String[] indiceColonneSplit = indicePremiereColonne.split(",");

		for (int indiceLigne = 0; indiceLigne < nombreLigne; indiceLigne++) {
			blocs[indiceLigne][0] = new Bloc(indiceColonneSplit[indiceLigne], indiceLigne, 0, false, false, false);
		}

		String indicePremiereLigne = ConfigurationJeu.INDICE_COLONNE;
		String[] indiceLigneSplit = indicePremiereLigne.split(",");

		for (int indiceColonne = 0; indiceColonne < nombreLigne; indiceColonne++) {
			blocs[0][indiceColonne] = new Bloc(indiceLigneSplit[indiceColonne], indiceColonne, 0, false, false, false);
		}

		for (int indiceLigne = 1; indiceLigne < nombreLigne; indiceLigne++) {
			for (int indiceColonne = 1; indiceColonne < nombreColonne; indiceColonne++) {
				blocs[indiceLigne][indiceColonne] = new Bloc("  ", indiceLigne, indiceColonne, false, false, false);
			}
		}
	}

	public Bloc[][] getBlocs() {
		return blocs;
	}

	public int getNombreLigne() {
		return nombreLigne;
	}

	public int getNombreColonne() {
		return nombreColonne;
	}

	public Bloc getBloc(int ligne, int colonne) {
		return blocs[ligne][colonne];
	}

	public void afficheCarte() {
		for (int i = 0; i < ConfigurationJeu.NB_LIGNE-1; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE-1; j++) {
				if (i < 1 || j < 1) {
					System.out.print(ConfigurationJeu.ANSI_JAUNE+"[" + blocs[i][j].getValeur() + "]"+ConfigurationJeu.ANSI_RESET);
				}else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
					System.out.print("["+ConfigurationJeu.ANSI_NOIR+"::"+ConfigurationJeu.ANSI_RESET+"]");
				}else if (blocs[i][j].isTouche() == true) {
					if (!blocs[i][j].getValeur().equals("  ")) {
						System.out.print("["+ConfigurationJeu.ANSI_ROUGE+"//"+ConfigurationJeu.ANSI_RESET+"]");
					} else {
						System.out.print("[  ]");
					}
				} else {
					System.out.print("[" +ConfigurationJeu.ANSI_CYAN+ blocs[i][j].getValeur() +ConfigurationJeu.ANSI_RESET+ "]");
				}
			}
			System.out.println();
		}
	}

	public void afficheCarteEnnemi(boolean modeTriche) {
		if (modeTriche) {
			for (int i = 0; i < ConfigurationJeu.NB_LIGNE-1; i++) {
				for (int j = 0; j < ConfigurationJeu.NB_COLONNE-1; j++) {
					if (i < 1 || j < 1) {
						System.out.print(ConfigurationJeu.ANSI_JAUNE+"[" + blocs[i][j].getValeur() + "]"+ConfigurationJeu.ANSI_RESET);
					}else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
						System.out.print("["+ConfigurationJeu.ANSI_NOIR+"::"+ConfigurationJeu.ANSI_RESET+"]");
					}else if (blocs[i][j].isTouche() == true) {
						if (!blocs[i][j].getValeur().equals("  ")) {
							System.out.print("["+ConfigurationJeu.ANSI_ROUGE+"//"+ConfigurationJeu.ANSI_RESET+"]");
						} else {
							System.out.print("[  ]");
						}
					} else if(blocs[i][j].isEclaire() == true){
						System.out.print("[" +ConfigurationJeu.ANSI_VERT+ blocs[i][j].getValeur() + ConfigurationJeu.ANSI_RESET+"]");
					}else {
						System.out.print("[" +ConfigurationJeu.ANSI_CYAN+ blocs[i][j].getValeur() +ConfigurationJeu.ANSI_RESET+ "]");
					}

				}
				System.out.println();
			}
		} else {
			for (int i = 0; i < ConfigurationJeu.NB_LIGNE-1; i++) {
				for (int j = 0; j < ConfigurationJeu.NB_COLONNE-1; j++) {
					if (i < 1 || j < 1) {
						System.out.print(ConfigurationJeu.ANSI_JAUNE+"[" + blocs[i][j].getValeur() + "]"+ConfigurationJeu.ANSI_RESET);
					} else if (blocs[i][j].isTouche() == true && blocs[i][j].isCoule() == true) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else if (blocs[i][j].isTouche() == true) {
						if (!blocs[i][j].getValeur().equals("  ")) {
							System.out.print("["+ConfigurationJeu.ANSI_ROUGE+"//"+ConfigurationJeu.ANSI_RESET+"]");
						} else {
							System.out.print("[  ]");
						}
					} else if(blocs[i][j].isEclaire() == true){
						System.out.print("[" +ConfigurationJeu.ANSI_CYAN+ blocs[i][j].getValeur() + ConfigurationJeu.ANSI_RESET+"]");
					}else {
						System.out.print("[--]");
					}
				}
				System.out.println();
			}
		}
	}

//	public static void main(String[] main) {
//		Carte c = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
//		c.afficheCarte();
//	}

}

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

		String indicePremiereColonne = "  , 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15";
		String[] indiceColonneSplit = indicePremiereColonne.split(",");

		for (int indiceLigne = 0; indiceLigne < nombreLigne; indiceLigne++) {
			blocs[indiceLigne][0] = new Bloc(indiceColonneSplit[indiceLigne], indiceLigne, 0, false);
		}

		String indicePremiereLigne = "  , 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15";
		String[] indiceLigneSplit = indicePremiereLigne.split(",");

		for (int indiceColonne = 0; indiceColonne < nombreLigne; indiceColonne++) {
			blocs[0][indiceColonne] = new Bloc(indiceLigneSplit[indiceColonne], indiceColonne, 0, false);
		}

		for (int indiceLigne = 1; indiceLigne < nombreLigne; indiceLigne++) {
			for (int indiceColonne = 1; indiceColonne < nombreColonne; indiceColonne++) {
				blocs[indiceLigne][indiceColonne] = new Bloc("  ", indiceLigne, indiceColonne, false);
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
		for (int i = 0; i < ConfigurationJeu.NB_LIGNE; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE; j++) {
				System.out.print("[" + blocs[i][j].getValeur() + "]");
			}
			System.out.println();
		}
	}

	public void afficheCarteEnnemi(boolean modetriche) {
		if (modetriche) {
			for (int i = 0; i < ConfigurationJeu.NB_LIGNE; i++) {
				for (int j = 0; j < ConfigurationJeu.NB_COLONNE; j++) {
					System.out.print("[" + blocs[i][j].getValeur() + "]");
				}
				System.out.println();
			}
		} else {
			for (int i = 0; i < ConfigurationJeu.NB_LIGNE; i++) {
				for (int j = 0; j < ConfigurationJeu.NB_COLONNE; j++) {
					if (i < 1 || j < 1) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else if (blocs[i][j].isTouche()==true) {
						System.out.print("[" + blocs[i][j].getValeur() + "]");
					} else {
						System.out.print("[XX]");
					}
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] main) {
		Carte c = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
		c.afficheCarte();
	}

}

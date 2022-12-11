/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.grille;

import config.ConfigurationJeu;
import modele.mobile.Coordonnees;

public class Grille {
	private Bloc[][] blocs; // La grille est compose d'un tableau à deux dimensions de Bloc

	private int nombreLigne;
	private int nombreColonne;

	/**
	 * 
	 * @param nombreLigne   : Nombre de lignes pour notre grille de Partie
	 * @param nombreColonne Nombre de colonnes pour notre grille de Partie
	 */
	public Grille(int nombreLigne, int nombreColonne) {
		this.nombreLigne = nombreLigne;
		this.nombreColonne = nombreColonne;

		blocs = new Bloc[nombreLigne][nombreColonne];

		String indicePremiereColonne = ConfigurationJeu.INDICE_LIGNE;
//		String indicePremiereColonne = "  , 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16";
		String[] indiceColonneSplit = indicePremiereColonne.split(",");

		for (int indiceLigne = 0; indiceLigne < nombreLigne; indiceLigne++) {
			blocs[indiceLigne][0] = new Bloc(indiceColonneSplit[indiceLigne], indiceLigne, 0);
		}

		String indicePremiereLigne = ConfigurationJeu.INDICE_COLONNE;
		String[] indiceLigneSplit = indicePremiereLigne.split(",");

		for (int indiceColonne = 0; indiceColonne < nombreLigne; indiceColonne++) {
			blocs[0][indiceColonne] = new Bloc(indiceLigneSplit[indiceColonne], indiceColonne, 0);
		}

		for (int indiceLigne = 1; indiceLigne < nombreLigne; indiceLigne++) {
			for (int indiceColonne = 1; indiceColonne < nombreColonne; indiceColonne++) {
				blocs[indiceLigne][indiceColonne] = new Bloc("  ", indiceLigne, indiceColonne);
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

	public Bloc getBloc(Coordonnees c) {
		return blocs[c.getLigne()][c.getColonne()];
	}

//	public static void main(String[] main) {
//		Grille grille = new Grille(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
//		
//		AffichageCLI v = new AffichageCLI();
//		
//		v.afficheGrille(grille);
//		
//		v.afficheGrilleEnnemi(grille,true);
//	}

}

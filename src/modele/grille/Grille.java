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

/**
 * Cette classe sert a la creation d'un objet Grille. Cette grille est bas de
 * notre jeu de bataille navale.
 */

public class Grille {

	private Bloc[][] blocs; // La grille est compose d'un tableau a deux dimensions de Bloc

	private int nombreLigne;
	private int nombreColonne;

	/**
	 * Constructeur de la classe Grille
	 * 
	 * @param nombreLigne   : Nombre de lignes pour notre grille de Partie
	 * @param nombreColonne : Nombre de colonnes pour notre grille de Partie
	 */
	public Grille(int nombreLigne, int nombreColonne) {
		this.nombreLigne = nombreLigne;
		this.nombreColonne = nombreColonne;

		blocs = new Bloc[nombreLigne][nombreColonne];

		String indicePremiereColonne = ConfigurationJeu.INDICE_LIGNE;
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

	/**
	 * Methode servant a recuperer un bloc en fonction de deux indices
	 * 
	 * @param ligne   : indice de la ligne ou est le bloc
	 * @param colonne : indice de la colonne ou est le bloc
	 *
	 * @return Le bloc qui est a la ligne et colonne donne en parametre.
	 */
	public Bloc getBloc(int ligne, int colonne) {
		return blocs[ligne][colonne];
	}

	/**
	 * Methode servant a recuperer un bloc en fonction de ses coordonnees
	 * 
	 * @param c : indice de la ligne ou est le bloc
	 *
	 * @return Le bloc qui est a placer a la coordonnees donne en parametre.
	 */
	public Bloc getBloc(Coordonnees c) {
		return blocs[c.getLigne()][c.getColonne()];
	}

}

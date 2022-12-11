/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.mobile;

/**
 * Cette classe sert a la creation d'un objet Coordonnes. Les Coordonnees sont
 * utilises pour localiser nos Navires.
 */

public class Coordonnees {
	private int ligne;
	private int colonne;

	/**
	 * Constructeur de la classe Coordonnees
	 * 
	 * @param ligne   : premiere coordonnees
	 * @param colonne : seconde coordonnees
	 */
	public Coordonnees(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int x) {
		this.ligne = x;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int y) {
		this.colonne = y;
	}

	/**
	 * Methode servant a verifier si les coordonnees choisi sont dans les bornes de
	 * la grille
	 *
	 * @return vrai: si les coordonnees sont compris dans la grille et faux: sinon
	 */
	public boolean valideCoordonnees() {
		if (getLigne() >= 1 && getLigne() <= 14 && getColonne() >= 1 && getColonne() <= 14) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode servant a verifier si les coordonnees choisi sont compris dans les
	 * bornes de la grille
	 *
	 * @return vrai: si les coordonnees sont compris dans la grille et faux: sinon
	 */
	public boolean verifCoord(Coordonnees c2) {
		if (getLigne() == c2.getLigne() && getColonne() == c2.getColonne()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Coordonnees [ligne=" + ligne + ", colonne=" + colonne + "]";
	}

}

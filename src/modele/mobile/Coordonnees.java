/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.mobile;

public class Coordonnees {
	private int ligne;
	private int colonne;
	/**
	 * 
	 * @param ligne : indice 1 de la coordonnees
	 * @param colonne : indice 2 de la coordonees
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
	 * Methode servant a verifier que les coordonees sont dans les bornes de la grille  
	 * 
	 * @return : 
	 * 		true si les coordonnees sont dans les bornes
	 * 		false si les coordonnees ne sont pas dans les bornes
	 * 
	 */
	public boolean valideCoordonnees() {
		if (getLigne() >= 1 && getLigne() <= 14 && getColonne() >= 1 && getColonne() <= 14) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Methode servant a verifier que deux coordonnees sont identiques
	 * 
	 * @param c2 : Coordonnees que l'on souhaite compare
	 * 
	 * @return : 
	 * 		true si les coordonnees sont identiques
	 * 		false si les coordonnees sont différentes
	 * 
	 */
	public boolean verifCoord( Coordonnees c2) {
		if (getLigne() == c2.getLigne() && getColonne() == c2.getColonne()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode servant a connaitre la distance horizontale comprise entre deux coordonnees 
	 * 
	 * @param c1 : Coordonnees 1 que l'on souhaite compare
	 * @param c2 : Coordonnees 2 que l'on souhaite compare
	 * 
	 * @return : La distance horizontale
	 * 
	 */
	public int distanceX(Coordonnees c1, Coordonnees c2) {
		return (c1.getLigne() - c2.getLigne());
	}
	
	/**
	 * Methode servant a connaitre la distance verticale comprise entre deux coordonnees 
	 * 
	 * @param c1 : Coordonnees 1 que l'on souhaite compare
	 * @param c2 : Coordonnees 2 que l'on souhaite compare
	 * 
	 * @return : La distance verticale
	 * 
	 */
	public int distanceY(Coordonnees c1, Coordonnees c2) {
		return (c1.getColonne() - c2.getColonne());
	}

	@Override
	public String toString() {
		return "Coordonnees [ligne=" + ligne + ", colonne=" + colonne + "]";
	}

}

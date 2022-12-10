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

	public boolean valideCoordonnees() {
		if (getLigne() >= 1 && getLigne() <= 14 && getColonne() >= 1 && getColonne() <= 14) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifCoord( Coordonnees c2) {
		if (getLigne() == c2.getLigne() && getColonne() == c2.getColonne()) {
			return true;
		} else {
			return false;
		}
	}

	public int distanceX(Coordonnees c1, Coordonnees c2) {
		return (c1.getLigne() - c2.getLigne());
	}

	public int distanceY(Coordonnees c1, Coordonnees c2) {
		return (c1.getColonne() - c2.getColonne());
	}

	@Override
	public String toString() {
		return "Coordonnees [ligne=" + ligne + ", colonne=" + colonne + "]";
	}

}

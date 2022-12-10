/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.map;

public class Bloc {
	private String valeur;
	private int ligne;
	private int colonne;
	private boolean touche;

	public Bloc(String valeur, int ligne, int colonne, boolean touche) {
		this.valeur = valeur;
		this.ligne = ligne;
		this.colonne = ligne;
		this.touche = touche;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public boolean isTouche() {
		return touche;
	}

	public void setTouche(boolean touche) {
		this.touche = touche;
	}

	@Override
	public String toString() {
		return "Bloc [valeur=" + valeur + ", ligne=" + ligne + ", colonne=" + colonne + ", touche=" + touche + "]";
	}

}

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
	private boolean coule;
	private boolean eclaire;

	public Bloc(String valeur, int ligne, int colonne, boolean touche, boolean coule, boolean eclaire) {
		this.valeur = valeur;
		this.ligne = ligne;
		this.colonne = ligne;
		this.touche = touche;
		this.coule = coule;
		this.eclaire = eclaire;
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

	public boolean isCoule() {
		return coule;
	}

	public void setCoule(boolean coule) {
		this.coule = coule;
	}

	public boolean isEclaire() {
		return eclaire;
	}

	public void setEclaire(boolean eclaire) {
		this.eclaire = eclaire;
	}

	@Override
	public String toString() {
		return "Bloc [valeur=" + valeur + ", ligne=" + ligne + ", colonne=" + colonne + ", touche=" + touche
				+ ", coule=" + coule + ", eclaire=" + eclaire + "]";
	}

}

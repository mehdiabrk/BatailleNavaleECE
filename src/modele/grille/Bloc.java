/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.grille;

/**
 * Cette classe sert a la creqtion d'un objet Bloc. Notre Grille sera un tableau a deux dimenssion de blocs.
 */

public class Bloc {
	private String valeur;
	private int ligne;
	private int colonne;
	private boolean touche;
	private boolean coule;
	private boolean eclaire;
	private int idNavire;

	/**
	 * Constructeur de la classe Bloc 
	 * 
	 * @param valeur  : Chaque bloc possede une valeur qui lui est propre et qui est
	 *                utilise lors de son affichage
	 * @param ligne   : Un bloc est identifiable par deux indices, la ligne est le
	 *                premier
	 * @param colonne : Un bloc est identifiable par deux indices, la colonne est le
	 *                second
	 */
	public Bloc(String valeur, int ligne, int colonne) {
		this.valeur = valeur;
		this.ligne = ligne;
		this.colonne = ligne;
		this.touche = false;
		this.coule = false;
		this.eclaire = false;
		this.idNavire = 0;
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

	public int getIdNavire() {
		return idNavire;
	}

	public void setIdNavire(int idNavire) {
		this.idNavire = idNavire;
	}

	@Override
	public String toString() {
		return "Bloc [valeur=" + valeur + ", ligne=" + ligne + ", colonne=" + colonne + ", touche=" + touche
				+ ", coule=" + coule + ", eclaire=" + eclaire + "]";
	}

}

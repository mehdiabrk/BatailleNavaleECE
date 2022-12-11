/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.mobile;

import java.util.ArrayList;

public class Navire {
	protected int id;
	protected int taille;
	protected int impactMissile;
	protected ArrayList<Coordonnees> listeCoordonnees;
	protected boolean coule;
	protected int direction;
	protected boolean touche;

	/**
	 * @param id : (1 : CUIRASSE | 2: CROISEUR | 3: DESTROYER | 4: SOUS-MARIN)
	 * @param taille : (7 : CUIRASSE | 5: CROISEUR | 3: DESTROYER | 1: SOUS-MARIN)
	 * @param impactMissile : (9 : CUIRASSE | 4: CROISEUR | 1: DESTROYER | 1: SOUS-MARIN)
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Navire(ArrayList<Coordonnees> listeCoordonnees, int direction) {
		this.listeCoordonnees = listeCoordonnees;
		this.coule = false;
		this.direction = direction;
		this.touche = false;
	}

	public int getId() {
		return id;
	}

	public int getTaille() {
		return taille;
	}

	public int getImpactMissile() {
		return impactMissile;
	}

	public ArrayList<Coordonnees> getListeCoordonnees() {
		return listeCoordonnees;
	}

	public boolean isCoule() {
		return coule;
	}

	public void setCoule(boolean coule) {
		this.coule = coule;
	}

	public int getDirection() {
		return direction;
	}

	public boolean isTouche() {
		return touche;
	}

	public void setTouche(boolean touche) {
		this.touche = touche;
	}

	public Coordonnees futuresCoordonneesValid(int directionDeplacement) {
		int coordLigne = 0;
		int coordColonne = 0;
		switch (directionDeplacement) {
		case 0: { // gauche
			coordLigne = listeCoordonnees.get(0).getLigne();
			coordColonne = listeCoordonnees.get(0).getColonne() - 1;

			break;
		}
		case 1: { // haut
			coordLigne = listeCoordonnees.get(0).getLigne() - 1;
			coordColonne = listeCoordonnees.get(0).getColonne();
			break;
		}
		case 2: { // droite
			coordLigne = listeCoordonnees.get(taille - 1).getLigne();
			coordColonne = listeCoordonnees.get(taille - 1).getColonne() + 1;
			break;
		}
		case 3: { // bas
			coordLigne = listeCoordonnees.get(taille - 1).getLigne() + 1;
			coordColonne = listeCoordonnees.get(taille - 1).getColonne();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
		return new Coordonnees(coordLigne, coordColonne);
	}

	public boolean deplacable(int directionDeplacement) {
		Coordonnees c = futuresCoordonneesValid(directionDeplacement);
		if (c.valideCoordonnees()) {
			System.out.println("Methode deplacable : True");
			return true;
		}
		System.out.println("Methode deplacable : False");
		return false;
	}

	private Coordonnees MajCoordonnees(int directionDeplacement, Coordonnees c) {
		int coordLigne = 0;
		int coordColonne = 0;
		switch (directionDeplacement) {
		case 0: { // gauche
			coordLigne = c.getLigne();
			coordColonne = c.getColonne() - 1;
			break;
		}
		case 1: { // haut
			coordLigne = c.getLigne() - 1;
			coordColonne = c.getColonne();
			break;
		}
		case 2: { // droite
			coordLigne = c.getLigne();
			coordColonne = c.getColonne() + 1;
			break;
		}
		case 3: { // bas
			coordLigne = c.getLigne() + 1;
			coordColonne = c.getColonne();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + directionDeplacement);
		}

		return new Coordonnees(coordLigne, coordColonne);
	}

	public void deplacer(int directionDeplacement) {
		if (deplacable(directionDeplacement)) {
			for (int i = 0; i < listeCoordonnees.size(); i++) {
				Coordonnees c = this.listeCoordonnees.get(i);
				this.listeCoordonnees.set(i, MajCoordonnees(directionDeplacement, c));
			}
		}
	}

	@Override
	public String toString() {
		return "\nNavire [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + "]";
	}

//	public static void main(String[] args) {
//
//		Coordonnees c1 = new Coordonnees(1, 1);
//		Coordonnees c2 = new Coordonnees(1, 2);
//		Coordonnees c3 = new Coordonnees(1, 3);
//
//		ArrayList<Coordonnees> c = new ArrayList<Coordonnees>();
//		c.add(c1);
//		c.add(c2);
//		c.add(c3);
//
//		Navire n = new Navire(1, 3, 1, c, false, 0);
//
//		int deplacementDirection = 3;
//
//		System.out.println(n.getCoordonnees());
//
//		n.deplacer(deplacementDirection);
//
//		System.out.println(n.getCoordonnees());
//
//		n.tirer();
//		n.estEndommage();
//
//	}

}

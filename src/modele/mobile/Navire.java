/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.mobile;

/**
 * Cette classe sert a la creqtion d'un objet Navire. Un joueur possede 7 Navires.
 */

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
	 * Constructeur de la classe Navire 
	 * @param id               : (1 : CUIRASSE | 2: CROISEUR | 3: DESTROYER | 4:
	 *                         SOUS-MARIN)
	 * @param taille           : (7 : CUIRASSE | 5: CROISEUR | 3: DESTROYER | 1:
	 *                         SOUS-MARIN)
	 * @param impactMissile    : (9 : CUIRASSE | 4: CROISEUR | 1: DESTROYER | 1:
	 *                         SOUS-MARIN)
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule            : (Vrai : Si tous les blocs du Navire sont coulees |
	 *                         Faux : sinon)
	 * @param direction        : (0 : HORIZONTALE | 1 : VERTICALE)
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
	/**
	 * Methode servant a renvoyer la coordonnees du bloc en tete du Navire apres son deplacement dans la direction donnee
	 * 
	 * @param directionDeplacement : direction dans laquelle il faut deplacer le  Navire ( 0 : gauche | 1: haut | 2 : droite | 3 : bas )
	 *
	 * @return Les coordonnees du bloc en tete du navire apres son deplacement.
	 */
	public Coordonnees validationFuturesCoordonnees(int directionDeplacement) {
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
			throw new IllegalArgumentException("Unexpected value: " + directionDeplacement);
		}
		return new Coordonnees(coordLigne, coordColonne);
	}

	/**
	 * Methode servant a savoir si un Navire et deplacable dans la direction donnee en parametre
	 * 
	  * @param directionDeplacement   : direction dans laquelle il faut deplacer le  Navire ( 0 : gauche | 1: haut | 2 : droite | 3 : bas )
	 *
	 * @return Vrai : si le navire peut etre deplace dans la direction donnee 
	 */
	public boolean deplacable(int directionDeplacement) {
		Coordonnees c = validationFuturesCoordonnees(directionDeplacement);
		if (c.valideCoordonnees()) {
			return true;
		}
		return false;
	}

	/**
	 * Methode servant a recuperer les coordonnees donnee en parametre apres le
	 * deplacement dans la direction donnee en parametre
	 * 
	 * @param directionDeplacement   : direction dans laquelle il faut deplacer le  Navire ( 0 : gauche | 1: haut | 2 : droite | 3 : bas )
	 *                            
	 * @param c : Coordonnee du Navire avant deplacement
	 *
	 * @return La coordonnees du Navire après deplacement
	 */
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

	/**
	 * Methode servant a deplacer un Navire dans la direction donne en parametre
	 * 
	 * @param directionDeplacement : direction dans laquelle il faut deplacer le
	 *                             Navire ( 0 : gauche | 1: haut | 2 : droite | 3 :
	 *                             bas )
	 *
	 */
	public void deplacer(int directionDeplacement) {
		if (deplacable(directionDeplacement)) {
			for (int i = 0; i < listeCoordonnees.size(); i++) {
				Coordonnees c = listeCoordonnees.get(i);
				listeCoordonnees.set(i, MajCoordonnees(directionDeplacement, c));
			}
		}
	}

	@Override
	public String toString() {
		return "Navire [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

}

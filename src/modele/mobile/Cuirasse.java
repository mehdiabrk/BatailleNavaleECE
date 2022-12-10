/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.mobile;

import java.util.ArrayList;

import config.ConfigurationJeu;

public class Cuirasse extends Navire {

	/**
	 * @param id
	 * @param taille
	 * @param impactMissile
	 * @param listeCoordonnees
	 * @param touche
	 * @param direction
	 */
	public Cuirasse(int id, int taille, int impactMissile, ArrayList<Coordonnees> coordonnees, boolean touche,
			int direction) {
		super(id, taille, impactMissile, coordonnees, touche, direction);
		this.id = ConfigurationJeu.ID_CUIRASSE;
		this.taille = ConfigurationJeu.TAILLE_CUIRASSE;
		this.impactMissile = ConfigurationJeu.IMPACT_CROISSEUR;

	}

	@Override
	public String toString() {
		return "Cuirasse [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", touche=" + touche + ", direction=" + direction + "]";
	}

	public static void main(String[] args) {

		Coordonnees c1 = new Coordonnees(1, 1);
		Coordonnees c2 = new Coordonnees(1, 2);
		Coordonnees c3 = new Coordonnees(1, 3);

		ArrayList<Coordonnees> co = new ArrayList<Coordonnees>();
		co.add(c1);
		co.add(c2);
		co.add(c3);

		Cuirasse c = new Cuirasse(0, 3, 1, co, false, 0);

		int deplacementDirection = 3;

		System.out.println(c.getListeCoordonnees());

		c.deplacer(deplacementDirection);

		System.out.println(c.getListeCoordonnees());

	}

}

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

public class Destroyer extends Navire {

	/**
	 * @param id
	 * @param taille
	 * @param impactMissile
	 * @param listeCoordonnees
	 * @param coule
	 * @param direction
	 */
	public Destroyer(int id, int taille, int impactMissile, ArrayList<Coordonnees> coordonnees, boolean coule,
			int direction, boolean touche) {
		super(id, taille, impactMissile, coordonnees, coule, touche, direction);
		this.id = ConfigurationJeu.ID_DESTROYER;
		this.taille = ConfigurationJeu.TAILLE_DESTROYER;
		this.impactMissile = ConfigurationJeu.IMPACT_DESTROYER;
	}

	@Override
	public String toString() {
		return "Destroyer [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

}

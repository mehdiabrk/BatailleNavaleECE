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

public class SousMarin extends Navire {

	/**
	 * @param id
	 * @param taille
	 * @param impactMissile
	 * @param listeCoordonnees
	 * @param coule
	 * @param direction
	 */
	public SousMarin(int id, int taille, int impactMissile, ArrayList<Coordonnees> coordonnees, boolean coule,
			boolean touche, int direction) {
		super(id, taille, impactMissile, coordonnees, coule, touche, direction);
		this.id = ConfigurationJeu.ID_SOUSMARIN;
		this.taille = ConfigurationJeu.TAILLE_SOUSMARIN;
		this.impactMissile = ConfigurationJeu.IMPACT_SOUSMARIN;
	}

	@Override
	public String toString() {
		return "SousMarin [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

}

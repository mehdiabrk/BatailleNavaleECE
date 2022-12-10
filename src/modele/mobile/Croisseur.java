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

public class Croisseur extends Navire {

	/**
	 * @param id
	 * @param taille
	 * @param impactMissile
	 * @param listeCoordonnees
	 * @param touche
	 * @param direction
	 */
	public Croisseur(int id, int taille, int impactMissile, ArrayList<Coordonnees> coordonnees, boolean touche,
			int direction) {
		super(id, taille, impactMissile, coordonnees, touche, direction);
		this.id = ConfigurationJeu.ID_CROISSEUR;
		this.taille = ConfigurationJeu.TAILLE_CROISSEUR;
		this.impactMissile = ConfigurationJeu.IMPACT_CROISSEUR;
	}

	@Override
	public String toString() {
		return "Croisseur [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", touche=" + touche + ", direction=" + direction + "]";
	}

}

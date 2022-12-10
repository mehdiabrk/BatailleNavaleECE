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

	private boolean fuseeEclairante;

	/**
	 * @param id
	 * @param taille
	 * @param impactMissile
	 * @param listeCoordonnees
	 * @param touche
	 * @param direction
	 */
	public Destroyer(int id, int taille, int impactMissile, ArrayList<Coordonnees> coordonnees, boolean touche,
			int direction, boolean fuseeEclairante) {
		super(id, taille, impactMissile, coordonnees, touche, direction);
		this.id = ConfigurationJeu.ID_DESTROYER;
		this.taille = ConfigurationJeu.TAILLE_DESTROYER;
		this.impactMissile = ConfigurationJeu.IMPACT_DESTROYER;
		this.fuseeEclairante = true;
	}

	public boolean isFuseeEclairante() {
		return fuseeEclairante;
	}

	public void tirerFuseeEclairante() {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "Destroyer [fuseeEclairante=" + fuseeEclairante + ", id=" + id + ", taille=" + taille
				+ ", impactMissile=" + impactMissile + ", listeCoordonnees=" + listeCoordonnees + ", touche=" + touche
				+ ", direction=" + direction + "]";
	}

}

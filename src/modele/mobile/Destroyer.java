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
	 * @param id : ( 3: DESTROYER )
	 * @param taille : ( 3: DESTROYER )
	 * @param impactMissile : ( 1: DESTROYER )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Destroyer(ArrayList<Coordonnees> listeCoordonnees, int direction) {
		super(listeCoordonnees, direction);
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

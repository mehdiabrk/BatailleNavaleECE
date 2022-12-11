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
	 * @param id : ( 2: CROISEUR )
	 * @param taille : ( 5: CROISEUR )
	 * @param impactMissile : ( 4: CROISEUR )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Croisseur(ArrayList<Coordonnees> listeCoordonnees, int direction) {
		super(listeCoordonnees, direction);
		this.id = ConfigurationJeu.ID_CROISSEUR;
		this.taille = ConfigurationJeu.TAILLE_CROISSEUR;
		this.impactMissile = ConfigurationJeu.IMPACT_CROISSEUR;
	}

	@Override
	public String toString() {
		return "Croisseur [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

}

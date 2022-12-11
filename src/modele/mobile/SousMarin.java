/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.mobile;

import java.util.ArrayList;

/**
 * Cette classe sert a la creation d'un SousMarin. Un SousMarin est un type precis de Navire et chaque joueur en possede 4.
 */

import config.ConfigurationJeu;

/**
 * Cette classe sert a la creation d'un SousMarin. Un SousMarin est un type precis de Navire et chaque joueur en possede 4.
 */

public class SousMarin extends Navire {

	/**
	 * Premier Constructeur de la classe SousMarin : Servant lors d'une nouvelle partie
	 * 
	 * @param id : ( 4: SOUS-MARIN )
	 * @param taille : ( 1: SOUS-MARIN )
	 * @param impactMissile : ( 1: SOUS-MARIN )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public SousMarin(ArrayList<Coordonnees> listeCoordonnees, int direction) {
		super(listeCoordonnees, direction);
		this.id = ConfigurationJeu.ID_SOUSMARIN;
		this.taille = ConfigurationJeu.TAILLE_SOUSMARIN;
		this.impactMissile = ConfigurationJeu.IMPACT_SOUSMARIN;
	}
	
	/**
	 * Premier Constructeur de la classe SousMarin : Servant lors d'une nouvelle partie
	 * 
	 * @param id : ( 4: SOUS-MARIN )
	 * @param taille : ( 1: SOUS-MARIN )
	 * @param impactMissile : ( 1: SOUS-MARIN )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public SousMarin( int id, int taille, int impactMissile , Boolean coule, int direction ,Boolean touche ,ArrayList<Coordonnees> listeCoordonnees) {
		super(listeCoordonnees, direction);
		this.id = id;
		this.taille = taille;
		this.impactMissile = impactMissile;
		this.coule = coule ;
		this.direction = direction;
		this.touche = touche;
		this.listeCoordonnees = listeCoordonnees;
	}

	@Override
	public String toString() {
		return "SousMarin [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}
}

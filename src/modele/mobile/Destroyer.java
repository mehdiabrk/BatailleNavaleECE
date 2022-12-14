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

/**
 * Cette classe sert a la creation d'un Destroyer. Un Destroyer est un type precis de Navire et chaque joueur en possede 3.
 */

public class Destroyer extends Navire {
	
	/**
	 * Premier Constructeur de la classe Destroyer : Servant lors d'une nouvelle partie
	 * 
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
	
	/**
	 * Premier Constructeur de la classe Destroyer : Servant lors d'une nouvelle partie
	 * @param id : ( 3: DESTROYER )
	 * @param taille : ( 3: DESTROYER )
	 * @param impactMissile : ( 1: DESTROYER )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Destroyer( int id, int taille, int impactMissile , Boolean coule, int direction ,Boolean touche ,ArrayList<Coordonnees> listeCoordonnees) {
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
		return "Destroyer [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

}

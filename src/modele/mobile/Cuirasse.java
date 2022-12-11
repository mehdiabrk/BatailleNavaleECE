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
 * Cette classe sert a la creation d'un Cuirrasse. Un Cuirasse est un type precis de Navire et chaque joueur en possede un unique.
 */

public class Cuirasse extends Navire {

	/**
	 * Premier Constructeur de la classe Cuirasse : Servant lors d'une nouvelle partie
	 * 
	 * @param id               : (1 : CUIRASSE )
	 * @param taille           : (7 : CUIRASSE )
	 * @param impactMissile    : (9 : CUIRASSE )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule            : (True : Si tous les blocs du Navire sont coulees |
	 *                         False : sinon)
	 * @param direction        : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Cuirasse(ArrayList<Coordonnees> listeCoordonnees, int direction) {
		super(listeCoordonnees, direction);
		this.id = ConfigurationJeu.ID_CUIRASSE;
		this.taille = ConfigurationJeu.TAILLE_CUIRASSE;
		this.impactMissile = ConfigurationJeu.IMPACT_CROISSEUR;

	}

	/**
	 * Second Constructeur de la classe Cuirasse : Servant lors du chargement d'une ancienne partie
	 * 
	 * @param id               : (1 : CUIRASSE )
	 * @param taille           : (7 : CUIRASSE )
	 * @param impactMissile    : (9 : CUIRASSE )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule            : (True : Si tous les blocs du Navire sont coulees |
	 *                         False : sinon)
	 * @param direction        : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Cuirasse( int id, int taille, int impactMissile , Boolean coule, int direction ,Boolean touche ,ArrayList<Coordonnees> listeCoordonnees) {
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
		return "Cuirasse [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

}

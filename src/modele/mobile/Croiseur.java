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
 * Cette classe sert a la creation d'un Croiseur. Un Croiseur est un type precis de Navire et chaque joueur en possede deux.
 */

public class Croiseur extends Navire {

	/**
	 * Premier Constructeur de la classe Croiseur : Servant lors d'une nouvelle partie
	 * 
	 * @param id : ( 2: CROISEUR )
	 * @param taille : ( 5: CROISEUR )
	 * @param impactMissile : ( 4: CROISEUR )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Croiseur(ArrayList<Coordonnees> listeCoordonnees, int direction) {
		super(listeCoordonnees, direction);
		this.id = ConfigurationJeu.ID_CROISSEUR;
		this.taille = ConfigurationJeu.TAILLE_CROISSEUR;
		this.impactMissile = ConfigurationJeu.IMPACT_CROISSEUR;
	}
	
	
	/**
	 * Second Constructeur de la classe Croiseur :Servant lors du chargement d'une ancienne partie
	 * 
	 * @param id : ( 2: CROISEUR )
	 * @param taille : ( 5: CROISEUR )
	 * @param impactMissile : ( 4: CROISEUR )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Croiseur( int id, int taille, int impactMissile , Boolean coule, int direction ,Boolean touche ,ArrayList<Coordonnees> listeCoordonnees) {
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
		return "Croiseur [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

}

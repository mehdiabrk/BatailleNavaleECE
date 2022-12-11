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

public class Cuirasse extends Navire {

	/**
	 * @param id : (1 : CUIRASSE )
	 * @param taille : (7 : CUIRASSE )
	 * @param impactMissile : (9 : CUIRASSE )
	 * @param listeCoordonnees : Liste des coordonnes des blocs du Navire
	 * @param coule : (True : Si tous les blocs du Navire sont coulees | False : sinon)
	 * @param direction : (0 : HORIZONTALE | 1 : VERTICALE)
	 */
	public Cuirasse( ArrayList<Coordonnees> listeCoordonnees, int direction) {
		super(listeCoordonnees, direction);
		this.id = ConfigurationJeu.ID_CUIRASSE;
		this.taille = ConfigurationJeu.TAILLE_CUIRASSE;
		this.impactMissile = ConfigurationJeu.IMPACT_CROISSEUR;

	}

	@Override
	public String toString() {
		return "Cuirasse [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}

//	public static void main (String [] args ) {
//		
//		ArrayList<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();
//		
//		for(int i=0 ; i<ConfigurationJeu.TAILLE_CUIRASSE ; i++) {
//			listeCoordonnees.add(OutilsJeu.generateurCoordonneesAleatoire());
//		}
//		
//		Cuirasse cu = new Cuirasse(listeCoordonnees);
//		
//		System.out.println(cu.toString());
//	}
//

//	public static void main(String[] args) {
//
//		Coordonnees c1 = new Coordonnees(1, 1);
//		Coordonnees c2 = new Coordonnees(1, 2);
//		Coordonnees c3 = new Coordonnees(1, 3);
//
//		ArrayList<Coordonnees> co = new ArrayList<Coordonnees>();
//		co.add(c1);
//		co.add(c2);
//		co.add(c3);
//
//		Cuirasse c = new Cuirasse(0, 3, 1, co, false, 0);
//
//		int deplacementDirection = 3;
//
//		System.out.println(c.getListeCoordonnees());
//
//		c.deplacer(deplacementDirection);
//
//		System.out.println(c.getListeCoordonnees());
//
//	}

}

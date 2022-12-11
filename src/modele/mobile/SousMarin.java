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

	@Override
	public String toString() {
		return "SousMarin [id=" + id + ", taille=" + taille + ", impactMissile=" + impactMissile + ", listeCoordonnees="
				+ listeCoordonnees + ", coule=" + coule + ", direction=" + direction + ", touche=" + touche + "]";
	}
	
//	public static void main (String [] args ) {
//		
//		ArrayList<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();
//		
//		for(int i=0 ; i<ConfigurationJeu.TAILLE_SOUSMARIN ; i++) {
//			listeCoordonnees.add(OutilsJeu.generateurCoordonneesAleatoire());
//		}
//		
//		SousMarin sm = new SousMarin(listeCoordonnees);
//		
//		System.out.println(sm.toString());
//	}

}

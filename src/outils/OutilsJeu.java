/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * EceBatailleNavale
 *
 * 4 déc. 2022
 */
package outils;

import java.util.Random;

import config.ConfigurationJeu;
import modele.mobile.Coordonnees;

public class OutilsJeu {
	public static int directionAleatoire() {
		Random r = new Random();
		return (r.nextInt(2));
	}

	public static int valeurAleatoire(int borneSup) {
		Random r = new Random();

		return (r.nextInt(borneSup) + 1);
	}

	public static int valeurZeroAleatoire(int borneSup) {
		Random r = new Random();

		return (r.nextInt(borneSup));
	}

	public static Coordonnees generateurCoordonneesAleatoire() {
		int indiceLigne = valeurAleatoire(ConfigurationJeu.NB_LIGNE-2);

		int indiceColonne = valeurAleatoire(ConfigurationJeu.NB_COLONNE-2);

		return new Coordonnees(indiceLigne, indiceColonne);

	}
	
	public static Coordonnees traduitCoordonnees(String coordString) {
		String[] tabCoordString = coordString.split(" "); 
		
		String indiceLigneString = tabCoordString[0];
		String indiceColonneString = tabCoordString[1];
		int indiceColonne = Integer.parseInt(indiceColonneString);

		Coordonnees c = null;

		int i = 0;

		for (String string : ConfigurationJeu.INDICE_LIGNE.split(",")) {
			if (indiceLigneString.equals(string)) {
				c = new Coordonnees(i, indiceColonne);
			}
			i++;
		}

		return c;
	}
	
	public static int idNavireAleatoire() {
		return OutilsJeu.valeurAleatoire(4);
	}
	
	public static int numNavireDeplacer() {
		return OutilsJeu.valeurZeroAleatoire(9);
	}
}

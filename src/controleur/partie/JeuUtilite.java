/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package controleur.partie;

import java.util.Random;

public class JeuUtilite {
	
	public static int directionAleatoire() {
		Random r = new Random();
        return (r.nextInt(2));
	}
	
	

}

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
 * Cette classe sert a la creqtion d'un objet Flotte. La Flotte comprend tous les navires d'un joueur.
 */

public class Flotte {
	private ArrayList<Navire> listeNavire;

	/**
	 * Constructeur de la classe Flotte
	 * 
	 * @param listeNavire : Liste comprenant la totalite des navires
	 * 
	 */
	public Flotte(ArrayList<Navire> flotte) {
		this.listeNavire = flotte;
	}

	public ArrayList<Navire> getListeNavire() {
		return listeNavire;
	}

	@Override
	public String toString() {
		return "Flotte [listeNavire=" + listeNavire + "]";
	}

}

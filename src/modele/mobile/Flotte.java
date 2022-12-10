/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package modele.mobile;

import java.util.ArrayList;

public class Flotte {
	private ArrayList<Navire> listeNavire;

	/**
	 * @param listeNavire
	 */
	public Flotte(ArrayList<Navire> flotte) {
		super();
		this.listeNavire = flotte;
	}

	public ArrayList<Navire> getListeNavire() {
		return listeNavire;
	}

	@Override
	public String toString() {
		return "Flotte [listeNavire=" + listeNavire + "]";
	}
	
//	public static void main(String []args) {
//		Coordonnees cuirasse1 = new Coordonnees(1, 1);
//		Coordonnees cuirasse2 = new Coordonnees(1, 2);
//		Coordonnees cuirasse3 = new Coordonnees(1, 3);
//		Coordonnees cuirasse4 = new Coordonnees(1, 4);
//		Coordonnees cuirasse5 = new Coordonnees(1, 5);
//		Coordonnees cuirasse6 = new Coordonnees(1, 6);
//		Coordonnees cuirasse7 = new Coordonnees(1, 7);
//
//		ArrayList<Coordonnees> coordonneesCuirasse = new ArrayList<Coordonnees>();
//		coordonneesCuirasse.add(cuirasse1);
//		coordonneesCuirasse.add(cuirasse2);
//		coordonneesCuirasse.add(cuirasse3);
//		coordonneesCuirasse.add(cuirasse4);
//		coordonneesCuirasse.add(cuirasse5);
//		coordonneesCuirasse.add(cuirasse6);
//		coordonneesCuirasse.add(cuirasse7);
//
//		Navire cuirasse = new Navire(1, 3, 1, coordonneesCuirasse, false, 0);
//	
//	}

}

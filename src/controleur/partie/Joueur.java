/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package controleur.partie;

import java.util.ArrayList;
import java.util.Random;

import config.ConfigurationJeu;
import outils.OutilsJeu;
import modele.grille.Bloc;
import modele.grille.Grille;
import modele.mobile.Coordonnees;
import modele.mobile.Croisseur;
import modele.mobile.Cuirasse;
import modele.mobile.Destroyer;
import modele.mobile.Flotte;
import modele.mobile.Navire;
import modele.mobile.SousMarin;

public class Joueur {

	private Grille grilleJoueur;
	private Grille grilleEnnemi;

	private ArrayList<Coordonnees> listeCoordonneesFlotte;

	private Flotte flotte;

	private int NbBlocTouche;
	private int NbNavireCoule;

	public Joueur(Grille carteJoueur, Grille carteEnnemi) {
		this.grilleJoueur = carteJoueur;
		this.grilleEnnemi = carteEnnemi;
		this.listeCoordonneesFlotte = new ArrayList<Coordonnees>();

		this.flotte = generationFlotte(carteJoueur);
		placementFlotte();

		NbBlocTouche = 0;
		NbNavireCoule = 0;

	}

	public Grille getGrilleJoueur() {
		return grilleJoueur;
	}

	public Grille getGrilleEnnemi() {
		return grilleEnnemi;
	}

	public void setGrilleJoueur(Grille carteJoueur) {
		this.grilleJoueur = carteJoueur;
	}

	public Flotte getFlotte() {
		return flotte;
	}

	public int getNbBlocTouche() {
		return NbBlocTouche;
	}

	public int getNbNavireCoule() {
		return NbNavireCoule;
	}

	public void modifValueBlocs(Coordonnees c, int id) {

		int BlocLigne = c.getLigne();
		int BlocColonne = c.getColonne();
		String value = null;

		switch (id) {
		case 1: {
			value = ConfigurationJeu.SIGNE_CUIRASSE;
			break;
		}
		case 2: {
			value = ConfigurationJeu.SIGNE_CROISSEUR;
			break;
		}
		case 3: {
			value = ConfigurationJeu.SIGNE_DESTROYER;
			break;
		}
		case 4: {
			value = ConfigurationJeu.SIGNE_SOUSMARIN;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + id);
		}

		Bloc b = grilleJoueur.getBloc(BlocLigne, BlocColonne);

		b.setValeur(value);
	}

	public void initializeNavire(Navire n) {
		int id = n.getId();

		for (Coordonnees c : n.getListeCoordonnees()) {
			modifValueBlocs(c, id);
		}
	}

	public void placementFlotte() {
		for (Navire n : flotte.getListeNavire()) {
			initializeNavire(n);
		}
	}

	public boolean verifBoolean(int verif, int taille) {
		if (verif != taille) {
			System.out.println("VOTRE BATEAU NE PEUT PAS RENTRER !!!");
			return false;
		} else {
			System.out.println("VOTRE BATEAU PEUT RENTRER !!!");
			return true;
		}
	}

	public boolean verifExistFlotte(Coordonnees c2) {
		int verif = 0;
		for (Coordonnees c1 : listeCoordonneesFlotte) {
			if (c1.verifCoord(c2)) {
				verif++;
			}
		}
		if (verif > 0) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Coordonnees> checkCoordonneesDisponible(int CoordLigne, int CoordColonne, int taille,
			int direction) {
		ArrayList<Coordonnees> c = new ArrayList<Coordonnees>();
		int verif = 0;

		switch (direction) {
		case 0: {
			if (ConfigurationJeu.NB_COLONNE > CoordColonne + taille) {
				for (int i = 0; i < taille; i++) {
					if (!verifExistFlotte(new Coordonnees(CoordLigne, (CoordColonne + i)))) {
						verif++;
						c.add(new Coordonnees(CoordLigne, CoordColonne + i));
					}
				}
			} else {
				verif = 0;
			}
			break;
		}
		case 1: {
			if (ConfigurationJeu.NB_LIGNE > CoordLigne + taille) {
				for (int i = 0; i < taille; i++) {
					if (!verifExistFlotte(new Coordonnees((CoordLigne + i), CoordColonne))) {
						verif++;
						c.add(new Coordonnees(CoordLigne + i, CoordColonne));
					}
				}

			} else {
				verif = 0;
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}

		if (verifBoolean(verif, taille)) {
			return c;
		} else {
			return null;
		}

	}

	public ArrayList<Coordonnees> listeCoordonneesAleatoire(int taille, int direction) {
		Random r = new Random();
		int CordLigne = r.nextInt(15) + 1;
		int CordColonne = r.nextInt(15) + 1;

		while (checkCoordonneesDisponible(CordLigne, CordColonne, taille, direction) == null) {
			CordLigne = r.nextInt(15) + 1;
			CordColonne = r.nextInt(15) + 1;
		}
		System.out.println("COORD DE DEPART CHOISI: LIGNE=" + CordLigne + " || COLONNE=" + CordColonne + "\n");
		ArrayList<Coordonnees> listeCoordonnees = checkCoordonneesDisponible(CordLigne, CordColonne, taille, direction);

		for (Coordonnees coordonnees : listeCoordonnees) {
			listeCoordonneesFlotte.add(coordonnees);
		}

		System.out.println("LISTE COORDONNEES FLOTTE : " + listeCoordonneesFlotte.toString());
		System.out.println("SIZE : " + listeCoordonneesFlotte.size());

		return listeCoordonnees;

	}

	public ArrayList<Coordonnees> generationCoordonnees(int taille, int direction) {
		ArrayList<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();

		listeCoordonnees = listeCoordonneesAleatoire(taille, direction);

		return listeCoordonnees;
	}

	public Cuirasse generationCuirasse() {
		int direction = OutilsJeu.directionAleatoire();
		System.out.println("\nLA DIRECTION DU CUIRASSE : " + direction);
		ArrayList<Coordonnees> listeCoordCuirasse = generationCoordonnees(ConfigurationJeu.TAILLE_CUIRASSE, direction);

		Cuirasse cuirasse = new Cuirasse(listeCoordCuirasse, direction);

		return cuirasse;

	}

	public Croisseur generationCroisseur() {
		int direction = OutilsJeu.directionAleatoire();
		System.out.println("\nLA DIRECTION DU CROISSEUR : " + direction);
		ArrayList<Coordonnees> listeCoordCroisseur = generationCoordonnees(ConfigurationJeu.TAILLE_CROISSEUR,
				direction);

		Croisseur croisseur = new Croisseur(listeCoordCroisseur, direction);

		return croisseur;

	}

	public Destroyer generationDestroyer() {
		int direction = OutilsJeu.directionAleatoire();
		System.out.println("\nLA DIRECTION DU DESTROYER : " + direction);
		ArrayList<Coordonnees> listeCoordDestroyer = generationCoordonnees(ConfigurationJeu.TAILLE_DESTROYER,
				direction);

		Destroyer destroyer = new Destroyer(listeCoordDestroyer, direction);

		return destroyer;
	}

	public SousMarin generationSousMarin() {
		int direction = OutilsJeu.directionAleatoire();
		System.out.println("\nLA DIRECTION DU SOUS-MARIN : " + direction);
		ArrayList<Coordonnees> listeCoordSousMarin = generationCoordonnees(ConfigurationJeu.TAILLE_SOUSMARIN,
				direction);

		SousMarin sousMarin = new SousMarin(listeCoordSousMarin, direction);

		return sousMarin;
	}

	public Flotte generationFlotte(Grille carteJoueur) {
		ArrayList<Navire> listeNavire = new ArrayList<Navire>();

		listeNavire.add(generationCuirasse());

		for (int i = 1; i <= 2; i++) {
			listeNavire.add(generationCroisseur());
		}

		for (int i = 1; i <= 3; i++) {
			listeNavire.add(generationDestroyer());
		}

		for (int i = 1; i <= 4; i++) {
			listeNavire.add(generationSousMarin());
		}
		Flotte f = new Flotte(listeNavire);
		System.out.println("LA FLOTTE : " + f.toString());

		return f;
	}

	public void stockBlocTouche(Coordonnees coordonnees, int impact, int id) {
		int coordX = coordonnees.getLigne();
		int coordY = coordonnees.getColonne();

		Grille carteEnemi = grilleEnnemi;

		Bloc b = null;
		if (impact == 9) {

			System.out.println("\nTIRE DE CUIRASSE EN " + coordonnees.toString() + "\n");

			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");
			b = carteEnemi.getBloc(coordX, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX - 1, coordY - 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX + 1, coordY + 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX + 1, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX, coordY + 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX - 1, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX, coordY - 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX + 1, coordY - 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX - 1, coordY + 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

		} else if (impact == 4) {

			System.out.println("\nTIRE DE CROISSEUR EN " + coordonnees.toString() + "\n");

			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");
			b = carteEnemi.getBloc(coordX, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX - 1, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX + 1, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX, coordY - 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

			b = carteEnemi.getBloc(coordX, coordY + 1);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}
		} else if (id == 3 && ConfigurationJeu.NB_FUSEE_ECLAIRANTE > 0) {

			System.out.println("\nTIRE DE FUSEE ECLAIRANTE EN " + coordonnees.toString() + "\n");

			b = carteEnemi.getBloc(coordX, coordY);
			if (!b.isTouche()) {
				b.setEclaire(true);
			}

			b = carteEnemi.getBloc(coordX - 1, coordY);
			if (!b.isTouche()) {
				b.setEclaire(true);
			}

			b = carteEnemi.getBloc(coordX + 1, coordY);
			if (!b.isTouche()) {
				b.setEclaire(true);
			}

			b = carteEnemi.getBloc(coordX, coordY - 1);
			if (!b.isTouche()) {
				b.setEclaire(true);
			}

			b = carteEnemi.getBloc(coordX, coordY + 1);
			if (!b.isTouche()) {
				b.setEclaire(true);
			}
			ConfigurationJeu.NB_FUSEE_ECLAIRANTE--;
		} else if (id == 3) {
			System.out.println("\nTIRE DE DESTROYEUR EN " + coordonnees.toString() + "\n");

			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");

			b = carteEnemi.getBloc(coordX, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

		} else {
			System.out.println("\nTIRE DE SOUS-MARIN EN " + coordonnees.toString() + "\n");
			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");

			b = carteEnemi.getBloc(coordX, coordY);
			b.setTouche(true);
			if (!b.getValeur().equals("  ") && !b.getValeur().equals("::")) {
				b.setValeur("//");
			}

		}

	}

	public void updateNavireTouche(Coordonnees coordonnees) {
		Bloc[][] blocs = grilleJoueur.getBlocs();

		ArrayList<Navire> listeNav = flotte.getListeNavire();

		ArrayList<Coordonnees> listeCoord;

		int verif;

		for (int i = 0; i < listeNav.size(); i++) {
			listeCoord = listeNav.get(i).getListeCoordonnees();
			verif = 0;
			for (int j = 0; j < listeCoord.size(); j++) {
				int line = listeCoord.get(j).getLigne();
				int colonne = listeCoord.get(j).getColonne();
				if (blocs[line][colonne].isTouche()) {
					verif++;
				}
			}
			if (verif > 0) {
				Navire navire = listeNav.get(i);
				navire.setTouche(true);
			}
		}
	}

	public void tirer(Coordonnees coordonnees, int id) {

		System.out.println(coordonnees);

		if (id == 1) {
			stockBlocTouche(coordonnees, ConfigurationJeu.IMPACT_CUIRASSE, id);
		} else if (id == 2) {
			stockBlocTouche(coordonnees, ConfigurationJeu.IMPACT_CROISSEUR, id);
		} else if (id == 3) {
			stockBlocTouche(coordonnees, ConfigurationJeu.IMPACT_DESTROYER, id);
		} else {
			stockBlocTouche(coordonnees, ConfigurationJeu.IMPACT_SOUSMARIN, id);
		}

		updateNavireTouche(coordonnees);
	}

	public void MAJSigne(Navire navire) {
		for (Coordonnees c : navire.getListeCoordonnees()) {
			modifValueBlocs(c, navire.getId());
		}
	}

	public void DeleteSigne(Navire navire) {
		String value = "  ";
		int BlocLigne = 0;
		int BlocColonne = 0;
		for (Coordonnees c : navire.getListeCoordonnees()) {

			BlocLigne = c.getLigne();
			BlocColonne = c.getColonne();

			Bloc b = grilleJoueur.getBloc(BlocLigne, BlocColonne);
			b.setValeur(value);
		}
	}

	public boolean deplacementCoherent(Navire navire, int directionDeplacement) {
		ArrayList<Coordonnees> listeCoord = navire.getListeCoordonnees();
		Coordonnees cDebut;
		Coordonnees cFin;
		if (navire.getDirection() == 0) {// 0 = horizontale
			cDebut = listeCoord.get(0);
			cFin = listeCoord.get(listeCoord.size() - 1);
			if (directionDeplacement == 0 && cDebut.getColonne() != 0
					|| directionDeplacement == 2 && cFin.getColonne() != ConfigurationJeu.NB_COLONNE - 2) {
				return true;
			} else {
				return false;
			}
		} else { // 1 = verticale
			cDebut = listeCoord.get(0);
			cFin = listeCoord.get(listeCoord.size() - 1);
			if (directionDeplacement == 1 && cDebut.getLigne() != 0
					|| directionDeplacement == 3 && cFin.getLigne() != ConfigurationJeu.NB_COLONNE - 2) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean checkBlocDisponible(Navire navire, int directionDeplacement) {
		Coordonnees coordonneesDebutNavire = null;
		int BlocLigne = 0;
		int BlocColonne = 0;

		int futureCoordLigne = 0;
		int futureCoordColonne = 0;

		switch (directionDeplacement) {
		case 0: { // gauche

			coordonneesDebutNavire = navire.getListeCoordonnees().get(0);
			BlocLigne = coordonneesDebutNavire.getLigne();
			BlocColonne = coordonneesDebutNavire.getColonne();

			futureCoordLigne = BlocLigne;
			futureCoordColonne = BlocColonne - 1;

			break;
		}
		case 1: { // haut

			coordonneesDebutNavire = navire.getListeCoordonnees().get(0);
			BlocLigne = coordonneesDebutNavire.getLigne();
			BlocColonne = coordonneesDebutNavire.getColonne();

			futureCoordLigne = BlocLigne - 1;
			futureCoordColonne = BlocColonne;
			break;
		}
		case 2: { // droite

			coordonneesDebutNavire = navire.getListeCoordonnees().get(navire.getTaille() - 1);
			BlocLigne = coordonneesDebutNavire.getLigne();
			BlocColonne = coordonneesDebutNavire.getColonne();

			futureCoordLigne = BlocLigne;
			futureCoordColonne = BlocColonne + 1;
			break;
		}
		case 3: { // bas
			coordonneesDebutNavire = navire.getListeCoordonnees().get(navire.getTaille() - 1);
			BlocLigne = coordonneesDebutNavire.getLigne();
			BlocColonne = coordonneesDebutNavire.getColonne();

			futureCoordLigne = BlocLigne + 1;
			futureCoordColonne = BlocColonne;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + directionDeplacement);
		}

		Bloc FutureBlocNavire = grilleJoueur.getBloc(futureCoordLigne, futureCoordColonne);

		if (FutureBlocNavire.getValeur().equals("  ")) {
			return true;
		} else {
			return false;
		}

	}

//	public void deplacer(Navire navire, int directionDeplacement) {
//		if (deplacementCoherent(navire, directionDeplacement)) {
//			if (checkBlocDisponible(navire, directionDeplacement)) {
//				DeleteSigne(navire);
//				navire.deplacer(directionDeplacement);
//				MAJSigne(navire);
//			}
//		}
//
//	}

	public boolean deplacer(int indiceNavire, int directionDeplacement) {
		boolean verif = false;

		Navire navire = flotte.getListeNavire().get(indiceNavire);
		System.out.println(navire.toString());
		if (!navire.isTouche()) {
			if (deplacementCoherent(navire, directionDeplacement)) {
				if (checkBlocDisponible(navire, directionDeplacement)) {
					DeleteSigne(navire);
					navire.deplacer(directionDeplacement);
					MAJSigne(navire);
					verif = true;
				}
			}
		} else {
			System.out.println("ATTENTION ! VOUS NE POUVEZ PAS DEPLACER UN NAVIRE TOUCHE !!!");
		}

		return verif;
	}

	public boolean verifExistListe(Coordonnees c2, ArrayList<Coordonnees> liste) {
		int verif = 0;
		for (Coordonnees c1 : liste) {
			if (c1.verifCoord(c2)) {
				verif++;
			}
		}
		if (verif > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int recupIndice(int CoordLigne, int CoordColonne) {
		Coordonnees coordBloc = new Coordonnees(CoordLigne, CoordColonne);
		ArrayList<Navire> listeNaviresFlottes = flotte.getListeNavire();

		for (int i = 0; i < listeNaviresFlottes.size(); i++) {
			if (verifExistListe(coordBloc, listeNaviresFlottes.get(i).getListeCoordonnees())) {
				return i;
			}
		}
		return 44;
	}

//	public void afficheFlotteNumerote() {
//		Bloc[][] blocs = getGrilleJoueur().getBlocs();
//
//		for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
//			for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
//				if (i < 1 || j < 1) {
//					System.out.print("[" + blocs[i][j].getValeur() + "]");
//				} else if (!blocs[i][j].getValeur().equals("  ") && !blocs[i][j].isTouche()) {
//					System.out.print("[0" + recupIndice(i, j) + "]");
//				} else if (blocs[i][j].isTouche()) {
//					System.out.print("[//]");
//				} else if (blocs[i][j].isCoule()) {
//					System.out.print("[::]");
//				} else {
//					System.out.print("[--]");
//				}
//			}
//			System.out.println();
//		}
//	}

	public void afficheFlotteNumerote() {
		
		System.out.println("\nAFFICHAGE GRILLE NUMEROTE : \n");
		
		Bloc[][] blocs = getGrilleJoueur().getBlocs();

		for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
				if ((i < 1 || j < 1) || blocs[i][j].getValeur().equals("//") || blocs[i][j].getValeur().equals("::")) {
					System.out.print("[" + blocs[i][j].getValeur() + "]");
				} else if (!blocs[i][j].getValeur().equals("  ") && !blocs[i][j].getValeur().equals("//")
						&& !blocs[i][j].getValeur().equals("::")) {
					System.out.print("[0" + recupIndice(i, j) + "]");
				} else {
					System.out.print("[--]");
				}
			}
			System.out.println();
		}
	}

	public void afficheNbTouche() {
		int nbAvantIncrement = this.NbBlocTouche;

		incrementeNombreBlocTouche();
		int nbApresIncrement = this.NbBlocTouche;

		int difference = nbApresIncrement - nbAvantIncrement;

		if (difference > 0) {
			System.out.println("\nBRAVO !!! Vous avez touche " + difference + " bloc contenant un navire.");
		} else {
			System.out.println("\nVous n'avez touché aucun bloc contenant un navire.");
		}

	}

	public void incrementeNombreBlocTouche() {
		Bloc[][] blocs = grilleEnnemi.getBlocs();

		for (int i = 1; i < ConfigurationJeu.NB_LIGNE; i++) {
			for (int j = 1; j < ConfigurationJeu.NB_COLONNE; j++) {
				if (blocs[i][j].isTouche() && blocs[i][j].getValeur() != "  ") {
					NbBlocTouche++;
				}
			}
		}
	}

	public void verifNavireCoule() {
		ArrayList<Navire> listeNav = flotte.getListeNavire();

		Bloc[][] blocs = grilleJoueur.getBlocs();

		for (int i = 0; i < listeNav.size(); i++) {
			ArrayList<Coordonnees> listeCoord = listeNav.get(i).getListeCoordonnees();
			int sizeListeCoord = listeCoord.size();
			int verif = 0;
			for (int j = 0; j < sizeListeCoord; j++) {
				int coordLigne = listeCoord.get(j).getLigne();
				int coordColonne = listeCoord.get(j).getColonne();
				if (blocs[coordLigne][coordColonne].isTouche()) {
					verif++;
				}
			}
			if (!listeNav.get(i).isCoule()) {
				if (verif == sizeListeCoord) {
					listeNav.get(i).setCoule(true);
					System.out.println("BRAVO !!  Vous avez eliminer un Navire de l'ennemi");
					NbNavireCoule++;
					CouleSigne(listeNav.get(i));
				}
			}

		}
	}

	public void ToucheSigne(Navire navire) {
		String value = "//";
		int BlocLigne = 0;
		int BlocColonne = 0;
		for (Coordonnees c : navire.getListeCoordonnees()) {

			BlocLigne = c.getLigne();
			BlocColonne = c.getColonne();

			Bloc b = grilleJoueur.getBloc(BlocLigne, BlocColonne);
			b.setCoule(true);
			b.setValeur(value);
		}
	}

	public void CouleSigne(Navire navire) {
		String value = "::";
		int BlocLigne = 0;
		int BlocColonne = 0;
		for (Coordonnees c : navire.getListeCoordonnees()) {

			BlocLigne = c.getLigne();
			BlocColonne = c.getColonne();

			Bloc b = grilleJoueur.getBloc(BlocLigne, BlocColonne);
			b.setCoule(true);
			b.setValeur(value);
		}
	}

	public boolean verifLoose() {
		if (NbNavireCoule == 10) {
			return true;
		}
		return false;
	}

	public String coupAleatoire() {
		int valeurAleatoire = OutilsJeu.valeurAleatoire(2);
		if (valeurAleatoire == 1) {
			return "t";
		} else {
			return "d";
		}
	}

	public int idNavireDeplacement() {
		return OutilsJeu.valeurZeroAleatoire(10);
	}

	public int directionAleatoire() {
		int direction = OutilsJeu.valeurZeroAleatoire(4);
		System.out.println("DIRECTION ALEATOIRE TIREE : " + direction);
		return direction;
	}

	public boolean checkDeplacement(int indiceNavire, int directionDeplacement) {
		ArrayList<Navire> listeNav = flotte.getListeNavire();
		System.out.println(listeNav);

		Navire navire = listeNav.get(indiceNavire);

		ArrayList<Coordonnees> listeCoord = navire.getListeCoordonnees();
		Coordonnees cDebut;
		Coordonnees cFin;
		if (navire.getDirection() == 0) {// 0 = horizontale
			cDebut = listeCoord.get(0);
			cFin = listeCoord.get(listeCoord.size() - 1);
			if (directionDeplacement == 0 && cDebut.getColonne() != 0
					|| directionDeplacement == 2 && cFin.getColonne() != ConfigurationJeu.NB_COLONNE - 2) {
				return true;
			} else {
				return false;
			}
		} else { // 1 = verticale
			cDebut = listeCoord.get(0);
			cFin = listeCoord.get(listeCoord.size() - 1);
			if (directionDeplacement == 1 && cDebut.getLigne() != 0
					|| directionDeplacement == 3 && cFin.getLigne() != ConfigurationJeu.NB_COLONNE - 2) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean verifTouche(int indiceNavire) {
		Navire navire = flotte.getListeNavire().get(indiceNavire);

		if (navire.isTouche()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifCoule(int indiceNavire) {
		Navire navire = flotte.getListeNavire().get(indiceNavire);

		if (navire.isCoule()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifDeplacable(int indiceNavire) {
		if ((verifTouche(indiceNavire) && verifCoule(indiceNavire))
				|| (verifCoule(indiceNavire) || verifTouche(indiceNavire))) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verifNavireDispo(int idNavire) {
		ArrayList<Navire> listeNav = flotte.getListeNavire();

		int verif = 0;

		for (Navire navire : listeNav) {
			if (navire.getId() == idNavire) {
				if (!navire.isCoule()) {
					verif++;
				}
			}
		}
		if (verif > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Joueur [grilleJoueur=" + grilleJoueur + ", grilleEnnemi=" + grilleEnnemi + ", flotte=" + flotte + "]";
	}

	public boolean NbNavireDeplacable() {
		ArrayList<Navire> listeNavires = flotte.getListeNavire();
		int verif =0;
		for (Navire navire : listeNavires) {
			if(! navire.isTouche()) {
				verif++;
			}
		}
		return verif>0;
	}

//	public static void main(String[] args) {
//		AffichageCLI afficheCLI = new AffichageCLI();
//		
//		Grille grilleJoueur = new Grille(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
//		Grille grilleRobot = new Grille(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
//
//		Joueur j = new Joueur(grilleJoueur, grilleRobot);
//		Joueur robot = new Joueur(grilleRobot, grilleJoueur);
//
//		System.out.println("\n-------------------------------------------------\n");
//
//		afficheCLI.afficheGrille(j.getGrilleJoueur());
//
//		System.out.println("\n-------------------------------------------------\n");
//
//		afficheCLI.afficheGrilleEnnemi(j.getGrilleEnnemi(), false);
//
//		System.out.println("\n-------------------------------------------------\n");
//
//		j.tirer(OutilsJeu.traduitCoordonnees("a 02"), 3);
//
//		afficheCLI.afficheGrilleEnnemi(j.getGrilleEnnemi(), false);
//		
//
//		// TEST DEPLACEMENT
//
////		j.afficheFlotteNumerote();
////		
////		j.deplacer(1, 3);
////		
////		j.carteJoueur.afficheGrille();
//
////
////		System.out.println("\n-------------------------------------------------\n");
////
////		j.carteJoueur.afficheGrille();
//
//	}

}

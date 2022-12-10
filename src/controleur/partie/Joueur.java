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
import controleur.partie.JeuUtilite;
import modele.map.Bloc;
import modele.map.Carte;
import modele.mobile.Coordonnees;
import modele.mobile.Croisseur;
import modele.mobile.Cuirasse;
import modele.mobile.Destroyer;
import modele.mobile.Flotte;
import modele.mobile.Navire;
import modele.mobile.SousMarin;

public class Joueur {

	private Carte carteJoueur;
	private Carte carteEnnemi;

	private ArrayList<Coordonnees> listeCoordonneesFlotte;

	private Flotte flotte;
	
	private int NbBlocTouche;
	private int NbNavireCoule;

	public Joueur(Carte carteJoueur, Carte carteRobot) {
		this.carteJoueur = carteJoueur;
		this.carteEnnemi = carteRobot;
		this.listeCoordonneesFlotte = new ArrayList<Coordonnees>();

		this.flotte = generationFlotteAleatoire(carteJoueur);
		placementFlotte();
		
		NbBlocTouche = 0;
		NbNavireCoule = 0;
	}

	public Carte getCarteJoueur() {
		return carteJoueur;
	}

	public Carte getCarteEnnemi() {
		return carteEnnemi;
	}

	public void setCarteJoueur(Carte carteJoueur) {
		this.carteJoueur = carteJoueur;
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

		Bloc b = carteJoueur.getBloc(BlocLigne, BlocColonne);

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

	public ArrayList<Coordonnees> coordonneesAleatoire(int taille, int direction) {
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

		listeCoordonnees = coordonneesAleatoire(taille, direction);

		return listeCoordonnees;
	}

	public Cuirasse generationCuirasse() {
		int direction = JeuUtilite.directionAleatoire();
		System.out.println("\nLA DIRECTION DU CUIRASSE : " + direction);
		ArrayList<Coordonnees> coordCuirasse = generationCoordonnees(ConfigurationJeu.TAILLE_CUIRASSE, direction);

		Cuirasse cuirasse = new Cuirasse(ConfigurationJeu.ID_CUIRASSE, ConfigurationJeu.TAILLE_CUIRASSE,
				ConfigurationJeu.IMPACT_CUIRASSE, coordCuirasse, false, false, direction);

		return cuirasse;

	}

	public Croisseur generationCroisseur() {
		int direction = JeuUtilite.directionAleatoire();
		System.out.println("\nLA DIRECTION DU CROISSEUR : " + direction);
		ArrayList<Coordonnees> coordCroisseur = generationCoordonnees(ConfigurationJeu.TAILLE_CROISSEUR, direction);

		Croisseur croisseur = new Croisseur(ConfigurationJeu.ID_CROISSEUR, ConfigurationJeu.TAILLE_CROISSEUR,
				ConfigurationJeu.IMPACT_CROISSEUR, coordCroisseur, false, false, direction);

		return croisseur;

	}

	public Destroyer generationDestroyer() {
		int direction = JeuUtilite.directionAleatoire();
		System.out.println("\nLA DIRECTION DU DESTROYER : " + direction);
		ArrayList<Coordonnees> coordDestroyer = generationCoordonnees(ConfigurationJeu.TAILLE_DESTROYER, direction);

		Destroyer destroyer = new Destroyer(ConfigurationJeu.ID_DESTROYER, ConfigurationJeu.TAILLE_DESTROYER,
				ConfigurationJeu.IMPACT_DESTROYER, coordDestroyer, false, direction, true);

		return destroyer;
	}

	public SousMarin generationSousMarin() {
		int direction = JeuUtilite.directionAleatoire();
		System.out.println("\nLA DIRECTION DU SOUS-MARIN : " + direction);
		ArrayList<Coordonnees> coordSousMarin = generationCoordonnees(ConfigurationJeu.TAILLE_SOUSMARIN, direction);

		SousMarin sousMarin = new SousMarin(ConfigurationJeu.ID_SOUSMARIN, ConfigurationJeu.TAILLE_SOUSMARIN,
				ConfigurationJeu.IMPACT_SOUSMARIN, coordSousMarin, false, false, direction);

		return sousMarin;
	}

	public Flotte generationFlotteAleatoire(Carte carteJoueur) {
		ArrayList<Navire> listeNavire = new ArrayList<Navire>();

		Cuirasse cuirasse = generationCuirasse();
		listeNavire.add(cuirasse);

		for (int i = 1; i <= 2; i++) {
			Croisseur croisseur = generationCroisseur();
			listeNavire.add(croisseur);
		}

		for (int i = 1; i <= 3; i++) {
			Destroyer destroyer = generationDestroyer();
			listeNavire.add(destroyer);
		}

		for (int i = 1; i <= 4; i++) {
			SousMarin sousMarin = generationSousMarin();
			listeNavire.add(sousMarin);
		}
		Flotte f = new Flotte(listeNavire);
		System.out.println("LA FLOTTE : " + f.toString());

		return new Flotte(listeNavire);
	}

	public void stockBlocTouche(Coordonnees coordonnees, int impact, int id) {
		int coordX = coordonnees.getLigne();
		int coordY = coordonnees.getColonne();
		
		Carte carte = carteEnnemi;

		Bloc b = null;
		if (impact == 9) {
			System.out.println("IMPACT DE : " + impact + " CASES !!!");
			b = carte.getBloc(coordX, coordY);
			b.setTouche(true);

			b = carte.getBloc(coordX - 1, coordY - 1);
			b.setTouche(true);

			b = carte.getBloc(coordX + 1, coordY + 1);
			b.setTouche(true);

			b = carte.getBloc(coordX + 1, coordY);
			b.setTouche(true);

			b = carte.getBloc(coordX, coordY + 1);
			b.setTouche(true);

			b = carte.getBloc(coordX - 1, coordY);
			b.setTouche(true);

			b = carte.getBloc(coordX, coordY - 1);
			b.setTouche(true);

			b = carte.getBloc(coordX + 1, coordY - 1);
			b.setTouche(true);

			b = carte.getBloc(coordX - 1, coordY + 1);
			b.setTouche(true);

		} else if (impact == 4) {
			System.out.println("IMPACT DE : " + impact + " CASES !!!");
			b = carte.getBloc(coordX, coordY);
			b.setTouche(true);

			b = carte.getBloc(coordX - 1, coordY);
			b.setTouche(true);

			b = carte.getBloc(coordX + 1, coordY);
			b.setTouche(true);

			b = carte.getBloc(coordX, coordY - 1);
			b.setTouche(true);

			b = carte.getBloc(coordX, coordY + 1);
			b.setTouche(true);
		} else if (id == 3 && ConfigurationJeu.NB_FUSEE_ECLAIRANTE > 0) {
			b = carte.getBloc(coordX, coordY);
			b.setEclaire(true);

			b = carte.getBloc(coordX - 1, coordY);
			b.setEclaire(true);

			b = carte.getBloc(coordX + 1, coordY);
			b.setEclaire(true);

			b = carte.getBloc(coordX, coordY - 1);
			b.setEclaire(true);

			b = carte.getBloc(coordX, coordY + 1);
			b.setEclaire(true);
			ConfigurationJeu.NB_FUSEE_ECLAIRANTE--;
		} else {
			System.out.println("IMPACT DE : " + impact + " CASES !!!");
			b = carte.getBloc(coordX, coordY);
			b.setTouche(true);

		}

	}

	private Coordonnees decodeCoordonnees(String[] stringCoordonnes) {
		String indiceLigneString = stringCoordonnes[0];
		String indiceColonneString = stringCoordonnes[1];
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

	public void updateNavireTouche(Coordonnees coordonnees) {
		Bloc[][] blocs = carteJoueur.getBlocs();

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

	public void tirer(String[] stringCoordonnes, int id) {

		Coordonnees coordonnees = decodeCoordonnees(stringCoordonnes);

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

			Bloc b = carteJoueur.getBloc(BlocLigne, BlocColonne);
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

		Bloc FutureBlocNavire = carteJoueur.getBloc(futureCoordLigne, futureCoordColonne);

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

	public void afficheFlotteNumerote() {
		Bloc[][] blocs = getCarteJoueur().getBlocs();

		for (int i = 0; i < ConfigurationJeu.NB_LIGNE - 1; i++) {
			for (int j = 0; j < ConfigurationJeu.NB_COLONNE - 1; j++) {
				if (i < 1 || j < 1) {
					System.out.print("[" + blocs[i][j].getValeur() + "]");
				} else if (!blocs[i][j].getValeur().equals("  ") && !blocs[i][j].isTouche()) {
					System.out.print("[0" + recupIndice(i, j) + "]");
				} else if (blocs[i][j].isTouche()) {
					System.out.print("[//]");
				} else if (blocs[i][j].isCoule()) {
					System.out.print("[::]");
				}else {
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
		Bloc[][] blocs = carteEnnemi.getBlocs();

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

		Bloc[][] blocs = carteJoueur.getBlocs();

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

			Bloc b = carteJoueur.getBloc(BlocLigne, BlocColonne);
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

			Bloc b = carteJoueur.getBloc(BlocLigne, BlocColonne);
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

	@Override
	public String toString() {
		return "Joueur [carteJoueur=" + carteJoueur + ", carteEnnemi=" + carteEnnemi + ", flotte=" + flotte + "]";
	}

	public String coupAleatoire() {
		int valeurAleatoire = JeuUtilite.valeurAleatoire(2);
		System.out.println(valeurAleatoire);
		if (valeurAleatoire == 1) {
			return "t";
		} else {
			return "d";
		}
	}

	public String[] coordAleatoireSplit() {
		int indiceLigneAleatoire = JeuUtilite.valeurAleatoire(ConfigurationJeu.NB_LIGNE - 2);
		int indiceColonneAleatoire = JeuUtilite.valeurAleatoire(ConfigurationJeu.NB_COLONNE - 2);

		String indiceLigne = ConfigurationJeu.INDICE_LIGNE;
		String[] tabIndiceLigne = indiceLigne.split(",");

		String indiceColonne = ConfigurationJeu.INDICE_COLONNE;
		String[] tabIndiceColonne = indiceColonne.split(",");

		String[] coordonneesSplit = { tabIndiceLigne[indiceLigneAleatoire], tabIndiceColonne[indiceColonneAleatoire] };
		return coordonneesSplit;
	}

	public int idNavireAleatoire() {
		return JeuUtilite.valeurAleatoire(9);
	}

	public String recupNomBateau(int id) {
		if (id == 1) {
			return ConfigurationJeu.NOM_CUIRASSE;
		} else if (id == 2) {
			return ConfigurationJeu.NOM_CROISSEUR;
		} else if (id == 3) {
			return ConfigurationJeu.NOM_DESTROYER;
		} else {
			return ConfigurationJeu.NOM_SOUSMARIN;
		}
	}

	public int idNavireDeplacement() {
		return JeuUtilite.valeurZeroAleatoire(10);
	}

	public int directionAleatoire() {
		int direction = JeuUtilite.valeurZeroAleatoire(4);
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
		if ((verifTouche(indiceNavire) && verifCoule(indiceNavire)) || (verifCoule(indiceNavire) || verifTouche(indiceNavire) )) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verifNavireDispo(int idNavire) {
		ArrayList<Navire> listeNav = flotte.getListeNavire();
		
		int verif =0;
		
		for (Navire navire : listeNav) {
			if(navire.getId() == idNavire) {
				if(!navire.isCoule()) {
					verif ++;
				}
			}
		}
		if(verif>0) {
			return true;
		}else {
			return false;
		}
	}

//	public static void main(String[] args) {
//		Carte carteJoueur = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
//		Carte carteRobot = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
//
//		Joueur j = new Joueur(carteJoueur, carteRobot);
//
//		System.out.println("\n-------------------------------------------------\n");
//
//		j.carteJoueur.afficheCarte();
//
//		System.out.println("\n-------------------------------------------------\n");
//
//		j.carteJoueur.afficheCarteEnnemi(true);
//
//		System.out.println("\n-------------------------------------------------\n");
//
//		ArrayList<Navire> copyFlotte = j.getFlotte().getListeNavire();
//
//		String[] coordonnees = { "f", "05" };
//		j.tirer(coordonnees, 3);
//
//		j.carteJoueur.afficheCarteEnnemi(false);
//
//		// TEST DEPLACEMENT
//
////		j.afficheFlotteNumerote();
////		
////		j.deplacer(1, 3);
////		
////		j.carteJoueur.afficheCarte();
//
////
////		System.out.println("\n-------------------------------------------------\n");
////
////		j.carteJoueur.afficheCarte();
//
//	}

}

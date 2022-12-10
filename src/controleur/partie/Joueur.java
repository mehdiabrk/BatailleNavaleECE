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

	public Joueur(Carte carteJoueur, Carte carteRobot) {
		this.carteJoueur = carteJoueur;
		this.carteEnnemi = carteRobot;
		this.listeCoordonneesFlotte = new ArrayList<Coordonnees>();

		this.flotte = generationFlotteAleatoire(carteJoueur);

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
				ConfigurationJeu.IMPACT_CUIRASSE, coordCuirasse, false, direction);

		return cuirasse;

	}

	public Croisseur generationCroisseur() {
		int direction = JeuUtilite.directionAleatoire();
		System.out.println("\nLA DIRECTION DU CROISSEUR : " + direction);
		ArrayList<Coordonnees> coordCroisseur = generationCoordonnees(ConfigurationJeu.TAILLE_CROISSEUR, direction);

		Croisseur croisseur = new Croisseur(ConfigurationJeu.ID_CROISSEUR, ConfigurationJeu.TAILLE_CROISSEUR,
				ConfigurationJeu.IMPACT_CROISSEUR, coordCroisseur, false, direction);

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
				ConfigurationJeu.IMPACT_SOUSMARIN, coordSousMarin, false, direction);

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

	public void stockBlocTouche(Coordonnees coordonnees, int impact) {
		int coordX = coordonnees.getLigne();
		int coordY = coordonnees.getColonne();
		System.err.println("IMPACT : " + impact);
		Carte carte = carteEnnemi;

		if (impact == 9) {
			carte.getBloc(coordX, coordY).setTouche(true);
			carte.getBloc(coordX - 1, coordY - 1).setTouche(true);
			carte.getBloc(coordX + 1, coordY + 1).setTouche(true);
			carte.getBloc(coordX + 1, coordY).setTouche(true);
			carte.getBloc(coordX, coordY + 1).setTouche(true);
			carte.getBloc(coordX - 1, coordY).setTouche(true);
			carte.getBloc(coordX, coordY - 1).setTouche(true);
			carte.getBloc(coordX + 1, coordY - 1).setTouche(true);
			carte.getBloc(coordX - 1, coordY + 1).setTouche(true);
		} else if (impact == 4) {
			carte.getBloc(coordX, coordY).setTouche(true);
			carte.getBloc(coordX - 1, coordY).setTouche(true);
			carte.getBloc(coordX + 1, coordY).setTouche(true);
			carte.getBloc(coordX, coordY - 1).setTouche(true);
			carte.getBloc(coordX, coordY + 1).setTouche(true);
		} else {
			carte.getBloc(coordX, coordY).setTouche(true);
		}

	}

	public void tirer(Coordonnees coordonnees, Navire navire) {

		int impact = navire.getImpactMissile();

		stockBlocTouche(coordonnees, impact);

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
	
	public boolean deplacementCoherent(Navire navire,int directionDeplacement) {
		if (navire.getDirection() == 0) {// 0 = horizontale
			if (directionDeplacement == 0 || directionDeplacement == 2) {
				System.out.println("Methode deplacementCoherent : True");
				return true;
			} else {
				System.err.println(
						"Le bateau que vous avez choisi est a l'horizontale il ne peut donc pas se deplacer à la verticale");
				System.out.println("Methode deplacementCoherent : False");
				return false;
			}
		} else { // 1 = verticale
			if (directionDeplacement == 1 || directionDeplacement == 3) {
				System.out.println("Methode deplacementCoherent : True");
				return true;
			} else {
				System.err.println(
						"Le bateau que vous avez choisi est a la verticale il ne peut donc pas se deplacer à l'horizontale");
				System.out.println("Methode deplacementCoherent : False");
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
			
			coordonneesDebutNavire = navire.getListeCoordonnees().get(navire.getTaille()-1);
			BlocLigne = coordonneesDebutNavire.getLigne();
			BlocColonne = coordonneesDebutNavire.getColonne();
			
			futureCoordLigne = BlocLigne;
			futureCoordColonne = BlocColonne + 1;
			break;
		}
		case 3: { // bas
			coordonneesDebutNavire = navire.getListeCoordonnees().get(navire.getTaille()-1);
			BlocLigne = coordonneesDebutNavire.getLigne();
			BlocColonne = coordonneesDebutNavire.getColonne();
			
			futureCoordLigne = BlocLigne + 1;
			futureCoordColonne = BlocColonne;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + directionDeplacement);
		}

		Bloc FutureBlocNavire= carteJoueur.getBloc(futureCoordLigne, futureCoordColonne);
		System.out.println(FutureBlocNavire.toString());

		if (FutureBlocNavire.getValeur().equals("  ")) {
			return true;
		} else {
			System.err.println("\nVotre deplacement est impossible car la case est déjà occupée");
			return false;
		}

	}

	public void deplacer(Navire navire, int directionDeplacement) {
		if (deplacementCoherent(navire, directionDeplacement) ) {
			if(checkBlocDisponible(navire, directionDeplacement)) {
				DeleteSigne(navire);
				navire.deplacer(directionDeplacement);
				MAJSigne(navire);
			}
		}

	}

	@Override
	public String toString() {
		return "Joueur [carteJoueur=" + carteJoueur + ", carteEnnemi=" + carteEnnemi + ", flotte=" + flotte + "]";
	}

	public static void main(String[] args) {
		Carte carteJoueur = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);
		Carte carteRobot = new Carte(ConfigurationJeu.NB_LIGNE, ConfigurationJeu.NB_COLONNE);

		Joueur j = new Joueur(carteJoueur, carteRobot);

		j.getCarteJoueur().afficheCarte();

		System.out.println("\n-------------------------------------------------\n");

		j.placementFlotte();

		System.out.println("\n-------------------------------------------------\n");

		j.carteJoueur.afficheCarte();

		System.out.println("\n-------------------------------------------------\n");

		ArrayList<Navire> copyFlotte = j.getFlotte().getListeNavire();

		j.deplacer(copyFlotte.get(0), 3);

		System.out.println("\n-------------------------------------------------\n");

		j.carteJoueur.afficheCarte();

	}

}

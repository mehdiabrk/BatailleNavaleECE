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
import controleur.outils.OutilsJeu;
import modele.grille.Bloc;
import modele.grille.Grille;
import modele.mobile.Coordonnees;
import modele.mobile.Croiseur;
import modele.mobile.Cuirasse;
import modele.mobile.Destroyer;
import modele.mobile.Flotte;
import modele.mobile.Navire;
import modele.mobile.SousMarin;

/**
 * Cette classe sert a la creation d'un objet Joueur. Le Joueur est compose de
 * deux grilles : la sienne et celle de l'ennemi, une flotte : la liste de ses
 * navires , listeDesCoordonneees: contenant une liste de coordonnees de la
 * flotte. Nous l'avons nomme controleur car elle contient egalement les
 * methodes servant aux actions des navires ( exemple : tourJoueur() ,
 * tourRobot() , ...)
 */

public class Joueur {

	private Grille grilleJoueur;
	private Grille grilleEnnemi;

	private ArrayList<Coordonnees> listeCoordonneesFlotte;

	private Flotte flotte;

	private int NbBlocTouche;
	private int NbNavireCoule;

	/**
	 * Premier Constructeur de la classe Joueur : Servant lors d'une nouvelle partie
	 * @param grilleJoueur : grille du Joueur
	 * @param grilleEnnemi : grille de l'ennemi
	 * @param listeCoordonneesFlotte : liste de coordonnees de la Flotte du joueur
	 * @param flotte : Liste des Navires du joueurs
	 * @param nbBlocTouche : Nombre de blocs touche par l'adversaire
	 * @param nbNavireCoule : Nombre de Navire que le joueur possede
	 */
	public Joueur(Grille carteJoueur, Grille carteEnnemi, Flotte flotte, int nbBlocTouche, int nbNavireCoule) {
		this.grilleJoueur = carteJoueur;
		this.grilleEnnemi = carteEnnemi;
		this.listeCoordonneesFlotte = new ArrayList<Coordonnees>();
		this.flotte = flotte;
		placementFlotte();
		NbBlocTouche = nbBlocTouche;
		NbNavireCoule = nbNavireCoule;
	}

	/**
	 * Second Constructeur de la classe Joueur : ervant lors du chargement d'une ancienne partie
	 * @param grilleJoueur : grille du Joueur
	 * @param grilleEnnemi : grille de l'ennemi
	 * @param listeCoordonneesFlotte : liste de coordonnees de la Flotte du joueur
	 * @param flotte : Liste des Navires du joueurs
	 * @param nbBlocTouche : Nombre de blocs touche par l'adversaire
	 * @param nbNavireCoule : Nombre de Navire que le joueur possede
	 */
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

	public void setNbNavireCoule(int nbNavireCoule) {
		NbNavireCoule = nbNavireCoule;
	}

	public int getNbNavireCoule() {
		return NbNavireCoule;
	}

	/**
	 * Methode servant a Modifier les attributs du bloc dont les coordonnees sont donnes en parametre
	 */
	public void modifValueBlocs(Coordonnees c, int id, boolean coule) {

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
		b.setIdNavire(id);
		if (coule) {
			b.setTouche(true);
			b.setCoule(true);
		}
	}

	/**
	 * Methode servant a Modifier les blocs ou sont positionnes les navires
	 */
	public void initializeNavire(Navire n) {
		int id = n.getId();
		boolean coule = n.isCoule();

		for (Coordonnees c : n.getListeCoordonnees()) {
			modifValueBlocs(c, id, coule);
		}
	}

	/**
	 * Methode servant au placement de la flotte sur la grille 
	 */
	public void placementFlotte() {
		for (Navire n : flotte.getListeNavire()) {
			initializeNavire(n);
		}
	}

	/**
	 * Methode servant a verfier si deux entier sont egaux
	 * 
	 * @param verif : entier a verifier
	 * @param taille : taille du navire
	 *
	 * @return Vrai si egaux | Faux sinon
	 */
	public boolean verifEgal(int verif, int taille) {
		if (verif != taille) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Methode servant a verfier si une coordonnes appartient deja a celle d'un autre navire
	 * 
	 * @param c2 : coordonnees 
	 *
	 * @return Vrai si coordonnees deja prise | Faux sinon
	 */
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

	/**
	 * Methode servant au choix des Coordonnees aleatoire d'un Navire. Sert a savoir
	 * si avec les coordonnees choisi, le navire rentre dans la grille
	 * 
	 * @param CoordLigne    : coordonnes de la ligne ou commencera le navire 
	 * @param CoordColonne : coordonnes de la colonne ou commencera le navire 
	 * @param taille : taille du navire 
	 * @param direction : direction du navire 
	 *
	 * @return Liste de Coordonnees
	 */
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

		if (verifEgal(verif, taille)) {
			return c;
		} else {
			return null;
		}

	}

	/**
	 * Methode servant a la creation d'une liste aleatoire de Coordonnees d'un
	 * Navire
	 * 
	 * @param taille    : taille du navire pour lequel il faut generer des
	 *                  coordonneees
	 * @param direction : direction du navire pour lequel il faut generer des
	 *                  coordonneees
	 *
	 * @return Liste de Coordonnees
	 */
	public ArrayList<Coordonnees> listeCoordonneesAleatoire(int taille, int direction) {
		Random r = new Random();
		int CordLigne = r.nextInt(15) + 1;
		int CordColonne = r.nextInt(15) + 1;

		while (checkCoordonneesDisponible(CordLigne, CordColonne, taille, direction) == null) {
			CordLigne = r.nextInt(15) + 1;
			CordColonne = r.nextInt(15) + 1;
		}
		ArrayList<Coordonnees> listeCoordonnees = checkCoordonneesDisponible(CordLigne, CordColonne, taille, direction);

		for (Coordonnees coordonnees : listeCoordonnees) {
			listeCoordonneesFlotte.add(coordonnees);
		}

		return listeCoordonnees;

	}

	/**
	 * Methode servant a la generation d'une liste aleatoire de Coordonnees d'un
	 * Navire
	 * 
	 * @param taille    : taille du navire pour lequel il faut generer des
	 *                  coordonneees
	 * @param direction : direction du navire pour lequel il faut generer des
	 *                  coordonneees
	 *
	 * @return Liste de Coordonnees
	 */
	public ArrayList<Coordonnees> generationCoordonnees(int taille, int direction) {
		ArrayList<Coordonnees> listeCoordonnees = new ArrayList<Coordonnees>();

		listeCoordonnees = listeCoordonneesAleatoire(taille, direction);

		return listeCoordonnees;
	}

	/**
	 * Methode servant a generer un Cuirasse
	 * 
	 * @return Cuirasse
	 */
	public Cuirasse generationCuirasse() {
		int direction = OutilsJeu.directionAleatoire();
		ArrayList<Coordonnees> listeCoordCuirasse = generationCoordonnees(ConfigurationJeu.TAILLE_CUIRASSE, direction);

		Cuirasse cuirasse = new Cuirasse(listeCoordCuirasse, direction);

		return cuirasse;

	}

	/**
	 * Methode servant a generer un Croiseur
	 * 
	 * @return Croiseur
	 */
	public Croiseur generationCroisseur() {
		int direction = OutilsJeu.directionAleatoire();
		ArrayList<Coordonnees> listeCoordCroisseur = generationCoordonnees(ConfigurationJeu.TAILLE_CROISSEUR,
				direction);

		Croiseur croiseur = new Croiseur(listeCoordCroisseur, direction);

		return croiseur;

	}

	/**
	 * Methode servant a generer un Destroyer
	 * 
	 * @return Destroyer
	 */
	public Destroyer generationDestroyer() {
		int direction = OutilsJeu.directionAleatoire();
		ArrayList<Coordonnees> listeCoordDestroyer = generationCoordonnees(ConfigurationJeu.TAILLE_DESTROYER,
				direction);

		Destroyer destroyer = new Destroyer(listeCoordDestroyer, direction);

		return destroyer;
	}

	/**
	 * Methode servant a generer un Sous Marin
	 * 
	 * @return Sous Marin
	 */
	public SousMarin generationSousMarin() {
		int direction = OutilsJeu.directionAleatoire();
		ArrayList<Coordonnees> listeCoordSousMarin = generationCoordonnees(ConfigurationJeu.TAILLE_SOUSMARIN,
				direction);

		SousMarin sousMarin = new SousMarin(listeCoordSousMarin, direction);

		return sousMarin;
	}

	/**
	 * Methode servant a generer une flotte aleatoire
	 * 
	 * @param grilleJoueur : grille du joueur sur laquelle sera genere la flotte
	 *
	 * @return Flotte , liste de tous les navires
	 */
	public Flotte generationFlotte(Grille grilleJoueur) {
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

		return f;
	}

	/**
	 * Methode servant a mettre a jour les blocs touche par le tire
	 * 
	 * @param coordonnees : coordonnees de l'impact principal du tir
	 * @param impact      : taille de l'impact du tir
	 * @param id          : du navire avec lequel on tire
	 *
	 */
	public void stockBlocTouche(Coordonnees coordonnees, int impact, int id) {
		int coordX = coordonnees.getLigne();
		int coordY = coordonnees.getColonne();

		Grille carteEnemi = grilleEnnemi;

		ArrayList<Coordonnees> listCoordonnees = new ArrayList<>();

		listCoordonnees.add(new Coordonnees(coordX, coordY));

		listCoordonnees.add(new Coordonnees(coordX + 1, coordY));
		listCoordonnees.add(new Coordonnees(coordX - 1, coordY));
		listCoordonnees.add(new Coordonnees(coordX, coordY + 1));
		listCoordonnees.add(new Coordonnees(coordX, coordY - 1));

		listCoordonnees.add(new Coordonnees(coordX + 1, coordY + 1));
		listCoordonnees.add(new Coordonnees(coordX - 1, coordY - 1));
		listCoordonnees.add(new Coordonnees(coordX - 1, coordY + 1));
		listCoordonnees.add(new Coordonnees(coordX + 1, coordY - 1));

		Bloc b = null;
		if (impact == 9) {

			System.out.println("\nTIRE DE CUIRASSE EN " + coordonnees.toString() + "\n");

			for (int i = 0; i < impact; i++) {
				Coordonnees c = listCoordonnees.get(i);
				b = carteEnemi.getBloc(c);
				if (b.getIdNavire() != 4) {
					b.setTouche(true);
				}

			}

			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");

		} else if (impact == 4) {

			System.out.println("\nTIRE DE CROISSEUR EN " + coordonnees.toString() + "\n");

			for (int i = 0; i <= impact; i++) {
				Coordonnees c = listCoordonnees.get(i);
				b = carteEnemi.getBloc(c);
				if (b.getIdNavire() != 4) {
					b.setTouche(true);
				}
			}

			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");

		} else if (id == 3 && ConfigurationJeu.NB_FUSEE_ECLAIRANTE > 0) {

			System.out.println("\nTIRE DE FUSEE ECLAIRANTE EN " + coordonnees.toString() + "\n");

			for (int i = 0; i <= 4; i++) {// 4 IMPACTS
				Coordonnees c = listCoordonnees.get(i);
				b = carteEnemi.getBloc(c);

				if (!b.isTouche()) {
					b.setEclaire(true);
				}
			}

			System.out.println("\nECLAIRAGE DE : " + 4 + " CASES !!!\n");
			ConfigurationJeu.NB_FUSEE_ECLAIRANTE--;
			System.out.println("\nIl VOUS RESTE " + ConfigurationJeu.NB_FUSEE_ECLAIRANTE + " FUSEE ECLAIRANTE.");

		} else if (id == 3) {
			System.out.println("\nTIRE DE DESTROYEUR EN " + coordonnees.toString() + "\n");

			for (int i = 0; i < impact; i++) {
				Coordonnees c = listCoordonnees.get(i);
				b = carteEnemi.getBloc(c);
				if (b.getIdNavire() != 4) {
					b.setTouche(true);
				}
			}

			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");

		} else {
			System.out.println("\nTIRE DE SOUS-MARIN EN " + coordonnees.toString() + "\n");

			for (int i = 0; i < impact; i++) {
				Coordonnees c = listCoordonnees.get(i);
				b = carteEnemi.getBloc(c);
				b.setTouche(true);
			}

			System.out.println("\nIMPACT DE : " + impact + " CASES !!!\n");
		}

	}

	/**
	 * Methode servant a modifier le booleen touche des navires apres un tire
	 * 
	 * @param id : du navire avec lequel on a tire
	 *
	 */
	public void updateNavireTouche(int id) {
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
			Navire navire = listeNav.get(i);
			if (navire.getId() != 4) {
				if (verif > 0) {
					navire.setTouche(true);
				}
			} else {
				if (navire.getId() == id) {
					navire.setTouche(true);
				}
			}

		}
	}

	/**
	 * Methode servant a tirer sur les coordonnes donnee en parametre et avec la
	 * puissance du navire ayant l'id en parametre
	 * 
	 * @param coordonnees : coordonnees d'ou l'on souhaite tirer
	 * @param id          : du navire avec lequel on souhaite tirer
	 *
	 */
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

		updateNavireTouche(id);
	}

	/**
	 * Methode servant a remettre a jour le signe des navires sur les nuoveaux blocs
	 * ou il a ete deplacer
	 */
	public void MAJSigne(Navire navire) {
		for (Coordonnees c : navire.getListeCoordonnees()) {
			modifValueBlocs(c, navire.getId(), navire.isCoule());
		}
	}

	/**
	 * Methode servant a remettre un neuf un bloc apres deplacement d'un navire
	 * 
	 * @param navire               : navire pour lequel on cherche si le futur
	 *                             deplacement est coherent
	 * @param directionDeplacement : direction dans laquelle il sera deplacer
	 *
	 * @return Vrai si deplacement coherant , Faux sinon
	 */
	public void DeleteSigne(Navire navire) {
		String value = "  ";
		int BlocLigne = 0;
		int BlocColonne = 0;
		for (Coordonnees c : navire.getListeCoordonnees()) {

			BlocLigne = c.getLigne();
			BlocColonne = c.getColonne();

			Bloc b = grilleJoueur.getBloc(BlocLigne, BlocColonne);
			b.setValeur(value);
			b.setIdNavire(0);
		}
	}

	/**
	 * Methode servant a savoir si le futur deplacement du Navire est coherent avec
	 * sa position actuelle
	 * 
	 * @param navire               : navire pour lequel on cherche si le futur
	 *                             deplacement est coherent
	 * @param directionDeplacement : direction dans laquelle il sera deplacer
	 *
	 * @return Vrai si deplacement coherant , Faux sinon
	 */
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

	/**
	 * Methode servant a savoir si le futur deplacement du Navire dans la direction
	 * en parametre est possible
	 * 
	 * @param navire               : navire pour lequel on cherche si le futur
	 *                             deplacement est possible
	 * @param directionDeplacement : direction dans laquelle il sera deplacer
	 *
	 * @return Vrai si deplacement possible, Faux sinon
	 */
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

	/**
	 * Methode servant a recuperer les coordonnees donnee en parametre apres le
	 * deplacement dans la direction donnee en parametre
	 * 
	 * @param directionDeplacement : direction dans laquelle il faut deplacer le
	 *                             Navire ( 0 : gauche | 1: haut | 2 : droite | 3 :
	 *                             bas )
	 * 
	 * @param c                    : Coordonnee du Navire avant deplacement
	 *
	 * @return La coordonnees du Navire après deplacement
	 */
	private Coordonnees MajCoordonnees(int directionDeplacement, Coordonnees c) {
		int coordLigne = 0;
		int coordColonne = 0;
		switch (directionDeplacement) {
		case 0: { // gauche
			coordLigne = c.getLigne();
			coordColonne = c.getColonne() - 1;
			break;
		}
		case 1: { // haut
			coordLigne = c.getLigne() - 1;
			coordColonne = c.getColonne();
			break;
		}
		case 2: { // droite
			coordLigne = c.getLigne();
			coordColonne = c.getColonne() + 1;
			break;
		}
		case 3: { // bas
			coordLigne = c.getLigne() + 1;
			coordColonne = c.getColonne();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + directionDeplacement);
		}

		return new Coordonnees(coordLigne, coordColonne);
	}

	/**
	 * Methode servant a renvoyer la coordonnees du bloc en tete du Navire apres son
	 * deplacement dans la direction donnee
	 * 
	 * @param directionDeplacement : direction dans laquelle il faut deplacer le
	 *                             Navire ( 0 : gauche | 1: haut | 2 : droite | 3 :
	 *                             bas )
	 *
	 * @return Les coordonnees du bloc en tete du navire apres son deplacement.
	 */
	public Coordonnees validationFuturesCoordonnees(Navire navire, int directionDeplacement) {
		ArrayList<Coordonnees> listeCoordonnees = navire.getListeCoordonnees();
		int taille = navire.getTaille();
		int coordLigne = 0;
		int coordColonne = 0;
		switch (directionDeplacement) {
		case 0: { // gauche
			coordLigne = listeCoordonnees.get(0).getLigne();
			coordColonne = listeCoordonnees.get(0).getColonne() - 1;

			break;
		}
		case 1: { // haut
			coordLigne = listeCoordonnees.get(0).getLigne() - 1;
			coordColonne = listeCoordonnees.get(0).getColonne();
			break;
		}
		case 2: { // droite
			coordLigne = listeCoordonnees.get(taille - 1).getLigne();
			coordColonne = listeCoordonnees.get(taille - 1).getColonne() + 1;
			break;
		}
		case 3: { // bas
			coordLigne = listeCoordonnees.get(taille - 1).getLigne() + 1;
			coordColonne = listeCoordonnees.get(taille - 1).getColonne();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + directionDeplacement);
		}
		return new Coordonnees(coordLigne, coordColonne);
	}

	/**
	 * Methode servant a savoir si un Navire et deplacable dans la direction donnee
	 * en parametre
	 * 
	 * @param directionDeplacement : direction dans laquelle il faut deplacer le
	 *                             Navire ( 0 : gauche | 1: haut | 2 : droite | 3 :
	 *                             bas )
	 *
	 * @return Vrai : si le navire peut etre deplace dans la direction donnee
	 */
	public boolean navireDeplacable(Navire navire, int directionDeplacement) {
		Coordonnees c = validationFuturesCoordonnees(navire, directionDeplacement);
		if (c.valideCoordonnees()) {
			return true;
		}
		return false;
	}

	/**
	 * Methode servant a deplacer un Navire dans la direction donne en parametre
	 * 
	 * @param directionDeplacement : direction dans laquelle il faut deplacer le
	 *                             Navire ( 0 : gauche | 1: haut | 2 : droite | 3 :
	 *                             bas )
	 *
	 */
	public void deplaceNavire(Navire navire, int directionDeplacement) {
		ArrayList<Coordonnees> listeCoordonnees = navire.getListeCoordonnees();

		if (navireDeplacable(navire, directionDeplacement)) {
			for (int i = 0; i < listeCoordonnees.size(); i++) {
				Coordonnees c = listeCoordonnees.get(i);
				listeCoordonnees.set(i, MajCoordonnees(directionDeplacement, c));
			}
		}
	}

	/**
	 * Methode servant a deplacer et a verifier si le navire dont l'indice est en
	 * parametre peut se deplacer dans la direction donne en parametre
	 * 
	 * @param indiceNavire         : indice du navire a deplacer
	 * @param directionDeplacement : direction dans laquelle il faut le deplacer
	 *
	 * @return Vrai si deplacement , Faux sinon
	 */
	public boolean deplacer(int indiceNavire, int directionDeplacement) {
		boolean verif = false;

		Navire navire = flotte.getListeNavire().get(indiceNavire);
		if (!navire.isTouche()) {
			if (deplacementCoherent(navire, directionDeplacement)) {
				if (checkBlocDisponible(navire, directionDeplacement)) {
					DeleteSigne(navire);
					deplaceNavire(navire, directionDeplacement);
					MAJSigne(navire);
					verif = true;
				}
			}
		} else {
			System.out.println("ATTENTION ! VOUS NE POUVEZ PAS DEPLACER UN NAVIRE TOUCHE !!!");
		}

		return verif;
	}

	/**
	 * Methode servant a verifier si la coordonneees en parametre appartient a la
	 * liste de coordonnees en parametre
	 * 
	 * @param c2    : coordonnees pour laquelle on veut savoir si elle appartient a
	 *              la liste
	 * @param liste : liste de coordonnees
	 *
	 * @return Vrai si appartient , Faux sinon
	 */
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

	/**
	 * Methode servant a recuperer l'indice du navire possedant le bloc dont les
	 * coordonneees sont en parametre
	 * 
	 * @param CoordLigne   : indice de la ligne ou est le bloc
	 * @param CoordColonne : indice de la colonne ou est le bloc
	 *
	 * @return L'indice du bloc ( Entre 0 et 9 inclus )
	 */
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

//	public void afficheNbTouche() {
//		int nbAvantIncrement = this.NbBlocTouche;
//
//		incrementeNombreBlocTouche();
//		int nbApresIncrement = this.NbBlocTouche;
//
//		int difference = nbApresIncrement - nbAvantIncrement;
//
//		if (difference > 0) {
//			System.out.println("\nBRAVO !!! Vous avez touche " + difference + " bloc contenant un navire.");
//		} else {
//			System.out.println("\nVous n'avez touche aucun bloc contenant un navire.");
//		}
//	}

	/**
	 * Mehode servant a incrementer le nombre de Bloc Touche chez l'adversaire
	 */
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

	/**
	 * Methode servant a mettre a jour le nombre de navire coule chez le joueur
	 * 
	 */
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

	/**
	 * Methode servant a modifier le signe des blocs d'un navire lorsqu'il est coule
	 *
	 */
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

	/**
	 * Methode servant a choisir un coup aleatoire
	 * 
	 *
	 * @return Le coup aleatoire choisi
	 */
	public String coupAleatoire() {
		int valeurAleatoire = OutilsJeu.valeurAleatoire(2);
		if (valeurAleatoire == 1) {
			return "t";
		} else {
			return "d";
		}
	}

	/**
	 * Methode servant a choisir l'indice d'un Navire de facon aleatoire
	 * 
	 *
	 * @return L'indice du navire a deplacer ( Entre 0 et 9 inclus)
	 */
	public int indiceNavireDeplacement() {
		return OutilsJeu.valeurZeroAleatoire(10);
	}

	/**
	 * Methode servant a choisir une direction aleatoire
	 * 
	 * @return La valeur de la direction choisi aleatoirement ( 0 : gauche | 1 :
	 *         haut | 2 : droite | 3 : bas )
	 */
	public int directionAleatoire() {
		int direction = OutilsJeu.valeurZeroAleatoire(4);
		System.out.println("DIRECTION ALEATOIRE TIREE : " + direction);
		return direction;
	}

	/**
	 * Methode servant a verifier si deplacement choisi aleatoirement est possible
	 * 
	 * @param indiceNavire:         Indice du Navire pour lequel on doit verifier
	 * @param directionDeplacement: direction du navire
	 *
	 * @return Vrai si le navire est coule , Faux sinon
	 */
	public boolean checkDeplacementAleatoire(int indiceNavire, int directionDeplacement) {
		ArrayList<Navire> listeNav = flotte.getListeNavire();

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

	/**
	 * Methode servant a verifier si le navire donnee en parametre est touche
	 * 
	 * @param indiceNavire: Indice du Navire pour lequel on doit verifier
	 *
	 * @return Vrai si le navire est touche , Faux sinon
	 */
	public boolean verifTouche(int indiceNavire) {
		Navire navire = flotte.getListeNavire().get(indiceNavire);

		if (navire.isTouche()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode servant a verifier si le navire donnee en parametre est coule
	 * 
	 * @param indiceNavire: Indice du Navire pour lequel on doit verifier
	 *
	 * @return Vrai si le navire est coule , Faux sinon
	 */
	public boolean verifCoule(int indiceNavire) {
		Navire navire = flotte.getListeNavire().get(indiceNavire);

		if (navire.isCoule()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode servant a savoir si le Navire donnee en parametre est touche et ou
	 * coule
	 * 
	 * @param indiceNavire: Indice du Navire pour lequel on doit verifier
	 *
	 * @return Vrai si le navire est deplacable , Faux sinon
	 */
	public boolean verifNavireDeplacable(int indiceNavire) {
		if ((verifTouche(indiceNavire) && verifCoule(indiceNavire))
				|| (verifCoule(indiceNavire) || verifTouche(indiceNavire))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Methode servant a savoir si un navire avec l'id donne en parametre est encore
	 * disponible
	 * 
	 * @param idNavire : Id du Navire qu'on recherche
	 *
	 * @return Vrai si un Navire de ce type est dispo | Faux sinon
	 */
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

	/**
	 * Methode servant a verifier s'il existe encore un navire qui n'a pas ete
	 * touche
	 * 
	 *
	 * @return Vrai si enccore un navire est deplacable , Faux sinon
	 */
	public boolean NbNavireDeplacable() {
		ArrayList<Navire> listeNavires = flotte.getListeNavire();
		int verif = 0;
		for (Navire navire : listeNavires) {
			if (!navire.isTouche()) {
				verif++;
			}
		}
		return verif > 0;
	}

	@Override
	public String toString() {
		return "Joueur [grilleJoueur=" + grilleJoueur + ", grilleEnnemi=" + grilleEnnemi + ", flotte=" + flotte + "]";
	}

}

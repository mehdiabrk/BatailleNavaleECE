package config;

public class ConfigurationJeu {
	public static final int LARGEUR_FENETRE = 480;
	public static final int HAUTEUR_FENETRE = 480;

	public static final int TAILLE_BLOC = 30;

	public static final int NB_LIGNE = HAUTEUR_FENETRE / TAILLE_BLOC; // NB_LIGNE = 16
	public static final int NB_COLONNE = LARGEUR_FENETRE / TAILLE_BLOC; // NB_LIGNE = 16

	public static final String SIGNE_CUIRASSE = "cu";
	public static final int ID_CUIRASSE = 1;
	public static final int TAILLE_CUIRASSE = 7;
	public static final int IMPACT_CUIRASSE = 9;

	public static final String SIGNE_CROISSEUR = "cr";
	public static final int ID_CROISSEUR = 2;
	public static final int TAILLE_CROISSEUR = 5;
	public static final int IMPACT_CROISSEUR = 4;

	public static final String SIGNE_DESTROYER = "dd";
	public static final int ID_DESTROYER = 3;
	public static final int TAILLE_DESTROYER = 3;
	public static final int IMPACT_DESTROYER = 1;

	public static final String SIGNE_SOUSMARIN = "ss";
	public static final int ID_SOUSMARIN = 4;
	public static final int TAILLE_SOUSMARIN = 1;
	public static final int IMPACT_SOUSMARIN = 1;

}

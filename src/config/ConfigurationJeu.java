package config;

public class ConfigurationJeu {
	public static final int LARGEUR_FENETRE = 480;
	public static final int HAUTEUR_FENETRE = 480;

	public static final int TAILLE_BLOC = 30;

	public static final int NB_LIGNE = HAUTEUR_FENETRE / TAILLE_BLOC; // NB_LIGNE = 16
	public static final int NB_COLONNE = LARGEUR_FENETRE / TAILLE_BLOC; // NB_LIGNE = 16

	public static String INDICE_LIGNE = " ,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,q";
	public static String INDICE_COLONNE = " ,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16";
	
	public static String ANSI_NOIR = "\u001B[30m";
	public static String ANSI_ROUGE = "\u001B[31m";
	public static String ANSI_VERT = "\u001B[32m";
	public static String ANSI_JAUNE = "\u001B[33m";
	public static String ANSI_BLEU= "\u001B[34m";
	public static String ANSI_VIOLET = "\u001B[35m";
	public static String ANSI_CYAN = "\u001B[36m";
	public static String ANSI_BLANC = "\u001B[37m";
	public static String ANSI_RESET = "\u001B[0m";
	
	public static int NB_NAVIRE = 10;

	public static final String NOM_CUIRASSE = "Cuirasse";
	public static final String SIGNE_CUIRASSE = "cu";
	public static final int ID_CUIRASSE = 1;
	public static final int TAILLE_CUIRASSE = 7;
	public static final int IMPACT_CUIRASSE = 9;

	public static final String NOM_CROISSEUR = "Croisseur";
	public static final String SIGNE_CROISSEUR = "cr";
	public static final int ID_CROISSEUR = 2;
	public static final int TAILLE_CROISSEUR = 5;
	public static final int IMPACT_CROISSEUR = 4;

	public static final String NOM_DESTROYER = "Destroyer";
	public static final String SIGNE_DESTROYER = "dd";
	public static final int ID_DESTROYER = 3;
	public static final int TAILLE_DESTROYER = 3;
	public static final int IMPACT_DESTROYER = 1;
	public static int NB_FUSEE_ECLAIRANTE = 3;

	public static final String NOM_SOUSMARIN = "Sous-Marin";
	public static final String SIGNE_SOUSMARIN = "ss";
	public static final int ID_SOUSMARIN = 4;
	public static final int TAILLE_SOUSMARIN = 1;
	public static final int IMPACT_SOUSMARIN = 1;
	
	public static boolean WIN = false;

}

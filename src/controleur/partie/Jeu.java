/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * BatailleNavale
 *
 * 26 nov. 2022
 */
package controleur.partie;

public class Jeu {

	private Joueur joueur;
	private Joueur robot;
	private boolean modeTriche;

	/**
	 * @param joueur
	 * @param robot
	 * @param modeTriche
	 */
	public Jeu(Joueur joueur, Joueur robot, boolean modeTriche) {
		this.joueur = joueur;
		this.robot = robot;
		this.modeTriche = modeTriche;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Joueur getRobot() {
		return robot;
	}

	public boolean isModeTriche() {
		return modeTriche;
	}
	
	public void affichePartie() {
		System.out.println("VOTRE CARTE CONTENANT VOS NAVIRES : ");
		joueur.getCarteJoueur().afficheCarte();
		
		System.out.println("LA CARTE DE L'ENNEMI : ");
		joueur.getCarteEnnemi().afficheCarteEnnemi(modeTriche);
	}

}

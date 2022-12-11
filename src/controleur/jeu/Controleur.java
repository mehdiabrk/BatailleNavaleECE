/**
 * @author ABERKANE | HANNA | BELARBI | RODRIGUES DA COSTA
 *
 * EceBatailleNavale
 *
 * 4 déc. 2022
 */
package controleur.jeu;

import java.util.Scanner;

import config.ConfigurationJeu;
import controleur.partie.Joueur;
import controleur.partie.Partie;

public class Controleur {

	private String winnerName;

	public Controleur() {
		this.winnerName = null;

		System.out.println("\nBienvenue dans le jeu Bataille Navale !!! \n");

		try (Scanner in = new Scanner(System.in)) {

			System.out.println(
					"Vous voulez jouer en mode console ou en mode graphique ? (Console => c || Graphique => g)");
			String scanValue = in.nextLine();

			while (!(scanValue.equals("c") || scanValue.equals("g"))) {
				System.out.println(
						"\nVous n\'avez pas rentré \"n\" ou \"o\" , veuillez respecter la consigne demandé !!!!");
				System.out.println(
						"Vous voulez jouer en mode console ou en mode graphique ? (Console => c || Graphique => g)");
				scanValue = in.nextLine(); // Lecture de la console
			}

			if (scanValue.equals("c")) {
				runConsole(in);
			} else {
				runGUI();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String verifWin(Partie p) {
		Joueur joueur = p.getJoueur();
		Joueur robot = p.getRobot();

		if (joueur.getNbNavireCoule() == ConfigurationJeu.NB_NAVIRE) {
			return "Robot";
		} else if (robot.getNbNavireCoule() == ConfigurationJeu.NB_NAVIRE) {
			return "Joueur";
		}
		return null;
	}

	public void runConsole(Scanner in) {
		Partie p = new Partie(in);

		while (!ConfigurationJeu.WIN) {
			p.tourJoueur(in);
			p.tourRobot();

			winnerName = verifWin(p);
			if (winnerName != null) {
				ConfigurationJeu.WIN = true;
			}
		}
	}

	private void runGUI() {
		// TODO Auto-generated method stub
	}

}

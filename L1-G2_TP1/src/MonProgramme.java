import java.util.Scanner;

public class MonProgramme {

	/*
	 * Créez un nouveau projet, une nouvelle classe et écrivez un programme Java qui demande
	 * à l'utilisateur deux chaînes de caractères et qui les affiche à l'écran, dans un ordre
	 * puis dans l'autre. Les variables sont de type String. La lecture des String se fait avec ...=s.nextLine();
		Voici un exemple d’exécution du programme :
		Chaine 1 ? BLABLA
		Chaine 2 ? BOUMBLABLA BOUM
		BOUM BLABLA
	 */

    public static void main(String[] args) {
        // Je crée 2 variables de type String = chaînes de caractères
        String chaine1, chaine2;

        // Je crée une variable scanner pour lire les entrées utilisateur
        Scanner s = new Scanner(System.in);

        // Je demande la chaîne 1
        System.out.println("Chaîne 1 : ");

        // Je stocke la chaîne 1
        chaine1 = s.nextLine();

        // Je demande la chaîne 2
        System.out.println("Chaîne 2 : ");

        // Je stocke la chaîne 2
        chaine2 = s.nextLine();

        System.out.println(chaine1 + " "+ chaine2);
        System.out.println(chaine2 + " " + chaine1);
    }

}

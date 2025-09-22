import java.util.Scanner;

public class Exo1 {
    // Affichage du périmètre et de la surface d'un cercle étant donné son rayon

    public static void main(String[] args) {
        double rayon,perimetre,surface;
        // Saisie des données
        Scanner s = new Scanner(System.in);
        System.out.println("Quel est le rayon de votre cercle ?");
        rayon=s.nextInt();
        // Calcul du périmètre et de la surface
        perimetre = 2*rayon*Math.PI;
        surface = rayon*rayon*Math.PI;
        // Affichage des résultats
        System.out.println("Le perimetre est de "+perimetre+" et la surface est de "+surface);
    }

}

import java.util.Scanner;

public class Annuaire {
    final static String fin = "_FIN";  // Dédicace Andrei

    public static void main(String[] args){
        /* Question a :
            Créez les deux tableaux dans le main. Pour simplifier le test des premières questions,
            remplissez les 3 premières cases des tableaux avec des valeurs fictives comme dans l'exemple ci-dessus.
            Les cases d’indice 2 doivent donc contenir la chaîne "_FIN".
        */

        // Création des 2 tableaux
        String[] tableauNoms = new String[100];
        String[] tableauNumeros = new String[100];

        // Remplissage avec des données fictives
        tableauNoms[0] = "Dima";
        tableauNumeros[0] = "0123456789";
        tableauNoms[1] = "Raphaël";
        tableauNumeros[1] = "9876543210";
        tableauNoms[2] = fin;
        tableauNumeros[2] = fin;

        // Appel de l'action d'affichage
        afficher(tableauNoms, tableauNumeros);

        /* Question c :
            Créez la boucle principale qui lit un caractère et qui boucle tant que l'utilisateur ne tape pas 'q'.
         */
        Scanner s = new Scanner(System.in);
        System.out.println("Bienvenue à ton annuaire téléphonique Java");
        char entreeUtilisateur = ' ';

        do {    // Dédicace Mouhamadou
            System.out.println("(a)jouter (r)echerche (l)ister (q)uitter :");
            entreeUtilisateur = s.nextLine().charAt(0);

            if(entreeUtilisateur == 'l'){
                afficher(tableauNoms, tableauNumeros);
            }

        } while(entreeUtilisateur != 'q');

        System.out.println("Merci et à bientôt !");
    }

    /*
        Écrire une action qui permet d'afficher le contenu de l’annuaire (une ligne par personne) et
        appelez-la dans le main. Cette action prend les deux tableaux en paramètre.
     */
    public static void afficher(String[] tabNoms, String[] tabNumeros){
        for(int i = 0; i < tabNoms.length; i++){
            if(!tabNoms[i].equals(fin)){
                System.out.println(tabNoms[i] + " / " + tabNumeros[i]);
            } else {
                break;
            }
        }
    }
}

import java.util.Scanner;

public class Annuaire {
    final static String fin = "_FIN";  // Dédicace Andrei

    public static void main(String[] args) {
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
        ordonnerAnnuaireAlphabetiquement(tableauNoms, tableauNumeros);
        afficher(tableauNoms, tableauNumeros);

        /* Question c :
            Créez la boucle principale qui lit un caractère et qui boucle tant que l'utilisateur ne tape pas 'q'.
         */
        Scanner s = new Scanner(System.in);
        System.out.println("Bienvenue à ton annuaire téléphonique Java");
        char entreeUtilisateur;

        do {    // Dédicace Mouhamadou
            System.out.println("(a)jouter (r)echerche (l)ister (s)upprimer (q)uitter :");
            entreeUtilisateur = s.nextLine().charAt(0);

            switch (entreeUtilisateur) {
                case 'l':
                    // Affichage annuaire
                    afficher(tableauNoms, tableauNumeros);
                    break;

                case 'r':
                    // Recherche dans l'anuaire
                    System.out.println("Quel est le nom recherché ?");
                    // On récupère le nom en lettres minuscules pour faciliter la recherche sans se soucier de
                    // la casse
                    String nomATrouver = s.nextLine().toLowerCase();
                    System.out.println("Le numéro de téléphone est " + rechercher(tableauNoms, tableauNumeros, nomATrouver));
                    break;

                case 'a':
                    // Ajouter un nom et un numéro
                    System.out.println("Quel est le nom à ajouter ? ");
                    String nomAAjouter = s.nextLine();
                    System.out.println("Quel est le numéro à ajouter ? ");
                    String numeroAAjouter = s.nextLine();
                    ajouter(tableauNoms, tableauNumeros, nomAAjouter, numeroAAjouter);
                    ordonnerAnnuaireAlphabetiquement(tableauNoms, tableauNumeros);
                    break;

                case 's':
                    // Supprimer une entrée
                    System.out.println("Quel est le nom à supprimer ? ");
                    String nomASupprimer = s.nextLine();
                    supprimer(tableauNoms, tableauNumeros, nomASupprimer);
                    ordonnerAnnuaireAlphabetiquement(tableauNoms, tableauNumeros);
                    break;

                default:
                    System.out.println("Entrée non correcte, veuillez réessayer");
            }
        } while (entreeUtilisateur != 'q');

        System.out.println("Merci et à bientôt !");
    }

    /*
        Écrire une action qui permet d'afficher le contenu de l’annuaire (une ligne par personne) et
        appelez-la dans le main. Cette action prend les deux tableaux en paramètre.
     */
    public static void afficher(String[] tabNoms, String[] tabNumeros) {
        // On itère sur le tableau
        for (int i = 0; i < tabNoms.length; i++) {
            // Tant qu'on n'est pas arrivé au mot _FIN, on affiche le nom et le numéro
            if (!tabNoms[i].equals(fin)) {
                System.out.println(tabNoms[i] + " " + tabNumeros[i]);
            } else {
                break;
            }
        }
    }

    /**
     * Écrire une fonction renvoyant le numéro de téléphone à partir du nom,
     * ou la chaîne vide si le nom n'existe pas. Complétez le main en appelant cette fonction.
     */
    public static String rechercher(String[] tabNoms, String[] tabNumeros, String nomATrouver) {
        // On fait une boucle qui va itérer jusqu'au dernier élément du tableau ET qui s'arrête dès
        // qu'elle trouve le mot _FIN
        for (int i = 0; (i < tabNoms.length) && (!tabNoms[i].equals(fin)); i++) {
            if (tabNoms[i].toLowerCase().equals(nomATrouver)) {
                // J'ai trouvé le nom, donc je renvoie le numéro correspondant
                return tabNumeros[i];
            }
        }

        // Si je suis ici, c'est que le nom n'a pas été trouvé, donc on renvoie la chaîne vide
        return "";
    }

    /**
     * Écrire le code permettant d’ajouter une personne : demander le nom et le numéro,
     * vérifier que ce nom n’existe pas déjà, vérifier que les tableaux ne sont pas pleins
     * et ranger ces informations à la place de la chaîne "_FIN" qui sera décalée d’un cran à droite.
     */
    public static void ajouter(String[] tabNoms, String[] tabNumeros, String nomAAjouter, String numeroAAjouter) {
        String nomDansAnnuaire = rechercher(tabNoms, tabNumeros, nomAAjouter.toLowerCase());
        if(nomDansAnnuaire.isEmpty()){
            // Cas où le nom n'est pas dans l'anuaire
            // On va vérifier l'indice de _FIN
            int indiceFin = 0;

            for(int i = 0; i < tabNoms.length; i++){
                if(tabNoms[i].equals(fin)) {
                    indiceFin = i;
                    break;
                }
            }

            if(indiceFin < tabNoms.length - 1){
                // Cas 1 : l'indice de fin est inférieur à 99, on fait l'ajout
                // On va stocker le nom et le numéro à la place des mots _FIN
                tabNoms[indiceFin] = nomAAjouter;
                tabNumeros[indiceFin] = numeroAAjouter;

                // On va décaler à droite le mot _FIN pour les noms et numéros
                tabNoms[indiceFin+1] = fin;
                tabNumeros[indiceFin+1] = fin;
            } else {
                // Cas 2 : l'indice de fin est à 99, alors l'annuaire est plein et on informe l'utilisateur qu'on ne peut pas ajouter
                System.out.println("Annuaire plein. Ajout non réalisé");
            }
        } else {
            // Cas où le nom est dans l'annuaire
            // On va mettre à jour le numéro de ce nom
            // On itère sur les tableaux
            for(int i = 0; i < tabNoms.length; i++){
                if(tabNoms[i].equals(nomAAjouter)){
                    // Le nom a été trouvé donc on met à jour le numéro puis on casse la boucle pour sortir
                    tabNumeros[i] = numeroAAjouter;
                    break;
                }
            }
        }
    }

    /** Bonus 1 : Supprimer une entrée de l'annuaire */
    private static void supprimer(String[] tabNoms, String[] tabNumeros, String nomASupprimer) {
        String nomDansAnnuaire = rechercher(tabNoms, tabNumeros, nomASupprimer.toLowerCase());

        if(!nomDansAnnuaire.isEmpty()){
            // Le nom est bien dans l'annuaire donc on le supprime
            for(int i = 0; i < tabNoms.length && !tabNoms[i].equals(fin); i++){
                if(tabNoms[i].equals(nomASupprimer)){
                    // On a trouvé le nom donc on décale tous les éléments à gauche d'une case
                    for(int j = i; j < tabNoms.length - 1; j++){
                        tabNoms[j] = tabNoms[j + 1];
                        tabNumeros[j] = tabNumeros[j + 1];

                        // Si on attend fin alors on peut arrêter le travail de décalage
                        if(tabNoms[j].equals(fin)){
                            break;
                        }
                    }
                    System.out.println(nomASupprimer + " a bien été supprimé de l'annuaire.");
                    // on casse la boucle car on a supprimé l'élément et on a tout réarrangé
                    break;
                }
            }
        } else {
            // Le nom n'existe pas dans l'anuaire
            System.out.println("Le nom " + nomASupprimer + " n'existe pas dans l'anuaire.");
        }
    }

    /** Bonus 2 : Ordonner l'annuaire par ordre alphabétique */
    private static void ordonnerAnnuaireAlphabetiquement(String[] tabNoms, String[] tabNumeros) {
        // On va utiliser l'algorithme Bubble Sort
        boolean echange;
        int taille = 0;

        // D'abord on trouve la taille des éléments stockés
        for(int i = 0; i < tabNoms.length; i++){
            if(tabNoms[i].equals(fin)){
                taille = i;
                break;
            }
        }

        // Maintenant on peut ordonner les tableaux
        do{
            echange = false;
            for(int i = 0; i < taille - 1; i++){
                if(tabNoms[i].compareToIgnoreCase(tabNoms[i + 1]) > 0){
                    // On doit échanger les éléments des 2 tableaux
                    String tempNom = tabNoms[i];
                    String tempNumero = tabNumeros[i];

                    tabNoms[i] = tabNoms[i + 1];
                    tabNumeros[i] = tabNumeros[i + 1];

                    tabNoms[i + 1] = tempNom;
                    tabNumeros[i + 1] = tempNumero;

                    echange = true;
                }
            }
        } while(echange);
    }
}

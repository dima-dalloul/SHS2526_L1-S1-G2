import java.io.FileReader;
import java.io.FileWriter;
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

        ordonnerAnnuaireAlphabetiquement(tableauNoms, tableauNumeros);

        // Appel de l'action d'affichage
        afficher(tableauNoms, tableauNumeros);

        /* Question c :
            Créez la boucle principale qui lit un caractère et qui boucle tant que l'utilisateur ne tape pas 'q'.
         */
        Scanner s = new Scanner(System.in);
        System.out.println("Bienvenue à ton annuaire téléphonique Java");
        char entreeUtilisateur;

        do {    // Dédicace Mouhamadou
            System.out.println("(a)jouter (r)echerche (l)ister (s)upprimer (w)sauvegarder (t)restaurer (q)uitter :");
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
                    // Recherche basique
                    System.out.println("Le numéro de téléphone est " + rechercher(tableauNoms, tableauNumeros, nomATrouver));
                    // Recherche dichotomique
                    System.out.println("Le numéro de téléphone est " + rechercheDichotomique(tableauNoms, tableauNumeros, nomATrouver));
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

                case 'w':
                    // Sauvegarder l'annuaire dans un fichier
                    sauvegarderAnnuaireDansFichier(tableauNoms, tableauNumeros);
                    break;

                case 't':
                    // Restaurer l'annuaire depuis un fichier
                    restaurerAnnuaireDepuisFichier(tableauNoms, tableauNumeros);
                    break;

                case 'q':
                    // Quitter
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
     * Bonus. Modifier la recherche pour tirer parti du fait que les éléments sont ordonnés.
     * Vous pourrez ainsi programmer une recherche dichotomique sur les éléments du tableau
     * entre les indices inf et sup (initialement, 0 et tab.length-1). Le principe est de
     * comparer l’élément à rechercher avec l’élément médian tab[m], c’est-à-dire l’élément au
     * milieu du tableau, d’indice m=(inf+sup)/2. Si tab[m] est l’élément recherché, on arrête.
     * Si tab[m] se situe après l’élément recherché dans l’ordre lexicographique, on recommence
     * la recherche sur le sous-tableau entre les indices 0 et m-1, sinon, on recommence sur le
     * sous-tableau entre les indices m+1 et tab.length-1.
     *
     * @param tabNoms
     * @param tabNumeros
     * @param nomATrouver
     * @return
     */
    public static String rechercheDichotomique(String[] tabNoms, String[] tabNumeros, String nomATrouver) {
        int debut = 0;
        int finIndice = 0;
        // On trouve l'indice de fin
        for (int i = 0; i < tabNoms.length; i++) {
            if (tabNoms[i].equals(fin)) {
                finIndice = i - 1; // On met -1 pour ne pas inclure le mot _FIN
                break;
            }
        }

        while (debut <= finIndice) {
            int milieu = (debut + finIndice) / 2;
            int comparaison = tabNoms[milieu].toLowerCase().compareTo(nomATrouver);
            if (comparaison == 0) {
                // J'ai trouvé le nom
                return tabNumeros[milieu];
            } else if (comparaison < 0) {
                // Le nom cherché est après le milieu
                debut = milieu + 1;
            } else {
                // Le nom cherché est avant le milieu
                finIndice = milieu - 1;
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
        if (nomDansAnnuaire.isEmpty()) {
            // Cas où le nom n'est pas dans l'anuaire
            // On va vérifier l'indice de _FIN
            int indiceFin = 0;

            for (int i = 0; i < tabNoms.length; i++) {
                if (tabNoms[i].equals(fin)) {
                    indiceFin = i;
                    break;
                }
            }

            if (indiceFin < tabNoms.length - 1) {
                // Cas 1 : l'indice de fin est inférieur à 99, on fait l'ajout
                // On va stocker le nom et le numéro à la place des mots _FIN
                tabNoms[indiceFin] = nomAAjouter;
                tabNumeros[indiceFin] = numeroAAjouter;

                // On va décaler à droite le mot _FIN pour les noms et numéros
                tabNoms[indiceFin + 1] = fin;
                tabNumeros[indiceFin + 1] = fin;
            } else {
                // Cas 2 : l'indice de fin est à 99, alors l'annuaire est plein et on informe l'utilisateur qu'on ne peut pas ajouter
                System.out.println("Annuaire plein. Ajout non réalisé");
            }
        } else {
            // Cas où le nom est dans l'annuaire
            // On va mettre à jour le numéro de ce nom
            // On itère sur les tableaux
            for (int i = 0; i < tabNoms.length; i++) {
                if (tabNoms[i].equals(nomAAjouter)) {
                    // Le nom a été trouvé donc on met à jour le numéro puis on casse la boucle pour sortir
                    tabNumeros[i] = numeroAAjouter;
                    break;
                }
            }
        }
    }

    /**
     * Bonus 1 : Supprimer une entrée de l'annuaire
     */
    private static void supprimer(String[] tabNoms, String[] tabNumeros, String nomASupprimer) {
        String nomDansAnnuaire = rechercher(tabNoms, tabNumeros, nomASupprimer.toLowerCase());

        if (!nomDansAnnuaire.isEmpty()) {
            // Le nom est bien dans l'annuaire donc on le supprime
            for (int i = 0; i < tabNoms.length && !tabNoms[i].equals(fin); i++) {
                if (tabNoms[i].equals(nomASupprimer)) {
                    // On a trouvé le nom donc on décale tous les éléments à gauche d'une case
                    for (int j = i; j < tabNoms.length - 1; j++) {
                        tabNoms[j] = tabNoms[j + 1];
                        tabNumeros[j] = tabNumeros[j + 1];

                        // Si on attend fin alors on peut arrêter le travail de décalage
                        if (tabNoms[j].equals(fin)) {
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

    /**
     * Bonus 2 : Ordonner l'annuaire par ordre alphabétique
     */
    private static void ordonnerAnnuaireAlphabetiquement(String[] tabNoms, String[] tabNumeros) {
        // On va utiliser l'algorithme Bubble Sort
        boolean echange;
        int taille = 0;

        // D'abord on trouve la taille des éléments stockés
        for (int i = 0; i < tabNoms.length; i++) {
            if (tabNoms[i].equals(fin)) {
                taille = i;
                break;
            }
        }

        // Maintenant on peut ordonner les tableaux
        do {
            echange = false;
            for (int i = 0; i < taille - 1; i++) {
                if (tabNoms[i].compareToIgnoreCase(tabNoms[i + 1]) > 0) {
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
        } while (echange);
    }


    /**
     * Écrire une action sauvegarder qui copie l’intégralité de l’annuaire dans un fichier
     * texte. Ce fichier doit contenir un nom et un numéro par ligne, séparés par un pointvirgule.
     * Il est inutile de copier les chaînes "_FIN". Sur l'exemple ci-dessus, la première
     * ligne du fichier doit donc être :
     * DUPONT;06 70..44
     * Ajouter un choix dans le menu proposé à l’utilisateur.
     *
     * @param tabNoms
     * @param tabNumeros
     */
    private static void sauvegarderAnnuaireDansFichier(String[] tabNoms, String[] tabNumeros) {
        // On va utiliser StringBuilder pour construire le contenu du fichier
        StringBuilder contenuFichier = new StringBuilder();

        // On itère sur les tableaux pour construire le contenu
        for (int i = 0; i < tabNoms.length; i++) {
            if (!tabNoms[i].equals(fin)) {
                contenuFichier.append(tabNoms[i]).append(";").append(tabNumeros[i]).append("\n");
            } else {
                break;
            }

            // Maintenant on écrit dans le fichier
            FileWriter fileWriter;

            try {
                fileWriter = new FileWriter("annuaire.txt");
                fileWriter.write(contenuFichier.toString());
                fileWriter.close();
                System.out.println("Annuaire sauvegardé avec succès dans annuaire.txt");
            } catch (Exception e) {
                System.out.println("Erreur lors de la sauvegarde de l'annuaire : " + e.getMessage());
            }
        }
    }

    /**
     * Écrire une action importer qui fait l'opération inverse en récupérant ce qui est dans
     * le fichier pour le copier dans le tableau. N'oubliez pas d'ajouter les chaînes "_FIN".
     *
     * @param tabNoms
     * @param tabNumeros
     */
    private static void restaurerAnnuaireDepuisFichier(String[] tabNoms, String[] tabNumeros) {
        // On va utiliser un Scanner pour lire le fichier
        Scanner fileScanner;
        try {
            FileReader fileReader = new FileReader("annuaire.txt");
            fileScanner = new Scanner(fileReader);
            int index = 0;

            // On lit le fichier ligne par ligne
            while (fileScanner.hasNextLine() && index < tabNoms.length) {
                String ligne = fileScanner.nextLine();
                String[] parties = ligne.split(";");

                if (parties.length == 2) {
                    tabNoms[index] = parties[0];
                    tabNumeros[index] = parties[1];
                    index++;
                }
            }

            // On ajoute le mot _FIN à la fin
            if (index < tabNoms.length) {
                tabNoms[index] = fin;
                tabNumeros[index] = fin;
            }

            fileScanner.close();
            System.out.println("Annuaire restauré avec succès depuis annuaire.txt");
        } catch (Exception e) {
            System.out.println("Erreur lors de la restauration de l'annuaire : " + e.getMessage());
        }
    }
}

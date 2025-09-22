import java.util.Scanner;
public class TP21 {
    public static void main (String[] args){
        int     jour = 0,
                mois = 0,
                nombreJoursPleins = 0;
        Scanner scanner = new Scanner(System.in);

        // Je demande à l'utilisateur de me donner les infos
        System.out.println("Quel est le numéro du jour : ");
        jour = scanner.nextInt();
        System.out.println("Quel est le numéro du mois : ");
        mois = scanner.nextInt();

        /* Pour compter le nombre de jours depuis le 1er janvier,  (
        31 pour février, 31+28=59 pour mars, 31+28+31=90 pour avril, 120 pour mai,
        151 pour juin, 181 pour juillet, 212 pour août, 243 pour septembre, 273 pour octobre,
        304 pour novembre et 334
        pour décembre).
         */
        if(mois == 1){ // Janvier
            nombreJoursPleins = 0;
        } else if(mois == 2){ // Février
            nombreJoursPleins = 31;
        } else if (mois == 3){ // Mars
            nombreJoursPleins = 31 + 28;
        } else if (mois == 4){ // Avril
            nombreJoursPleins = 31 + 28 + 31;
        } else if (mois == 5){ // Mai
            nombreJoursPleins = 31 + 28 + 31 + 30;
        } else if (mois == 6){ // Juin
            nombreJoursPleins = 31 + 28 + 31 + 30 + 31;
        } else if (mois == 7){ // Juillet
            nombreJoursPleins = 31 + 28 + 31 + 30 + 31 + 30;
        } else if (mois == 8){ // Aout
            nombreJoursPleins = 31 + 28 + 31 + 30 + 31 + 30 + 31;
        } else if (mois == 9){ // Septembre
            nombreJoursPleins = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
        } else if (mois == 10){ // Octobre
            nombreJoursPleins = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
        } else if (mois == 11){ // Novembre
            nombreJoursPleins = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
        } else if (mois == 12){ // Décembre
            nombreJoursPleins = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
        }

        // Vous pouvez additionner le numéro du jour avec le nombre de jours des mois pleins qui précèdent
        nombreJoursPleins = jour + nombreJoursPleins - 1; // -1 car on compte à partir du 1er janvier

        //Calculer le nombre de jours depuis le 1er janvier, prendre le reste de sa division par 7 et
        //tenir compte du fait que le 1er janvier était un mercredi.
        int resteDivisionPar7 = nombreJoursPleins % 7;
        // Ainsi on a récupéré le jour de la semaine en fonction du 1er janvier, qui est un mercredi.

        if(resteDivisionPar7 == 0) { // Mercredi
            System.out.println("Le " + jour + "/" + mois + " est un mercredi");
        } else if(resteDivisionPar7 == 1) { // Jeudi
            System.out.println("Le " + jour + "/" + mois + " est un jeudi");
        } else if(resteDivisionPar7 == 2) { // Vendredi
            System.out.println("Le " + jour + "/" + mois + " est un vendredi");
        } else if(resteDivisionPar7 == 3) { // Samedi
            System.out.println("Le " + jour + "/" + mois + " est un samedi");
        } else if(resteDivisionPar7 == 4) { // Dimanche
            System.out.println("Le " + jour + "/" + mois + " est un dimanche");
        } else if(resteDivisionPar7 == 5) { // Lundi
            System.out.println("Le " + jour + "/" + mois + " est un lundi");
        } else if(resteDivisionPar7 == 6) { // Mardi
            System.out.println("Le " + jour + "/" + mois + " est un mardi");
        }
    }
}
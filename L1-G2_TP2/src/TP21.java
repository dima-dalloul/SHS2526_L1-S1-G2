import java.util.Scanner;

/*

Le 23/9 est un vendredi


Pour compter le nombre de jours depuis le 1er janvier, vous pouvez additionner le numéro du jour avec le nombre
de jours des mois pleins qui précèdent (31 pour février, 31+28=59 pour mars, 31+28+31=90 pour avril, 120 pour mai,
151 pour juin, 181 pour juillet, 212 pour août, 243 pour septembre, 273 pour octobre, 304 pour novembre et 334
pour décembre).
Ainsi le 26 septembre est le 243+26 = 269e jour de l'année 2025. Comme le 1er janvier est le 1er jour de l'année,
il y a donc 269-1=268 jours d'écart entre les deux. Le reste de la division de 268 par 7 est 2. Donc le jour de
la semaine correspondant au 26 septembre est décalé de 2 jours par rapport à celui du 1er janvier. C'est donc un
vendredi.
Il est donc conseillé d'écrire votre programme de la manière suivante :
1.
lire depuis le clavier le numéro du jour et le numéro du mois ;
2.
calculer le nombre de jours depuis le 1er janvier avec une série de tests (si le mois est 1 alors... sinon si le
mois est 2 alors...) ;
3.
calculer le décalage avec le 1er janvier du jour de la semaine ;
4.
transformer ce nombre en un jour de la semaine avec une
 */
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
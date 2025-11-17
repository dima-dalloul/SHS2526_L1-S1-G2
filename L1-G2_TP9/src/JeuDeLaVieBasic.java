// Le jeu de la vie (Conway)

import java.awt.Color;

public class JeuDeLaVieBasic {

    final static int GSIZE = 50; // taille de la grille

    public static void initializeGrid(boolean[][] grille) {
        // initialisation de la grille
        for (int i = 0; i < GSIZE; i++) {
            for (int j = 0; j < GSIZE; j++) {
                grille[i][j] = false;
            }
        }

        // Question b - Initialisation du planeur ou glider
        // On va le faire manuellement
        grille[3][1] = true;
        grille[4][2] = true;
        grille[2][3] = true;
        grille[3][3] = true;
        grille[4][3] = true;
    }

    public static void drawGrid(boolean[][] grille, PixelScreen screen) {
        // affiche la grille
        for (int i = 0; i < GSIZE; i++) {
            for (int j = 0; j < GSIZE; j++) {
                if (grille[i][j]) screen.updatePixel(i, j, Color.BLACK);
                else screen.updatePixel(i, j, Color.WHITE);
            }
        }
    }

    public static boolean[][] updateGrid(boolean[][] grille) {
        // calcule l'état suivant de la grille
        boolean[][] update = new boolean[GSIZE][GSIZE];
        //....
        return(update);
    }

    public static void main(String[] args) {
        boolean[][] grille = new boolean[GSIZE][GSIZE];
        initializeGrid(grille);
        PixelScreen viewer = new PixelScreen(GSIZE, GSIZE, 16);
        while (true) {
            drawGrid(grille, viewer);
            viewer.refreshAfter(128);
            grille=updateGrid(grille);
            System.out.println("Nombre voisins vivants = " + countAliveNeighbors(grille, 2, 2));
            // Pour tester le cas extrême de [0,0] :
            // System.out.println("Nombre voisins vivants = " + countAliveNeighbors(grille, 0, 0));
        }
    }

    /**
     * Écrire la fonction countAliveNeighbors qui calcule le nombre de voisins vivants d'une case donnée.
     * Cette fonction a besoin de 3 paramètres. Ne partez pas dans un code compliqué, il faut juste considérer
     * chacun des 8 voisins l’un après l’autre et ajouter 1 au total ou non.
     * Attention quand vous accédez à la case immédiatement à gauche si vous êtes sur le bord de la grille (i=0).
     * Le mieux est de ne comptabiliser le voisin de gauche que si i>0. Même chose pour les autres bords de la
     * grille.
     * Testez votre fonction en ajoutant dans le main, juste après l'initialisation de la grille l'instruction suivante qui devrait afficher 3 :
     * System.out.println(countAliveNeighbors(grille,2,2));
     * d)
     */
    public static int countAliveNeighbors(boolean[][] grille, int x, int y) {
        int compteur = 0;

        // Représentation visuelle du travail à faire pour compter les voisins vivants du point T
        //  A(x-1,y-1)      B(x,y-1)    C(x+1,y-1)
        //  D(x-1,y)        T(x,y)      E(x+1,y)
        //  F(x-1,y+1)      G(x,y+1)    H(x+1,y+1)

        if (x > 0 && y > 0 && grille[x - 1][y - 1] == true) compteur++; // Point A

        if (y > 0 && grille[x][y - 1] == true) compteur++; // Point B

        if (x + 1 < GSIZE && y > 0 && grille[x + 1][y - 1] == true) compteur++; // Point C

        if (x > 0 && grille[x - 1][y] == true) compteur++; // Point D

        if (x + 1 < GSIZE && grille[x + 1][y] == true) compteur++; // Point E

        if (x > 0 && y + 1 < GSIZE && grille[x - 1][y + 1] == true) compteur++; // Point F

        if (y + 1 < GSIZE && grille[x][y + 1] == true) compteur++; // Point G

        if (x + 1 < GSIZE && y + 1 < GSIZE && grille[x + 1][y + 1] == true) compteur++; // Point H

        return compteur;
    }
}

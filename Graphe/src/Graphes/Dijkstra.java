package Graphes;

import Communes.Commune;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Christian TAGUEJOU on 10/06/2017.
 */
public class Dijkstra {

    static int HIGH = 1000000;


    /**
     * Algo Dijkstra du cours
     *
     * @param graphe
     * @param depart sommet de depart
     * @param arrive sommet d arrive
     * @return liste des sommets traités
     */
    public static ArrayList<Sommet> _Dijkstra(Graphe graphe, Sommet depart, Sommet arrive) {

        //Initialisation de l'algo
        ArrayList<Sommet> sommets = (ArrayList<Sommet>) graphe.getSommets().clone();
        ArrayList<Sommet> retour = (ArrayList<Sommet>) graphe.getSommets().clone();
        int indice_depart = sommets.indexOf(depart);

        ArrayList<Sommet> firstSuccesseur = depart.getSuccesseur();
        double[] poids = new double[sommets.size()];

        for (int i = 0; i < sommets.size(); i++) {
            if (i != indice_depart)
                poids[i] = HIGH;
            else
                poids[i] = 0;
        }

        for (int i = 0; i < firstSuccesseur.size(); i++) {
            if (i != indice_depart) {
                int indice = sommets.indexOf(firstSuccesseur.get(i));
                Arc tmp = new Arc(depart, sommets.get(indice));
                poids[indice] = tmp.getPoids();
                retour.get(indice).setPredecesseur(depart);
            }
        }

        sommets.remove(depart);

        while (!sommets.isEmpty()) {
            Sommet x = getMin(poids, sommets, graphe);
            int i_x = retour.indexOf(x);
            sommets.remove(x);
            ArrayList<Sommet> successeurs = x.getSuccesseur();

            for (int i = 0; i < successeurs.size(); i++) {
                Arc arc_tmp = new Arc(x, successeurs.get(i));
                int i_successeur = retour.indexOf(successeurs.get(i));
                if (poids[i_x] + arc_tmp.getPoids() < poids[i_successeur]) {
                    poids[i_successeur] = poids[i_x] + arc_tmp.getPoids();
                    retour.get(i_successeur).setPredecesseur(x);
                }
            }
            successeurs.clear();
        }
        ArrayList<Sommet> chemin = calcPath(arrive);
        return chemin;
    }

    /**
     * Calcul le chemin le plus court après l'algo de Dijkstra
     * @param arrive Sommet d arrive
     * @return Liste Sommet dans l'ordre pour le chemin le plus court
     */
    public static ArrayList<Sommet> calcPath(Sommet arrive) {
        ArrayList<Sommet> chemin = new ArrayList<Sommet>();
        chemin.add(arrive);
        Sommet courant = arrive.getPredecesseur();
        while (courant != null) {
            chemin.add(courant);
            courant = courant.getPredecesseur();
        }
        Collections.reverse(chemin);
        return chemin;

    }

    public static ArrayList<Sommet> dijkstra_plus(Graphe graphe, Sommet depart, Sommet arrive){
        //Initialisation de l'algo

        depart.setCout(0);
        PriorityQueue<Sommet> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(depart);

        for (Sommet sommet : graphe.getSommets()) {
            if (sommet != depart)
                sommet.setCout(Arc.distanceVolOiseau(sommet,depart));
        }

        while(!priorityQueue.isEmpty()){
            Sommet courant = priorityQueue.poll();
            for(Sommet sommet : courant.getSuccesseur()){
                double distance = Arc.distanceVolOiseau(sommet,courant)+courant.getCout();
                if(distance<sommet.getCout()){
                    priorityQueue.remove(sommet);
                    sommet.setCout(distance);
                    sommet.setPredecesseur(courant);
                    priorityQueue.add(sommet);
                }
            }
        }

        return calcPath(arrive);
    }



    /**
     * Calcul le plus petit Sommet de la liste sommets
     * @param poids tableau de poids des sommets
     * @param sommets liste des sommets
     * @param graphe graphe
     * @return plus petit Sommet
     */
    public static Sommet getMin(double[] poids, ArrayList<Sommet> sommets, Graphe graphe) {
        double min = HIGH;
        int i_min = 0;
        for (int i = 0; i < sommets.size(); i++) {
            int indice = graphe.getSommets().indexOf(sommets.get(i));
            if (poids[indice] < min) {
                min = poids[indice];
                i_min = i;
            }
        }
        return sommets.get(i_min);
    }

    public static void Skiplist(Graphe graphe, Sommet depart, Sommet arrive) {
        //Initialisation de l'algo
        ArrayList<Sommet> sommets = (ArrayList<Sommet>) graphe.getSommets().clone();
        ArrayList<Sommet> retour = (ArrayList<Sommet>) graphe.getSommets().clone();
        int indice_depart = sommets.indexOf(depart);

        ArrayList<Sommet> firstSuccesseur = depart.getSuccesseur();
        double[] poids = new double[sommets.size()];

        for (int i = 0; i < sommets.size(); i++) {
            if (i != indice_depart)
                poids[i] = HIGH;
            else
                poids[i] = 0;
        }

        for (int i = 0; i < firstSuccesseur.size(); i++) {
            if (i != indice_depart) {
                int indice = sommets.indexOf(firstSuccesseur.get(i));
                Arc tmp = new Arc(depart, sommets.get(indice));
                poids[indice] = tmp.getPoids();
                retour.get(indice).setPredecesseur(depart);
                System.out.println(poids[i]);
            }
        }

        //Principe
        //On va parcourir les villes pour atteindre la ville de poids = 0 (ville d'arrivée)
        //Au départ, on parcours les successeurs de la ville de depart
        //On va prendre celui qui a la poids le moins élevés
        //Ensuite, on va parcourir ses successeurs en choisissant le poids min
        //Ainsi de suite jusqu'a atteindre la ville avec poids = 0
    }

}

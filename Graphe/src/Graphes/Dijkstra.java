package Graphes;

import Communes.Commune;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 10/06/2017.
 */
public class Dijkstra {

    public static void _Dijkstra(Graphe graphe){
        //Initialisation de l'algo
        ArrayList<Sommet> sommets= graphe.getSommets();
        int[] poids = new int[sommets.size()];
        for(int i =0; i<sommets.size();i++){
            Sommet tmp = sommets.get(i);
            ArrayList<Sommet> successeurs = tmp.getSuccesseur();
            //System.out.println(i);
            for(int j = 0; j<successeurs.size();j++){
                //System.out.println(sommets.indexOf(successeurs.get(j)));
                System.out.println(sommets.get(i)+"-> "+successeurs.get(j));
            }
        }
    }
}

package Graphes;

import Communes.Commune;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 10/06/2017.
 */
public class Dijkstra {

    static int HIGH = 1000000;

    /**
     * Algo Dijkstra du cours
     * @param graphe
     * @param depart sommet de depart
     * @param arrive sommet d arrive
     * @return tableau de poids
     */
    public static double[] _Dijkstra(Graphe graphe, Sommet depart, Sommet arrive) {
        //Initialisation de l'algo
        ArrayList<Sommet> sommets = (ArrayList<Sommet>)graphe.getSommets().clone();
        ArrayList<Arc> arcs = graphe.getArcs();
        int indice_depart = sommets.indexOf(depart);
        System.out.println("indice depart " + indice_depart);

        ArrayList<Sommet> firstSuccesseur = depart.getSuccesseur();
        double[] poids = new double[sommets.size()];

        for(int i=0; i< sommets.size();i++){
            if(i!=indice_depart)
                poids[i] = HIGH;
            else
                poids[i]=0;
        }

        for (int i = 0; i < firstSuccesseur.size(); i++) {
            if(i!=indice_depart){
                Arc tmp = new Arc(depart, sommets.get(i));
                if (arcs.contains(tmp)){
                    poids[i] = tmp.getPoids();
                    sommets.get(i).setPredecesseur(depart);
                }

            }


        }

        sommets.remove(depart);

        while (!sommets.isEmpty()){
            Sommet x = getMin(poids, sommets);
            int i_x = graphe.getSommets().indexOf(x);
            sommets.remove(x);
            ArrayList<Sommet> successeurs = x.getSuccesseur();
            for(int i = 0; i<successeurs.size();i++){
                Arc arc_tmp = new Arc(x,successeurs.get(i));
                if(poids[i_x]+arc_tmp.getPoids()<poids[i]){
                    poids[i] = poids[i_x] + arc_tmp.getPoids();
                    successeurs.get(i).setPredecesseur(x);
                }
            }
            successeurs.clear();
        }

        return poids;
    }

    public static Sommet getMin(double[] poids, ArrayList<Sommet> sommets){
        double min = HIGH;
        int i_min =0;
        for(int i=0; i< sommets.size();i++){
            if(poids[i]<min){
                min = poids[i];
                i_min = i;
            }

        }
        return sommets.get(i_min);
    }

//    public Arc getSommetPlusPetiteDistance(ArrayList<Sommet> sommets){
//        Sommet sommetPlusPetit = sommets.get(0);
//        Arc arc = new Arc(null,null);
//        for(int i=1;i<sommets.size();i++){
//            if(Arc.distanceVolOiseau(sommets.get(i),sommetPlusPetit)<HIGH)
//        }
//        return Arc;
//    }
}

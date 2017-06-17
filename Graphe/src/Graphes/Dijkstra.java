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
     * @return liste des sommets traités
     */
    public static ArrayList<Sommet> _Dijkstra(Graphe graphe, Sommet depart, Sommet arrive) {
        //Initialisation de l'algo
        ArrayList<Sommet> sommets = (ArrayList<Sommet>)graphe.getSommets().clone();
        ArrayList<Sommet> retour = (ArrayList<Sommet>)graphe.getSommets().clone();
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
                int indice = sommets.indexOf(firstSuccesseur.get(i));
                Arc tmp = new Arc(depart, sommets.get(indice));
                poids[indice] = tmp.getPoids();
                retour.get(indice).setPredecesseur(depart);
            }
        }
//
//        System.out.println("Liste des poids");
//        for(int i=0; i<poids.length;i++){
//            if(poids[i]!=HIGH)
//                System.out.println("poids "+retour.get(i)+" : "+poids[i]);
//        }

        sommets.remove(depart);
        //System.out.println("depart ="+depart);

        while (!sommets.isEmpty()){
            Sommet x = getMin(poids, sommets,graphe);
            int i_x = retour.indexOf(x);
            sommets.remove(x);
            ArrayList<Sommet> successeurs = x.getSuccesseur();
            System.out.println(x+" poids " +poids[i_x]+"\n");

            for(int i = 0; i<successeurs.size();i++){
                Arc arc_tmp = new Arc(x,successeurs.get(i));
                int i_successeur = retour.indexOf(successeurs.get(i));
                //System.out.println("poids "+successeurs.get(i)+" "+poids[i_successeur]+"\n");
                if(poids[i_x]+arc_tmp.getPoids()<poids[i_successeur]){
                    System.out.println("-> "+successeurs.get(i)+"\n" );
                    poids[i_successeur] = poids[i_x] + arc_tmp.getPoids();
                    retour.get(i_successeur).setPredecesseur(x);
                }
            }
            successeurs.clear();
        }
        test(retour,depart,arrive);
        return retour;
    }

    public static void test( ArrayList<Sommet> dijkstra , Sommet depart, Sommet arrive){
        ArrayList<Sommet> chemin = new ArrayList<Sommet>();
        int indice_arrive = dijkstra.indexOf(arrive);
        System.out.println("chemin : "+depart+"\n");
        Sommet courant = dijkstra.get(indice_arrive).getPredecesseur();
        while(courant!=null){
            chemin.add(courant);
            courant = courant.getPredecesseur();
        }
        for(int i)

        System.out.println(chemin);



    }

    public static Sommet getMin(double[] poids, ArrayList<Sommet> sommets, Graphe graphe){
        double min = HIGH;
        int i_min =0;
        for(int i=0; i< sommets.size();i++){
            int indice = graphe.getSommets().indexOf(sommets.get(i));
            if(poids[indice]<min){
                min = poids[indice];
                i_min = i;
            }

        }
//        int indice = graphe.getSommets().indexOf(sommets.get(i_min));
//        System.out.println("Min retourné pour "+sommets.get(i_min)+poids[indice]);
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

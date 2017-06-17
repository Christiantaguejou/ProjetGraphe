package Graphes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import static javafx.scene.input.KeyCode.T;

/**
 * Created by Christian TAGUEJOU on 24/05/2017.
 */
public class Aetoile {

    static ArrayList<Sommet> sommetVisiter;

    public static LinkedList<Sommet> algo(ArrayList<Sommet> listeSommet, Sommet depart, Sommet arrive) {
        PriorityQueue<Sommet> openList = new PriorityQueue<>();
        LinkedList<Sommet> closedList = new LinkedList<>();
        openList.add(depart);
        Sommet courant;
        // 
        while (!openList.isEmpty() && !openList.contains(arrive)) {
            System.out.println("Debut Algo :");
            System.out.println("Affichage de la PriorityQueue");
            System.out.println(openList);
            System.out.println("Fin Affichage de la PriorityQueue");
             courant = openList.poll();
            System.out.println(courant.getCommune().getNom()+"\n");
            closedList.add(courant);
            System.out.println("Affichage de la closedList");
            System.out.println(closedList);
            System.out.println("Fin Affichage de la closedList");
            //parcours de la liste de successeurs
           //for (Sommet tmp : courant.getSuccesseur()) {
                for (Sommet tmp : courant.getSuccesseur()) {
                System.out.println("Sommet a tester  : "+tmp.getCommune().getNom());
                if (!closedList.contains(tmp)) {
                    System.out.println("Sommet  : "+tmp.getCommune().getNom());
                    tmp.setPredecesseur(courant);
                    System.out.println("Predecesseur : "+tmp.getPredecesseur().getCommune().getNom());
                    tmp.sethCost(arrive);
                    tmp.setgCost(courant);
                    if(!openList.contains(tmp)) openList.add(tmp);
                } else {
                    if (tmp.getgCost() > tmp.calculateGcost(courant)) {
                        tmp.setPredecesseur(courant);
                        tmp.setgCost(courant);
                    }
                }
            }
                System.out.println(openList);
                System.out.println(closedList);
        }
        arrive.setPredecesseur(closedList.getLast());
        return calcPath(depart,arrive);
    }

    private static LinkedList<Sommet> calcPath(Sommet depart, Sommet arrive) {
        // TODO if invalid nodes are given (eg cannot find from
        // goal to start, this method will result in an infinite loop!)
        LinkedList<Sommet> chemin = new LinkedList<Sommet>();
        Sommet courant = arrive;
        boolean done = false;
        int i=0; 
        while (!done) {
            chemin.addFirst(courant);
            System.out.println("Dans calc1: "+courant.getCommune().getNom()+"\n");
            courant = courant.getPredecesseur();
            System.out.println("Dans calc2: "+courant.getCommune().getNom()+"\n");
            if (courant.equals(depart)) {
                done = true;
            }
            i++;
        }
        return chemin;
    }

    public static void AfficherAetoile(LinkedList<Sommet> sommet) {
        sommet.forEach((s) -> {
            System.out.println(s.getCommune().getNom());
        });
    }
}

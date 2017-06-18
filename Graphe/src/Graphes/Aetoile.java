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

    public static LinkedList<Sommet> algo(Sommet depart, Sommet arrive, int type) {
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
            //on retire le 1er element
            courant = openList.poll();
            System.out.println(courant.getCommune().getNom() + "\n");
            //on l'ajoute a liste des sommets deja visités
            closedList.add(courant);
            System.out.println("Affichage de la closedList");
            System.out.println(closedList);
            System.out.println("Fin Affichage de la closedList");
            //parcours de la liste de successeurs
            //for (Sommet tmp : courant.getSuccesseur()) {
            for (Sommet tmp : courant.getSuccesseur()) {
                System.out.println("Sommet a tester  : " + tmp.getCommune().getNom());
                if (!closedList.contains(tmp)) {
                    System.out.println("Sommet  : " + tmp.getCommune().getNom());

                    //si ce successeur est pr
                    if (openList.contains(tmp) && tmp.getgCost() > ((type == 0) ? (tmp.calculateGcost(courant)) : (tmp.calculateGcost1(courant)))) {
                        System.out.println("openlist contient deja");
                        openList.remove(tmp);
                        //on calcule son cout vers la source
                        tmp.setPredecesseur(courant);
                        //on set comme predecesseur de chaque successeur le sommet courant
                        System.out.println("Predecesseur : " + tmp.getPredecesseur().getCommune().getNom());
                        tmp.sethCost(arrive, type);
                        //on calcule le cout vers la destination
                        tmp.setgCost(courant, type);
                        openList.add(tmp);
                    } else if (!openList.contains(tmp)) {
                        tmp.setPredecesseur(courant);
                        //on set comme predecesseur de chaque successeur le sommet courant
                        System.out.println("Predecesseur : " + tmp.getPredecesseur().getCommune().getNom());
                        //on calcule son cout vers la source
                        tmp.sethCost(arrive, type);
                        //on calcule le cout vers la destination
                        tmp.setgCost(courant, type);
                        openList.add(tmp);
                    }
                } else {
                    if (openList.contains(tmp) && tmp.getgCost() > ((type == 0) ? (tmp.calculateGcost(courant)) : (tmp.calculateGcost1(courant)))) {
                        openList.remove(tmp);
                        System.out.println("Sommet  : " + tmp.getCommune().getNom());
                        //on set comme predecesseur de chaque successeur le sommet courant
                        tmp.setPredecesseur(courant);
                        System.out.println("Predecesseur : " + tmp.getPredecesseur().getCommune().getNom());
                        //on met a jour les nouveaux couts
                        tmp.sethCost(arrive, type);
                        tmp.setgCost(courant, type);
                        openList.add(tmp);
                    }
                }
            }
            //System.out.println(openList);
            //System.out.println(closedList);
        }
        arrive.setPredecesseur(closedList.getLast());
        return calcPath(depart, arrive);
    }

    private static LinkedList<Sommet> calcPath(Sommet depart, Sommet arrive) {
        // TODO if invalid nodes are given (eg cannot find from
        // goal to start, this method will result in an infinite loop!)
        LinkedList<Sommet> chemin = new LinkedList<>();
        Sommet courant = arrive;
        boolean done = false;
        int i = 0;
        while (!done) {
            chemin.addFirst(courant);
            System.out.println("Dans calc1: " + courant.getCommune().getNom() + "\n");
            courant = courant.getPredecesseur();
            System.out.println("Dans calc2: " + courant.getCommune().getNom() + "\n");
            if (courant.equals(depart)) {
                done = true;
            }
            i++;
        }
        chemin.addFirst(depart);
        return chemin;
    }

    public static void AfficherAetoile(LinkedList<Sommet> sommet) {
        sommet.forEach((s) -> {
            System.out.println(s.getCommune().getNom());
        });
    }
}

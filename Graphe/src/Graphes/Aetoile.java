package Graphes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import static javafx.scene.input.KeyCode.T;

/**
 * Created by Christian TAGUEJOU on 24/05/2017.
 */
public class Aetoile {

    static ArrayList<Sommet> sommetVisiter;

    public static LinkedList<Sommet> algo(ArrayList<Sommet> listeSommet, Sommet arrive, Sommet depart) {
        PriorityQueue<Sommet> openList = new PriorityQueue<Sommet>();
        LinkedList<Sommet> closedList = new LinkedList<Sommet>();
        openList.add(depart);
        Sommet courant;
        while (!openList.isEmpty() && !openList.contains(arrive)) {
            courant = openList.poll();
            closedList.add(courant);
            for (Sommet tmp : listeSommet) {
                if (!closedList.contains(tmp)) {
                    tmp.setPredecesseur(courant);
                    tmp.sethCost(arrive);
                    tmp.setgCost(courant);
                    openList.add(tmp);
                } else {
                    if (tmp.getgCost() > tmp.calculateGcost(courant)) {
                        tmp.setPredecesseur(courant);
                        tmp.setgCost(courant);
                    }
                }
            }
        }
        return calcPath(depart,arrive);
    }

    private static LinkedList<Sommet> calcPath(Sommet depart, Sommet arrive) {
        // TODO if invalid nodes are given (eg cannot find from
        // goal to start, this method will result in an infinite loop!)
        LinkedList<Sommet> chemin = new LinkedList<Sommet>();

        Sommet courant = arrive;
        boolean done = false;
        while (!done) {
            chemin.addFirst(courant);
            courant = (Sommet) courant.getPredecesseur();
            if (courant.equals(depart)) {
                done = true;
            }
        }
        return chemin;
    }

    public static void AfficherAetoile() {
        sommetVisiter.forEach((s) -> {
            System.out.println(s.getCommune().getNom());
        });
    }
}

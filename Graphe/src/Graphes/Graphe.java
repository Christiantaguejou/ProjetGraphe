package Graphes;

import Communes.*;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 23/05/2017.
 */
public class Graphe {

    ArrayList<Commune> listeCommune;
    Commune depart;
    Commune arrive;

    public Graphe(ArrayList<Commune> listCommune, Commune depart, Commune arrive){
        this.listeCommune = listCommune;
        this.depart = depart;
        this.arrive = arrive;
    }

    /**
     * Renvoie la liste de commune entre la commune de depart et celle d'arrivée
     */
    public ArrayList<Commune> gps(){
        ArrayList<Commune> chemin = new ArrayList<>();
        Commune comSuiv;
        Commune comDepart = depart;

        chemin.add(depart);

        for(int i = 0; i < listeCommune.size(); i++) {
            while(comDepart.getNom() != null) {
                comSuiv = nextCommune(comDepart);
                comDepart = comSuiv;

                if(comDepart .getNom()!= null) {
                    chemin.add(comSuiv);
                }
            }
        }
        return chemin;
    }

    /**
     * Permet de déterminer la prochaine commune. La méthode cacule la distance entre la commune actuelle (comActuelle)
     * et toutes les autres communes contenues dans la liste. La prochaine commune sera celle qui sera la plus proche
     * de comActuelle
     * @param comActuelle
     * @return
     */
    private Commune nextCommune(Commune comActuelle){
        double distance = 999999;
        Commune nextCommune = new Commune();

        for(Commune com : listeCommune){
            Arc precedCom = new Arc(comActuelle, com);
            Arc arrivePreced = new Arc(comActuelle, arrive);
            Arc arriveCom = new Arc (com, arrive);

            if((precedCom.distanceVolOiseau() < distance) &&
                    ( arriveCom.distanceVolOiseau() <arrivePreced.distanceVolOiseau())){
                distance = precedCom.distanceVolOiseau();
                    nextCommune = com;
            }
        }
        return nextCommune;
    }
}

package Graphes;

import Communes.*;

import java.lang.Math;

import static java.lang.StrictMath.acos;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

/**
 * Created by chris on 23/05/2017.
 */
public class Arc {

    private Commune c1;
    private Commune c2;

    /**
     * Utilisation de Sommet pour le constructeur d'Arc
     */
    private Sommet s1;
    private Sommet s2;

    private double poids;

    /**
     * Constructeur d Arc avec deux Sommets
     * @param s1 Sommet 1
     * @param s2 Sommet 2
     */
    public Arc(Sommet s1, Sommet s2){
        this.s1 = s1;
        this.s2 = s2;
        s1.addSuccesseur(s2);
        s2.addSuccesseur(s1);
        this.poids = distanceVolOiseau(s1.getCommune(),s2.getCommune());
    }


    public Arc(Commune depart, Commune arrive){
        this.c1 = depart;
        this.c2 = arrive;
    }



    /**
     * Permet de calculer la distance entre deux villes
     * @return
     */
    public double distanceVolOiseau(){
        return distanceVolOiseau(c1,c2);
    }

    /**
     * @param c1
     * @param c2
     * @return
     */
    public double distanceVolOiseau(Commune c1, Commune c2){
        double distance;

        //Conversion des coordonnées en radian
        double lat1 = Math.toRadians(c1.getLatitude());
        double lat2 = Math.toRadians(c2.getLatitude());
        double long1 = Math.toRadians(c1.getLongitude());
        double long2 = Math.toRadians(c2.getLongitude());

        distance = acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(long1 - long2)) * 6371;
        return distance;
    }

    /**
     * Enleve s2 de la liste des successeurs de s1
     * et s1 de la liste des successeurs de s2
     */
    public void aboutToBeRemove()
    {
        this.s1.removeSuccesseur(s2);
        this.s2.removeSuccesseur(s1);
    }

}

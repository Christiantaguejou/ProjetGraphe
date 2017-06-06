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

    public Arc(Commune depart, Commune arrive){
        this.c1 = depart;
        this.c2 = arrive;
    }

    /**
     * Permet de calculer la distance entre deux villes
     * @return
     */
    public double distanceVolOiseau(){
        double distance;

        //Conversion des coordonnées en radian
        double lat1 = Math.toRadians(c1.getLatitude());
        double lat2 = Math.toRadians(c2.getLatitude());
        double long1 = Math.toRadians(c1.getLongitude());
        double long2 = Math.toRadians(c2.getLongitude());

        distance = acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(long1 - long2)) * 6371;
        return distance;
    }

}

package Graphes;

import Communes.Commune;

/**
 * Created by Christian TAGUEJOU on 25/05/2017.
 */
public class Sommet {

    public Commune commune;
    Sommet successeur;
    Sommet predecesseur;
    double cout;

    public Sommet(Commune commune,Sommet successeur, Sommet predecesseur,  double cout){
        this.commune = commune;
        this.predecesseur = predecesseur;
        this.successeur = successeur;
        this.cout = cout;
    }

    public Sommet(Commune commune){
        this.commune = commune;
    }

    public Sommet() {}

    public double coutTotal(Sommet dep, Sommet arr){
       cout = new Arc(dep.commune, commune).distanceVolOiseau() + new Arc(commune, arr.commune).distanceVolOiseau();
       return cout;
    }

    public Sommet successeur(Sommet dep, Sommet arr, Sommet actuelle){
        if ( new Arc(dep.commune, arr.commune).distanceVolOiseau() > new Arc(actuelle.commune, arr.commune).distanceVolOiseau()){
            successeur = actuelle; //La commune actuelle est un successeur de la commune de depart
        }
        return successeur;
    }

    public Sommet predecesseur(Sommet dep, Sommet arr, Sommet preced){
        if ( new Arc(dep.commune, arr.commune).distanceVolOiseau() < new Arc(preced.commune, arr.commune).distanceVolOiseau()){
            predecesseur = preced;
        }
        return predecesseur;
    }
}

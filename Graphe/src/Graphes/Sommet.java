package Graphes;

import Communes.Commune;

/**
 * Created by Christian TAGUEJOU on 25/05/2017.
 */
public class Sommet implements Comparable<Sommet>{

    private Commune commune;
    private Sommet successeur;
    private Sommet predecesseur;
    private double cout=0;
    private double gCost=0;
    private double hCost=0;

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

    public double coutTotal(){
       return this.gCost+this.hCost;
    }

    public Sommet successeur(Sommet dep, Sommet arr, Sommet actuelle){
        if ( new Arc(dep.getCommune(), arr.getCommune()).distanceVolOiseau() > new Arc(actuelle.getCommune(), arr.getCommune()).distanceVolOiseau()){
            setSuccesseur(actuelle); //La commune actuelle est un successeur de la commune de depart
        }
        return getSuccesseur();
    }

    public Sommet predecesseur(Sommet dep, Sommet arr, Sommet preced){
        if ( new Arc(dep.getCommune(), arr.getCommune()).distanceVolOiseau() < new Arc(preced.getCommune(), arr.getCommune()).distanceVolOiseau()){
            setPredecesseur(preced);
        }
        return getPredecesseur();
    }

    /**
     * @return the commune
     */
    public Commune getCommune() {
        return commune;
    }

    /**
     * @param commune the commune to set
     */
    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    /**
     * @return the successeur
     */
    public Sommet getSuccesseur() {
        return successeur;
    }

    /**
     * @param successeur the successeur to set
     */
    public void setSuccesseur(Sommet successeur) {
        this.successeur = successeur;
    }

    /**
     * @return the predecesseur
     */
    public Sommet getPredecesseur() {
        return predecesseur;
    }

    /**
     * @param predecesseur the predecesseur to set
     */
    public void setPredecesseur(Sommet predecesseur) {
        this.predecesseur = predecesseur;
    }

    /**
     * @return the cout
     */
    public double getCout() {
        return cout;
    }

    /**
     * @param cout the cout to set
     */
    public void setCout(double cout) {
        this.cout = cout;
    }

    /**
     * @return the gCost
     */
    public double getgCost() {
        return gCost;
    }

    /**
     * @param gCost the gCost to set
     */
    public void setgCost(double gCost) {
        this.gCost = gCost;
    }
    
    public void setgCost(Sommet s) { 
        this.gCost = s.getgCost() + calculateGcost(s);
    }

    /**
     * @return the hCost
     */
    public double gethCost() {
        return hCost;
    }

    /**
     * @param hCost the hCost to set
     */
    public void sethCost(double hCost) {
        this.hCost = hCost;
    }
    public void sethCost(Sommet s) {
        this.hCost = calculateHcost(s);
    }
    
    public double calculateHcost(Sommet s){
     return new Arc(this.commune,s.commune).distanceVolOiseau();
    }
     
    public double calculateGcost(Sommet s){
    return new Arc(this.commune,s.commune).distanceVolOiseau();
    }

    @Override
    public int compareTo(Sommet t) {
        Double tmp = this.coutTotal();
        return tmp.compareTo(t.coutTotal());
    }
}

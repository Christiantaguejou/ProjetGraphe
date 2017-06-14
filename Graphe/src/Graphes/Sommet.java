package Graphes;

import Communes.Commune;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 25/05/2017.
 */
public class Sommet implements Comparable<Sommet>{

    private Commune commune;
    private ArrayList<Sommet> successeurs;
    private double cout=0;
    private double gCost=0;
    private double hCost=0;

    public Sommet() {
        this.successeurs = new ArrayList<Sommet>();
    }

    public Sommet(Commune commune){
        this();
        this.commune = commune;
    }

    public Sommet(Commune commune,Sommet successeur,  double cout){
        this();
        this.commune = commune;
        this.successeurs.add(successeur);
        this.cout = cout;
    }

    @Override
    public boolean equals(Object s){
       boolean retVal = false;

        if (s instanceof Sommet){
            Sommet ptr = (Sommet) s;
            /*System.out.println("test degalite");
            System.out.println(this.commune.getId());
            System.out.println(ptr.commune.getId());
            System.out.println("fin test degalite");*/
            if(this.commune.getId().equals(ptr.commune.getId())) retVal = true;
           // System.out.println(retVal);
        }
     return retVal;
   
    }
    /**
     *
     */
    @Override
    public String toString(){
        return this.commune.getNom();   
    }




    public double coutTotal(){
       return this.gCost+this.hCost;
    }

//    public Sommet successeur(Sommet dep, Sommet arr, Sommet actuelle){
//        if ( new Arc(dep.getCommune(), arr.getCommune()).distanceVolOiseau() > new Arc(actuelle.getCommune(), arr.getCommune()).distanceVolOiseau()){
//            setSuccesseur(actuelle); //La commune actuelle est un successeur de la commune de depart
//        }
//        return getSuccesseur();
//    }
//
//    public Sommet predecesseur(Sommet dep, Sommet arr, Sommet preced){
//        if ( new Arc(dep.getCommune(), arr.getCommune()).distanceVolOiseau() < new Arc(preced.getCommune(), arr.getCommune()).distanceVolOiseau()){
//            setPredecesseur(preced);
//        }
//        return getPredecesseur();
//    }

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
    public ArrayList<Sommet> getSuccesseur() {
        return successeurs;
    }

    /**
     * add a successeur into the list
     * @param successeur the successeur to add
     */
    public void addSuccesseur(Sommet successeur) {
        this.successeurs.add(successeur);
    }

    /**
     * erase the corresponding successeur
     * @param successeur
     */
    public void removeSuccesseur(Sommet successeur){
        this.successeurs.remove(successeur);
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

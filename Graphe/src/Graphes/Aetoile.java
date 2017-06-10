package Graphes;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 24/05/2017.
 */
public class Aetoile {

    static ArrayList<Sommet> sommetVisiter;

    public static void algo(ArrayList<Sommet> listeSommet, Sommet arrive, Sommet depart){
        ArrayList<Sommet> sommetAExplorer = new ArrayList<>();
        sommetAExplorer.add(depart);
        sommetVisiter = new ArrayList<>();
        Sommet comSuivante = new Sommet();
        double coutTotal = 99999;

        while((!sommetAExplorer.isEmpty()) && (!sommetAExplorer.contains(arrive))){

            //Recupere la commune de cout minimal et l'ajoute à la liste des communes visitée
            for(Sommet c : listeSommet){
                if(c.successeur(depart, arrive, c) != null) {
                    if ((c.coutTotal(depart, arrive) < coutTotal)) {
                        coutTotal = c.coutTotal(depart, arrive);
                        sommetVisiter.add(c);
                        comSuivante = c;
                        System.out.println(c.commune.getNom());
                        System.out.println(coutTotal);
                    }
                }
            }

            //Ajout des successeurs à la liste de communes à visiter
            for(Sommet c : listeSommet){
                if(!sommetVisiter.contains(c) && (c.successeur(depart, arrive, c) != null)){
                    sommetAExplorer.add(c);
                    c.cout = c.coutTotal(depart, arrive);

                    //On identifie le predecesseur de la commune qu'on vient d'ajouter
                    listeSommet.forEach((som) -> {
                        som.predecesseur = som.predecesseur(depart, arrive, som);
                    });
                }
                if(sommetAExplorer.contains(c) && (c.coutTotal(comSuivante,arrive) < coutTotal)){
                    coutTotal = c.coutTotal(comSuivante,arrive);
                    c.cout = c.coutTotal(comSuivante, arrive);
                    c.predecesseur = comSuivante;
                }
            }
        }
    }

    public static void AfficherAetoile(){
        sommetVisiter.forEach((s) -> {
            System.out.println(s.commune.getNom());
        });
    }
}

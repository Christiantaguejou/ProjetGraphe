package Graphes;

import Communes.*;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 23/05/2017.
 */
public class Graphe {

    ArrayList<Commune> listeCommune;

    public Graphe(ArrayList<Commune> listCommune){
        this.listeCommune = listCommune;
    }

    /**
     * Permet de creer une liste avec des communes contenant une population > popMin
     * @param listeCommunes
     * @param popMin
     * @return
     */
    public static ArrayList<Commune> triPopMin (ArrayList<Commune> listeCommunes, int popMin){

        ArrayList<Commune> listeTrie = new ArrayList<>();

        for(int i = 0; i < listeCommunes.size(); i++) {
            if (listeCommunes.get(i).getPopulation() > popMin) {
                listeTrie.add(listeCommunes.get(i));
            }
        }
        return listeTrie;
    }

    /**
     * Permet de creer une liste avec des communant ayant une population < popMax
     * @param listeCommunes
     * @param popMax
     * @return
     */
    public static ArrayList<Commune> triPopMax (ArrayList<Commune> listeCommunes, int popMax){

        ArrayList<Commune> listeTrie = new ArrayList<>();

        for(int i = 0; i < listeCommunes.size(); i++) {
            if (listeCommunes.get(i).getPopulation() > popMax) {
                listeTrie.add(listeCommunes.get(i));
            }
        }
        return listeTrie;
    }

    /**
     * permet de creer un graphe avec des arcs de longueur inferieur a la
     * distance a vol doiseau
     *
     * @param listeCommunes
     * @param distanceMax
     * @return une arraylist de communes
     */
    public static ArrayList<Commune> triVolDoiseauMax(ArrayList<Commune> listeCommunes, int distanceMax) {
        ArrayList<Commune> listeTrie = new ArrayList<>();
        return listeTrie;
    }
    /**
     * permet de creer un graphe avec des arcs de longueur superieur a la
     * distance a vol doiseau
     * @param listeCommunes
     * @param distanceMin
     * @return
     */
    public static ArrayList<Commune> triVolDoiseauMin(ArrayList<Commune> listeCommunes, int distanceMin) {
        ArrayList<Commune> listeTrie = new ArrayList<>();
        return listeTrie;
    }
}

package Graphes;

import Communes.*;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 23/05/2017.
 */
public class Graphe {

    ArrayList<Commune> listeCommune;
    ArrayList<Arc> arcs;

    public enum choixTri{
        MAX,
        MIN
    }

    public enum triPar{
        POPULATION,
        DISTANCE
    }


    /**
     * Constructeur de Graphr sans arguement
     * Initialise l'arrayList Arc
     */
    public Graphe(){
        this.arcs = new ArrayList<>();
    }

    /**
     * Constructeurs de Graph
     * @param listeCommune liste des communes
     */
    public Graphe(ArrayList<Commune> listeCommune, triPar triPar, choixTri choixTri, int valeurTri)
    {
        this();
        ArrayList<Commune> trier;
        switch (triPar){
            case POPULATION:
                switch (choixTri){
                    case MAX:
                        trier = triPopMax(listeCommune, valeurTri);
                        break;
                    case MIN:
                         trier= triPopMin(listeCommune,valeurTri);
                        break;
                    default:
                        trier = new ArrayList<Commune>();
                        break;
                }
                break;
            case DISTANCE:
                trier = new ArrayList<Commune>();
                break;
            default:
                trier = new ArrayList<Commune>();
                break;
        }
        for(int i = 0; i<trier.size(); i++){
            for(int j = 0; j<trier.size(); j++){
                if(i==j)
                    continue;
                else {
                    Arc arc = new Arc(new Sommet(trier.get(i)),new Sommet(trier.get(j)));
                    this.arcs.add(arc);
                }
            }
        }

    }

    /**
     * Enleve l'arc du graph
     * @param arc
     */
    public void removeArc(Arc arc)
    {
        int indice = arcs.indexOf(arc);
        if(indice!=-1)
            arcs.get(indice).aboutToBeRemove();
        arcs.remove(arc);
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
     * permet de creer un graphe avec des distances entre sommet de longueur inferieur a la
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
     * permet de creer un graphe avec des distances entre sommet de longueur superieur a la
     * distance a vol doiseau
     * @param listeCommunes
     * @param distanceMin
     * @return
     */
    public static ArrayList<Commune> triVolDoiseauMin(ArrayList<Commune> listeCommunes, int distanceMin) {
        ArrayList<Commune> listeTrie = new ArrayList<>();
        return listeTrie;
    }

    /**
     * @return arcs liste des arêtes
     */
    public ArrayList<Arc> getArcs() {
        return arcs;
    }
}

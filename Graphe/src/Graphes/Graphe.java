package Graphes;

import Communes.*;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 23/05/2017.
 */
public class Graphe {

    ArrayList<Commune> listeCommune;
    ArrayList<Arc> arcs;

    public enum choixTri {
        MAX,
        MIN
    }

    public enum triPar {
        POPULATION,
        DISTANCE
    }


    /**
     * Constructeur de Graphr sans arguement
     * Initialise l'arrayList Arc
     */
    public Graphe() {
        this.arcs = new ArrayList<>();
    }

    /**
     * Constructeur de graph
     * On trie directement les communes et pour chaque commune on crée des arcs vers toutes les autres commmunes
     *
     * @param listeCommune liste des communes
     * @param triPar       choix du tri(Par population ou Distance)
     * @param choixTri     si on trie par Max ou Min
     * @param valeurTri    valeur pour le trie des communes
     */
    public Graphe(ArrayList<Commune> listeCommune, triPar triPar, choixTri choixTri, int valeurTri) {
        this();
        ArrayList<Arc> trier;
        switch (triPar) {
            case POPULATION:
                switch (choixTri) {
                    case MAX:
                        trier = triPopMax(listeCommune, valeurTri);
                        break;
                    case MIN:
                        trier = triPopMin(listeCommune, valeurTri);
                        break;
                    default:
                        trier = new ArrayList<Arc>();
                        break;
                }
                break;
            case DISTANCE:
                switch (choixTri) {
                    case MAX:
                        trier = triVolDoiseauMax(listeCommune, valeurTri);
                        break;
                    case MIN:
                        trier = triVolDoiseauMin(listeCommune, valeurTri);
                        break;
                    default:
                        trier = new ArrayList<>();
                        break;
                }
                break;
            default:
                trier = new ArrayList<>();
                break;
        }
        this.arcs = trier;

    }

    /**
     * Enleve l'arc du graph
     *
     * @param arc
     */
    public void removeArc(Arc arc) {
        int indice = arcs.indexOf(arc);
        if (indice != -1)
            arcs.get(indice).aboutToBeRemove();
        arcs.remove(arc);
    }


    /**
     * Permet de creer une liste avec des communes contenant une population > popMin
     *
     * @param listeCommunes
     * @param popMin
     * @return
     */
    public static ArrayList<Arc> triPopMin(ArrayList<Commune> listeCommunes, int popMin) {
        ArrayList<Arc> listeTrie = new ArrayList<>();
        ArrayList<Arc> alReadyVisited = new ArrayList<>();
        for (int i = 0; i < listeCommunes.size(); i++) {
            for (int j = 0; j < listeCommunes.size(); j++) {
                if (i != j && listeCommunes.get(i).getPopulation() > popMin) {
                    Arc arc = new Arc(new Sommet(listeCommunes.get(i)), new Sommet(listeCommunes.get(j)));
                    ////////LE GRAPH EST NON ORIENTE ON DOIT DONC VERIFIER QU IL N Y AIT PAS DEUX FOIS LE MEME ARC
                    if (alReadyVisited.indexOf(arc) == -1) {
                        Arc tmpt = new Arc(new Sommet(listeCommunes.get(j)), new Sommet(listeCommunes.get(i)));
                        alReadyVisited.add(arc);
                        alReadyVisited.add(tmpt);
                        listeTrie.add(arc);
                    } else
                        continue;
                } else
                    continue;
            }
        }
        return listeTrie;
    }

    /**
     * Permet de creer une liste avec des communant ayant une population < popMax
     *
     * @param listeCommunes
     * @param popMax
     * @return
     */
    public static ArrayList<Arc> triPopMax(ArrayList<Commune> listeCommunes, int popMax) {

        ArrayList<Arc> listeTrie = new ArrayList<>();
        ArrayList<Arc> alReadyVisited = new ArrayList<>();
        for (int i = 0; i < listeCommunes.size(); i++) {
            for (int j = 0; j < listeCommunes.size(); j++) {
                if (i != j && listeCommunes.get(i).getPopulation() < popMax) {
                    Arc arc = new Arc(new Sommet(listeCommunes.get(i)), new Sommet(listeCommunes.get(j)));
                    ////////LE GRAPH EST NON ORIENTE ON DOIT DONC VERIFIER QU IL N Y AIT PAS DEUX FOIS LE MEME ARC
                    if (alReadyVisited.indexOf(arc) == -1) {
                        Arc tmpt = new Arc(new Sommet(listeCommunes.get(j)), new Sommet(listeCommunes.get(i)));
                        alReadyVisited.add(arc);
                        alReadyVisited.add(tmpt);
                        listeTrie.add(arc);
                    } else
                        continue;
                } else
                    continue;
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
    public static ArrayList<Arc> triVolDoiseauMax(ArrayList<Commune> listeCommunes, int distanceMax) {
        ArrayList<Arc> listeTrie = new ArrayList<>();
        ArrayList<Arc> alReadyVisited = new ArrayList<>();
        for (int i = 0; i < listeCommunes.size(); i++) {
            for (int j = 0; j < listeCommunes.size(); j++) {
                if (i != j && distanceMax > Arc.distanceVolOiseau(listeCommunes.get(i), listeCommunes.get(j))) {
                    Arc arc = new Arc(new Sommet(listeCommunes.get(i)), new Sommet(listeCommunes.get(j)));
                    ////////LE GRAPH EST NON ORIENTE ON DOIT DONC VERIFIER QU IL N Y AIT PAS DEUX FOIS LE MEME ARC
                    if (alReadyVisited.indexOf(arc) == -1) {
                        Arc tmpt = new Arc(new Sommet(listeCommunes.get(j)), new Sommet(listeCommunes.get(i)));
                        alReadyVisited.add(arc);
                        alReadyVisited.add(tmpt);
                        listeTrie.add(arc);
                    } else
                        continue;
                } else
                    continue;
            }
        }
        return listeTrie;
    }

    /**
     * permet de creer un graphe avec des arcs de longueur superieur a la
     * distance a vol doiseau
     *
     * @param listeCommunes
     * @param distanceMin
     * @return
     */
    public static ArrayList<Arc> triVolDoiseauMin(ArrayList<Commune> listeCommunes, int distanceMin) {
        ArrayList<Arc> listeTrie = new ArrayList<>();
        ArrayList<Arc> alReadyVisited = new ArrayList<>();
        for (int i = 0; i < listeCommunes.size(); i++) {
            for (int j = 0; j < listeCommunes.size(); j++) {
                if (i != j && distanceMin < Arc.distanceVolOiseau(listeCommunes.get(i), listeCommunes.get(j))) {
                    Arc arc = new Arc(new Sommet(listeCommunes.get(i)), new Sommet(listeCommunes.get(j)));
                    ////////LE GRAPH EST NON ORIENTE ON DOIT DONC VERIFIER QU IL N Y AIT PAS DEUX FOIS LE MEME ARC
                    if (alReadyVisited.indexOf(arc) == -1) {
                        Arc tmpt = new Arc(new Sommet(listeCommunes.get(j)), new Sommet(listeCommunes.get(i)));
                        alReadyVisited.add(arc);
                        alReadyVisited.add(tmpt);
                        listeTrie.add(arc);
                    } else
                        continue;
                } else
                    continue;
            }
        }
        return listeTrie;
    }

    /**
     * @return arcs liste des arêtes
     */
    public ArrayList<Arc> getArcs() {
        return arcs;
    }
}

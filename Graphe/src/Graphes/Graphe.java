package Graphes;

import Communes.*;

import java.util.ArrayList;

/**
 * Created by Christian TAGUEJOU on 23/05/2017.
 */
public class Graphe {

    ArrayList<Commune> listeCommune;
    ArrayList<Arc> arcs;
    ArrayList<Sommet> sommets;
    static int DIST_MAX_ARC = 100;

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
        this.sommets = new ArrayList<>();
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
        switch (triPar) {
            case POPULATION:
                switch (choixTri) {
                    case MAX:
                        this.listeCommune = triPopMax(listeCommune, valeurTri);
                        break;
                    case MIN:
                        this.listeCommune= triPopMin(listeCommune, valeurTri);
                        break;
                    default:

                        this.listeCommune = new ArrayList<>();

                        break;
                }
                break;
            case DISTANCE:
                switch (choixTri) {
                    case MAX:
                        this.listeCommune = triVolDoiseauMax(listeCommune, valeurTri);
                        break;
                    case MIN:
                        this.listeCommune = triVolDoiseauMin(listeCommune, valeurTri);
                        break;
                    default:
                        this.listeCommune = new ArrayList<>();
                        break;
                }
                break;
            default:
                this.listeCommune = new ArrayList<>();
                break;
        }
        //////ARRAY LIST POUR NE PAS AJOUTER DEUX FOIS LE MEME SOMMET
        ArrayList<Sommet> sAlreadyAdd = new ArrayList<>();

        /////ARRAY LIST POUR NE PAS AJOUTER DEUX FOIS LE MEME ARC
        ArrayList<Arc> aAlreadyAdd = new ArrayList<>();


        for(int i =0; i<this.listeCommune.size();i++){
            for(int j = 0; j< this.listeCommune.size();j++){
                if(i!=j){
                    if(Arc.distanceVolOiseau(this.listeCommune.get(i),listeCommune.get(j))<DIST_MAX_ARC){
                        Sommet tmp_s1 = new Sommet(this.listeCommune.get(i));
                        Sommet tmp_s2 =new Sommet(this.listeCommune.get(j));
                        Arc tmp_arc = new Arc(tmp_s1,tmp_s2);
                        //System.out.println(tmp_s1);
                        //System.out.println(tmp_s2);
                        if(!aAlreadyAdd.contains(tmp_arc)){
                            this.arcs.add(tmp_arc);
                            ////COMME LE GRAPH EST NON ORIENTER ON AJOUTE DIRECTEMENT L ARC A->B et B->A AFIN DE NE PAS
                            ////AVOIR DE DOUBLONS
                            aAlreadyAdd.add(new Arc(tmp_s2,tmp_s1));
                            aAlreadyAdd.add(tmp_arc);
                        }

                        if(!sAlreadyAdd.contains(tmp_s1)){
                            this.sommets.add(tmp_s1);
                            sAlreadyAdd.add(tmp_s1);
                        }

                        if (!sAlreadyAdd.contains(tmp_s2)){
                            this.sommets.add(tmp_s2);
                            sAlreadyAdd.add(tmp_s2);
                        }

                    }
                }
            }
        }
        System.out.println("fin parcours");
        //ON AJOUTE LES SOMMETS A LA LISTE DES SOMMETS
//        for (int i = 0; i < arcs.size(); i++) {
//            Sommet[] sommets = arcs.get(i).getSommet();
//            if (this.sommets.indexOf(sommets[0]) != -1)
//                this.sommets.add(sommets[0]);
//            if (this.sommets.indexOf(sommets[1]) != -1)
//                this.sommets.add(sommets[1]);
//        }
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
    public static ArrayList<Commune> triPopMin(ArrayList<Commune> listeCommunes, int popMin) {
        ArrayList<Commune> listeTrie = new ArrayList<>();
        for (int i = 0; i < listeCommunes.size(); i++) {
            if (listeCommunes.get(i).getPopulation() > popMin) {
                listeTrie.add(listeCommunes.get(i));
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
    public static ArrayList<Commune> triPopMax(ArrayList<Commune> listeCommunes, int popMax) {

        ArrayList<Commune> listeTrie = new ArrayList<>();
        for (int i = 0; i < listeCommunes.size(); i++) {
            if (listeCommunes.get(i).getPopulation() < popMax)
                listeTrie.add(listeCommunes.get(i));
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
        for (int i = 0; i < listeCommunes.size(); i++) {
            for (int j = 0; j < listeCommunes.size(); j++) {
                if (distanceMax > Arc.distanceVolOiseau(listeCommunes.get(i), listeCommunes.get(j))) {
                    if (!listeTrie.contains(listeCommunes.get(i))) {
                        listeTrie.add(listeCommunes.get(i));
                    }
                    if (!listeTrie.contains(listeCommunes.get(j))) {
                        listeTrie.add(listeCommunes.get(j));
                    }
                }
            }
        }
        return listeTrie;
    }

    /**
     * permet de creer un graphe avec des distances entre sommet de longueur superieur a la
     * distance a vol doiseau
     *
     * @param listeCommunes
     * @param distanceMin
     * @return
     */
    public static ArrayList<Commune> triVolDoiseauMin(ArrayList<Commune> listeCommunes, int distanceMin) {
        ArrayList<Commune> listeTrie = new ArrayList<>();
        for (int i = 0; i < listeCommunes.size(); i++) {
            for (int j = 0; j < listeCommunes.size(); j++) {
                if (distanceMin < Arc.distanceVolOiseau(listeCommunes.get(i), listeCommunes.get(j))) {
                    if (!listeTrie.contains(listeCommunes.get(i))) {
                        listeTrie.add(listeCommunes.get(i));
                    }
                    if (!listeTrie.contains(listeCommunes.get(j))) {
                        listeTrie.add(listeCommunes.get(j));
                    }
                }
            }
        }
        return listeTrie;
    }

    /**
     * Getteur d'arêtes
     *
     * @return arcs liste des arêtes
     */
    public ArrayList<Arc> getArcs() {
        return arcs;
    }

    /**
     * Getteur de sommets
     *
     * @return sommets liste de sommets
     */
    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public Arc getArcBySommets(Sommet s1, Sommet s2) {
        Arc arc = null;
        Arc tmp1 = new Arc(s1, s2);
        Arc tmp2 = new Arc(s2, s1);
        if (this.arcs.indexOf(tmp1) != -1)
            arc = tmp1;
        else if (this.arcs.indexOf(tmp2) != -1)
            arc = tmp2;
        return arc;
    }

    public ArrayList<Commune> getListeCommune() {
        return listeCommune;
    }
}

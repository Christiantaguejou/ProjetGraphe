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
    ArrayList<Sommet> listeSuccesseurs = new ArrayList<>();
    ArrayList<Sommet> listeSommet = new ArrayList<>();
    Sommet somDepart;
    static int DIST_MAX_ARC = 160;

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
    public Graphe(ArrayList<Commune> listeCommune, triPar triPar, choixTri choixTri, int valeurTri, Sommet somDepart) {
        this();
        this.somDepart = somDepart;
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

        this.sommets = transformToSommet(this.listeCommune);

//        for(int i =0; i<this.listeCommune.size();i++){
//            for(int j = 0; j< this.listeCommune.size();j++){
//                if(i!=j){
//                    if(Arc.distanceVolOiseau(this.listeCommune.get(i),listeCommune.get(j))<DIST_MAX_ARC){
//                        Sommet tmp_s1 = new Sommet(this.listeCommune.get(i));
//                        Sommet tmp_s2 =new Sommet(this.listeCommune.get(j));
//                        Arc tmp_arc = new Arc(tmp_s1,tmp_s2);
//                        //System.out.println(tmp_s1);
//                        //System.out.println(tmp_s2);
//
//
//                        if(!sAlreadyAdd.contains(tmp_s1)){
//                            this.sommets.add(tmp_s1);
//                            sAlreadyAdd.add(tmp_s1);
//                        }
//
//                        if (!sAlreadyAdd.contains(tmp_s2)){
//                            this.sommets.add(tmp_s2);
//                            sAlreadyAdd.add(tmp_s2);
//                        }
//
//                        if(!aAlreadyAdd.contains(tmp_arc)){
//                            this.arcs.add(tmp_arc);
//                            ////COMME LE GRAPH EST NON ORIENTER ON AJOUTE DIRECTEMENT L ARC A->B et B->A AFIN DE NE PAS
//                            ////AVOIR DE DOUBLONS
//                            aAlreadyAdd.add(new Arc(tmp_s2,tmp_s1));
//                            aAlreadyAdd.add(tmp_arc);
//                        }
//
//                    }
//                }
//            }
//        }
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


    public ArrayList<Sommet> transformToSommet(ArrayList<Commune> listeCommune){
        ArrayList<Sommet> listeSommets = new ArrayList<>();
        for(Commune commune : listeCommune){
            listeSommets.add(new Sommet(commune));
        }
        return listeSommets;
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

    // Ceci permet d'affiche les successeur de la ville de départ
    public void firtSuccesseur(){
        listeSommet = transformToSommet(this.listeCommune);
        //liste des communes qu'on va supprimer de listeSommet parce qu'il sont deja dans la liste
        //de successeur du sommet de depart
        ArrayList<Sommet> listaSuppr = new ArrayList<>();

        //Pour chaque sommet s, on verifie que la distance soit < distMAX
        //si c'est bon, on l'ajoute dans les listes des successeurs
        for(Sommet s : listeSommet){
            if(Arc.distanceVolOiseau(s.getCommune(), somDepart.getCommune()) <= DIST_MAX_ARC){
                somDepart.addSuccesseur(s); //Liste personnel du sommet de depart
                listeSuccesseurs.add(s); //On travaillera avec cette liste par la suite pour avoir tous les sommet
                                        //possible
                System.out.println(s.getCommune().getNom());
            }
        }
        //On supprime les sommet qui sont deja dans listeSuccesseur pour diminuer le nombre d'itération
        for(int i = 0; i < listaSuppr.size(); i++){
            listeSommet.remove(i);
        }

        do{
            web();
        }while(listeSuccesseurs.isEmpty());//Sachant qu'a chaque itération on supprime les communes inutiles,
                        //On effectura cette boucle jusqu'a ce qu'il n'ya plus de commune (ou sommet) à tester

      /*  for(Sommet s : listeSuccesseurs){
            System.out.println(s.getCommune().getNom());
        }*/
    }

    public void web (){
        /**
         * Pour chaque sommet su GRAPHE A, on verifie si la distance entre A et les successeur <dISTMAX
         */
        for(Sommet s : listeSommet){
            System.out.println("sommet: "+s.getCommune().getNom() );
            for(Sommet som : listeSuccesseurs){
                System.out.println("sommet2: "+som.getCommune().getNom() );
                //Si la distance est bonne....
                if(Arc.distanceVolOiseau(som.getCommune(), s.getCommune()) <= DIST_MAX_ARC){
                    //S'il n'est pas contenu dans listeSuccesseurs, on l'ajoute, puis on le supprime de listeSommet
                    if(!listeSuccesseurs.contains(s)) {
                        som.addSuccesseur(s);
                        listeSuccesseurs.add(s);
                        listeSommet.remove(listeSommet.indexOf(s));
                        System.out.println(s.getCommune().getNom());
                    }
                    //S'il est contenu dans listeSuccesseur, on l'ajoute dans la liste perso des successeur de som
                    else
                        som.addSuccesseur(s);
                }
            }
        }
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

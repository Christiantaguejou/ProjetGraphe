import Communes.Commune;
import Communes.CsvCommunes;
import Graphes.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static Graphes.Dijkstra._Dijkstra;

/**
 * Created by Fabien on 19/05/2017.
 */
public class Main {

    public static void main(String[] args) {

        //Ouverture de la liste des communes en ArrayList
        List<String> liste;
        liste = CsvCommunes.readFile(new File("../doc/CommunesFrance.csv"));
        ArrayList<Commune> listeCommunes = CsvCommunes.tableau(liste);

        Graphe graphe = new Graphe(listeCommunes, Graphe.triPar.POPULATION, Graphe.choixTri.MIN, 50000);
        System.out.println(graphe.getArcs().size());
        _Dijkstra(graphe);
//        for(Commune c : graphe.getListeCommune()){
//            System.out.println(c.getNom());
//        }
//        System.out.println("FIN dAFFICHAGE");
//        //Trie de la liste
//        //ArrayList<Commune> listeTrie;
//        //listeTrie = CsvCommunes.triPopMin(listeCommunes, 10000);
//
//
//        //Graphes
//        Commune rennes = new Commune("rennes","RENNES",207178,-1.68333,48.0833);
//        Commune brest = new Commune("brest", "BREST", 141303,-4.48333,48.4);
//        Commune ozan = new Commune("ozan", "OZAN",618,4.91667,49.3833);
//        Commune nice = new Commune("nice","NICE",343304,7.25,43.7);
//
//        //   Graphe graph1 = new Graphe(listeTrie, rennes, brest);
//       /* for(Commune c : graph1.gps()){
//         //   System.out.println(c.getNom());
//        }*/
//
//        //Aetoile
//        ArrayList<Sommet> listeSommet= new ArrayList<>();
//        for(Commune c : graphe){
//            listeSommet.add(new Sommet(c));
//        }
//        Sommet sRennes = new Sommet(rennes);
//        Sommet sBrest = new Sommet(brest);
//        Sommet snice = new Sommet(nice);
//       /* System.out.println(sOzan.coutTotal(sRennes, sBrest));
//        System.out.println(new Arc(sRennes.commune, sOzan.commune).distanceVolOiseau());
//        System.out.println(new Arc(sBrest.commune, sOzan.commune).distanceVolOiseau());
//        System.out.println(new Arc(sRennes.commune, sOzan.commune).distanceVolOiseau() + new Arc(sBrest.commune, sOzan.commune).distanceVolOiseau());*/
//         LinkedList<Sommet> s = Aetoile.algo(listeSommet, snice, sRennes);
//         Aetoile.AfficherAetoile(s);
    }
}

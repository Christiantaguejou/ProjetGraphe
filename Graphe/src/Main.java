import Communes.Commune;
import Communes.CsvCommunes;
import Graphes.*;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.SyncFailedException;
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
        Graphe graphe = null;
        try {
            graphe = new Graphe(listeCommunes, Graphe.triPar.POPULATION, Graphe.choixTri.MIN, 50000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Sommet depart = graphe.getSommets().get(0);
        Sommet arrive = graphe.getSommets().get(1);
        //ArrayList<Sommet> dij = Dijkstra._Dijkstra(graphe,depart,arrive);
        //ArrayList<Sommet> oklm = Dijkstra.dijkstra_plus(graphe,depart,arrive);

        testTemps(graphe);
    }


    public static void testTemps(Graphe graphe){

        System.out.println("DEBUT TEST DE TEMPS DE CALCUL");

        Sommet depart = graphe.getSommets().get(0);
        Sommet arrive = graphe.getSommets().get(1);

        //TEST DE DIJKSTRA NORMAL
        long debut = System.currentTimeMillis();
        //ArrayList<Sommet> dij = Dijkstra._Dijkstra(graphe,depart,arrive);
        long fin = System.currentTimeMillis() - debut;
        /*System.out.println("Le temps de Dijkstra normal est de "+fin+" ms");

        Sommet depart1 = graphe.getSommets().get(0);
        Sommet arrive1 = graphe.getSommets().get(1);
        //TEST DE DIJKSTRA AVEC PRIORITY QUEUE
        long debut_1 = System.currentTimeMillis();
        ArrayList<Sommet> oklm = Dijkstra.dijkstra_plus(graphe,depart,arrive);
        long fin_1 = System.currentTimeMillis() - debut_1;
        System.out.println("Le temps de Dijkstra avec PriorityQueue est de "+fin_1+" ms");

        //TEST DE AETOILE
        long debut_2 = System.currentTimeMillis();
        LinkedList<Sommet> s = Aetoile.algo(depart, arrive,0);
        long fin_2 = System.currentTimeMillis() - debut_2;
        System.out.println("Le temps de A ETOILE est de "+fin_2+" ms");

        System.out.println("FIN TEST DE TEMPS DE CALCUL");
    }
}

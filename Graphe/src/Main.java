import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabien on 19/05/2017.
 */
public class Main {

    public static void main(String[] args) {

        //Ouverture de la liste des communes en ArrayList
        List<String> liste;
        liste = CsvCommunes.readFile(new File("../doc/CommunesFrance.csv"));
        ArrayList<Commune> listeCommunes;
        listeCommunes = CsvCommunes.tableau(liste);

        //Trie de la liste
        ArrayList<Commune> listeTrie;
        listeTrie = CsvCommunes.triPopMin(listeCommunes, 10000);

        //Graphe
        Commune rennes = new Commune("rennes","RENNES",207178,-1.68333,48.0833);
        Commune brest = new Commune("brest", "BREST", 141303,-4.48333,48.4);
        Graphe graph1 = new Graphe(listeTrie, rennes, brest);
        graph1.gps();

        for(Commune c : graph1.gps()){
            System.out.println(c.getNom());
        }

        //listeCommunes.get(0).afficheCommune();
        //listeCommunes.get(1326).afficheCommune();

     //   Arc a = new Arc(listeCommunes.get(0), listeCommunes.get(1326));
     //   a.distanceVolOiseau();
        //System.out.println(listeCommunes.get(456).getLatitude());

        //Créer une classe arret avec un point de départ et d'arrivée
        //Filtrer les villes pour améliorer la rapiditer du prgm
        //Filtrer les arretes par rapport à leur distance
    }
}

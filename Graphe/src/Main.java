import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabien on 19/05/2017.
 */
public class Main {

    public static void main(String[] args) {

        List<String> liste;
        liste = CsvCommunes.readFile(new File("../doc/CommunesFrance.csv"));
        ArrayList<Commune> listeCommunes;
        listeCommunes = CsvCommunes.tableau(liste);

        ArrayList<Commune> listeTrie;
        listeTrie = CsvCommunes.triPopMin(listeCommunes, 20000);

        Commune metz = new Commune("metz","METZ",120738,6.16667,49.1333);
        Commune strasbourg = new Commune("strasbourg", "STRASBOURG", 271782,7.75,48.5833);
        Graphe test1 = new Graphe(listeTrie, metz, strasbourg);
        test1.gps();

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

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
        listeCommunes.get(0).afficheCommune();
        listeCommunes.get(1).afficheCommune();

        Arc a = new Arc(listeCommunes.get(1234), listeCommunes.get(35876));
        a.distanceVolOiseau();
        //System.out.println(listeCommunes.get(456).getLatitude());

        //Créer une classe arret avec un point de départ et d'arrivée
        //Filtrer les villes pour améliorer la rapiditer du prgm
        //Filtrer les arretes par rapport à leur distance
    }
}

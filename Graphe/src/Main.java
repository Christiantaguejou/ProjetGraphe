import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabien on 19/05/2017.
 */
public class Main {

    public static void main(String[] args) {

    //Commune.readFile(new File("CommunesFrance.csv"));
    //Commune.tableau(Commune.readFile(new File("CommunesFrance.csv")));
        List<String> liste;
        liste = CsvCommunes.readFile(new File("CommunesFrance.csv"));
        ArrayList<Commune> listeCommunes;
        listeCommunes = CsvCommunes.tableau(liste);
        listeCommunes.get(456).afficheCommune();
    }
}

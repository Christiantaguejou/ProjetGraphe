import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian TAGUEJOU on 20/05/2017.
 */
public class CsvCommunes{

    public static List<String> readFile(File file) {

        List<String> result = new ArrayList<String>();

        FileReader fr = null;
        try {
            fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);

            //Tant que la ligne n'est pas nulle, on continue la lecture du fichier
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                result.add(line);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("Fichier Introuvable");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Problème lors de la lecture du fichier");
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Commune> tableau(List<String> listeCommune){

        ArrayList<Commune> tabCommune = new ArrayList<>();
       // listeCommune
        for(int n = 1; n < listeCommune.size(); n++){
          //  System.out.println(listeCommune.get(n));
            //Le ; permettra de découper les chaines de caracteres listeCommune.get(n)
            String[] commune = listeCommune.get(n).split(";");
            tabCommune.add(new Commune(commune[0], commune[1], Integer.parseInt(commune[2]), Double.parseDouble(commune[3]),Double.parseDouble(commune[4])));
        }

        return tabCommune;
    }

}

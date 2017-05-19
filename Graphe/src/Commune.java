import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 19/05/2017.
 */
public class Commune {

    private String id;
    private String nom;
    private int population;
    private float longitude;
    private float latitude;

    public Commune(String id, String nom, int population, float longitude, float latitude){
        this.id = id;
        this.nom = nom;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static void readFile(File file) {

        List<String> result = new ArrayList<String>();

        FileReader fr = null;
        try {
                fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);

            //Tant que la ligne n'est pas nulle, on continue la lecture du fichier
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                result.add(line);
                System.out.println(line);
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
      //  return result;
    }

}

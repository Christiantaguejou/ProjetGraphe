import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Fabien on 19/05/2017.
 */
public class Main {

    public static void main(String[] args) {
    /*    List<String> fichier = Commune.readFile(new File("CommunesFrance.csv"));

        for (Iterator iter = listeNom.iterator(); iter.hasNext();)
        {
            String  ch2= (String)iter.next();
            System.out.println(ch2)  ;
        }*/

    Commune.readFile(new File("CommunesFrance.csv"));
    }
}

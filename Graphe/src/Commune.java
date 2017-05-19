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
}
